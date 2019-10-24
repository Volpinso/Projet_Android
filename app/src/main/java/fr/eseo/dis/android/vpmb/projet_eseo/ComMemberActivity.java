package fr.eseo.dis.android.vpmb.projet_eseo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import androidx.annotation.StringRes;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.DataConverter;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.models.Porte;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.SectionsPagerAdapterCom;

public class ComMemberActivity extends AppCompatActivity {

    private static List<fr.eseo.dis.android.vpmb.models.Project> projectList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_member);
        SectionsPagerAdapterCom sectionsPagerAdapterCom = new SectionsPagerAdapterCom(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapterCom);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Button buttonLogout = (Button) findViewById(R.id.filterButtonMainWorkorderSelection);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComMemberActivity.this, MainActivity.class);
                LoginActivity.setUsername("");
                LoginActivity.setToken("");
                startActivity(intent);
            }
        });

        Button buttonCleanDatabase = (Button) findViewById(R.id.clean_database);
        buttonCleanDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDataBase.getINSTANCE(v.getContext()).projectJuryDAO().deleteAll();
                AppDataBase.getINSTANCE(v.getContext()).gradeDAO().deleteAll();
                AppDataBase.getINSTANCE(v.getContext()).visitorDAO().deleteAll();
                AppDataBase.getINSTANCE(v.getContext()).pseudoJuryDAO().deleteAll();
                AppDataBase.getINSTANCE(v.getContext()).projectDAO().deleteAll();
                String success = getString(R.string.TableEmptied);
                Toast.makeText(ComMemberActivity.this, success, Toast.LENGTH_SHORT).show();

                //Return up to five subject non confidential
                try {
                    // Instantiate the RequestQueue.
                    projectList=new ArrayList<>();
                    String token = LoginActivity.getToken();
                    RequestQueue queue = Volley.newRequestQueue(ComMemberActivity.this);
                    String url = RequestModel.getRandomNonConfidProjetc(LoginActivity.getUsername(), token);

                    // Request a string response from the provided URL.
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Gson gson = new Gson();
                                    Porte responseModel = gson.fromJson(String.valueOf(response),
                                            Porte.class);
                                    for(int i = 0 ; i < responseModel.getProjects().length; i++){
                                        projectList.add(responseModel.getProjects()[i]);
                                    }
                                    setProjectList(projectList);

                                    List<Project> projectToSave = DataConverter.convertProjectsToProject(projectList);
                                    if(!projectToSave.isEmpty()){

                                        for(int i=0; i<projectToSave.size(); i++){

                                            AppDataBase.getINSTANCE(ComMemberActivity.this).projectDAO().insert(projectToSave.get(i));

                                        }
                                    }
                                    showSuccesProjectLoaded(AppCompatActivity.RESULT_OK);

                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    projectList = null;
                                    System.out.println(error);

                                }
                            });

                    queue.add(jsonObjectRequest);
                    jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                            50000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public static List<fr.eseo.dis.android.vpmb.models.Project> getProjectList() {
        return projectList;
    }

    public static void setProjectList(List<fr.eseo.dis.android.vpmb.models.Project> projectList) {
        ComMemberActivity.projectList = projectList;
    }

    private void showSuccesProjectLoaded(@StringRes Integer successString) {
        String success = getString(R.string.SubjectLoaded);
        Toast.makeText(ComMemberActivity.this, success, Toast.LENGTH_SHORT).show();

    }
}