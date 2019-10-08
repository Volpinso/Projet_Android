package fr.eseo.dis.android.vpmb.projet_eseo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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
import fr.eseo.dis.android.vpmb.models.Liprj;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.AllPfeActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.MainActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentPfe extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private SharedPreferences sharedPreferences;

    private static List<Projects> projectList = new ArrayList<>();
    private static Context context;

    public static List<Projects> getProjectList() {
        return projectList;
    }

    public static void setProjectList(List<Projects> projectList) {
        PlaceholderFragmentPfe.projectList = projectList;
    }

    public static PlaceholderFragmentPfe newInstance(int index) {
        PlaceholderFragmentPfe fragment = new PlaceholderFragmentPfe();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        try {
            // Instantiate the RequestQueue.
            String token = LoginActivity.getToken();
            RequestQueue queue = Volley.newRequestQueue(getAppContext());
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
                            setProjectList(projectList);

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

        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jury_pfe, container, false);
        Button buttonAllPfe = (Button) root.findViewById(R.id.buttonAllPfe);
        buttonAllPfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllPfeActivity.class);
                startActivity(intent);
            }
        });
        Button buttonPfeJury = (Button) root.findViewById(R.id.buttonPfeJury);
        buttonPfeJury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    public static List<Projects> getListRequest(){
        try {
            // Instantiate the RequestQueue.
            String token = LoginActivity.getToken();
            RequestQueue queue = Volley.newRequestQueue(getAppContext());
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

    public static Context getAppContext() {
        return PlaceholderFragmentPfe.context;
    }
}