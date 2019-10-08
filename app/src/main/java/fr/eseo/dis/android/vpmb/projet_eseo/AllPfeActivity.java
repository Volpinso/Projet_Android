package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.PFERecyclerViewAdapter;
import fr.eseo.dis.android.vpmb.models.Liprj;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;


public class AllPfeActivity extends AppCompatActivity {

    PFERecyclerViewAdapter PfeRecyclerViewAdapter;
    private static List<Projects> projectList = new ArrayList<>();
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AllPfeActivity.context = getApplicationContext();
        setContentView(R.layout.activity_visitor_list_subjects);
        RecyclerView pfeRecycler = (RecyclerView)findViewById(R.id.rv_filmList);
        pfeRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        pfeRecycler.setLayoutManager(llm);
        PfeRecyclerViewAdapter = new PFERecyclerViewAdapter(this);
        pfeRecycler.setAdapter(PfeRecyclerViewAdapter);

    }

    public static List<Projects> listRequest(){
        try {
            // Instantiate the RequestQueue.
            String token = LoginActivity.getToken();
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = RequestModel.getAllProjectrequest(LoginActivity.getUsername(), token);
            System.out.println(url);
            // Request a string response from the provided URL.
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            Liprj responseModel = gson.fromJson(String.valueOf(response),
                                    Liprj.class);
                            for(int i = 0 ; i < responseModel.getProjects().length; i++){
                                projectList.add(responseModel.getProjects()[i]);
                            }
                            try {
                                Thread.sleep(5000);
                            }
                            catch (Exception e){

                            }
                            System.out.println(projectList);

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            projectList = null;
                            System.out.println("error");

                        }
                    });
            queue.add(jsonObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
}
