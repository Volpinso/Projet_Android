package fr.eseo.dis.android.vpmb.projet_eseo;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.PFERecyclerViewComAdapter;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;


public class MyPFEDetailsComActivity extends AppCompatActivity {

    private static String fullB64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final int projectId = intent.getIntExtra("projectId", 1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pfe_details);

        setTitle(LoginActivity.getProjectList().get(projectId).getTitle());

        Button buttonFull = (Button) findViewById(R.id.full_screen);
        Button buttonAddSubject = (Button) findViewById(R.id.add_subject);

        TextView description = (TextView) findViewById(R.id.textView);
        description.setText(LoginActivity.getProjectList().get(projectId).getDescrip());

        TextView supervisor = (TextView) findViewById(R.id.supervisor);
        if (LoginActivity.getProjectList().get(projectId).getSupervisor().getSurname() != null) {
            supervisor.setText("Tuteur : " + LoginActivity.getProjectList().get(projectId).getSupervisor().getSurname() + " "
                    + LoginActivity.getProjectList().get(projectId).getSupervisor().getForename());
        } else {
            supervisor.setText("Tuteur : Pas de tuteur pour ce projet");
        }

        TextView team = (TextView) findViewById(R.id.team_label);
        if (LoginActivity.getProjectList().get(projectId).getStudents() != null) {
            String equipe = "Team : ";
            for (int i =0 ; i < LoginActivity.getProjectList().get(projectId).getStudents().length - 1; i++){
                equipe = equipe + LoginActivity.getProjectList().get(projectId).getStudents()[i].getSurname() + " " +
                        LoginActivity.getProjectList().get(projectId).getStudents()[i].getForename() + ", ";
            }
            equipe = equipe + LoginActivity.getProjectList().get(projectId).getStudents()[LoginActivity.getProjectList().get(projectId).getStudents().length -1].getSurname() + " " +
                    LoginActivity.getProjectList().get(projectId).getStudents()[LoginActivity.getProjectList().get(projectId).getStudents().length -1].getForename();
            team.setText(equipe);
        } else {
            team.setText("Team : Pas d'équipe pour ce projet");
        }

        ImageView imageView = (ImageView) this.findViewById(R.id.poster_image);

        if(PFERecyclerViewComAdapter.getThumbnail()!= null && !PFERecyclerViewComAdapter.getThumbnail().equals("No Poster")) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(convertB64toImage(PFERecyclerViewComAdapter.getThumbnail()), 0,
                    convertB64toImage(PFERecyclerViewComAdapter.getThumbnail()).length));
        }
        else {
            TextView posterLabel = (TextView) findViewById(R.id.poster_label);
            posterLabel.setText("Poster : No Poster");
            buttonFull.setEnabled(false);
        }
        buttonFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = getIntent();
                    int projectId = intent.getIntExtra("projectId", 1000);
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(v.getContext());
                    String url = RequestModel.getPoster(LoginActivity.getUsername(), projectId, "FLB64", LoginActivity.getToken());

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.contains("Invalid Credentials")){
                                        setFullB64("No Poster");
                                    }
                                    else{
                                        setFullB64(response);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Handle error
                                }
                            });

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(7000);
                }
                catch (Exception e){

                }
                Intent intent = new Intent(MyPFEDetailsComActivity.this, PosterFullComActivity.class);
                startActivity(intent);
            }
        });

        if (LoginActivity.getProjectList().get(projectId).getConfid()==0){
            buttonAddSubject.setVisibility(View.VISIBLE);
        }

       buttonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Projects projectToAdd = LoginActivity.getProjectList().get(projectId);

                if (AppDataBase.getINSTANCE(MyPFEDetailsComActivity.this).projectDAO().selectProject(projectToAdd.getProjectId())!=null){
                    showJurySucces(AppCompatActivity.RESULT_CANCELED, getString(R.string.SubjectAlreadyAdded));
                }else {
                    fr.eseo.dis.android.vpmb.db.models.Project projectToDb = new fr.eseo.dis.android.vpmb.db.models.Project(projectToAdd.getProjectId(), projectToAdd.getTitle(), projectToAdd.getDescrip(), PFERecyclerViewComAdapter.getThumbnail());

                    AppDataBase.getINSTANCE(MyPFEDetailsComActivity.this).projectDAO().insert(projectToDb);

                    showJurySucces(AppCompatActivity.RESULT_OK, getString(R.string.SubjectAdded));

                }
            }
        });
    }

    public byte[] convertB64toImage(String base64){

        byte[] imageAsBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);

        return  imageAsBytes;

    }



    public static String getFullB64(){
        return fullB64;
    }

    public static void setFullB64(String fullB64){
        MyPFEDetailsComActivity.fullB64 = fullB64;
    }

    private void showJurySucces(@StringRes Integer successString, String message) {
        String success = message;
        Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }    }


}
