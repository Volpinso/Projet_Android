package fr.eseo.dis.android.vpmb.projet_eseo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.DefaultRetryPolicy;
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
import fr.eseo.dis.android.vpmb.CreatePseudoJuryManual;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.DataConverter;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;
import fr.eseo.dis.android.vpmb.models.Liprj;
import fr.eseo.dis.android.vpmb.models.Porte;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.AllPfeActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.MyPfeActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentComJury extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Context context;
    private PageViewModel pageViewModel;
    public static List<fr.eseo.dis.android.vpmb.models.Project> getProjectList() {
        return projectList;
    }

    public static void setProjectList(List<fr.eseo.dis.android.vpmb.models.Project> projectList) {
        PlaceholderFragmentComJury.projectList = projectList;
    }

    private static List<fr.eseo.dis.android.vpmb.models.Project> projectList = new ArrayList<>();

    public static PlaceholderFragmentComJury newInstance(int index) {
        PlaceholderFragmentComJury fragment = new PlaceholderFragmentComJury();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        context = getContext();
        View root = inflater.inflate(R.layout.fragment_com_member_jury, container, false);
        Button buttonAllPfe = (Button) root.findViewById(R.id.buttonManualJury);
        buttonAllPfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppDataBase.getINSTANCE(context).projectDAO().loadAll().isEmpty() ) {

                    //Return up to five subject non confidential
                    try {
                        // Instantiate the RequestQueue.
                        String token = LoginActivity.getToken();
                        RequestQueue queue = Volley.newRequestQueue(v.getContext());
                        String url = RequestModel.getRandomNonConfidProjetc(LoginActivity.getUsername(), token);
                        System.out.println(url);
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
                                                AppDataBase.getINSTANCE(context).projectDAO().insert(projectToSave.get(i));
                                                System.out.println(AppDataBase.getINSTANCE(context).projectDAO().loadAll());

                                            }
                                        }

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

                Intent intent = new Intent(getActivity(), CreatePseudoJuryManual.class);
                startActivity(intent);

        }
        }
        );
        Button buttonPfeJury = (Button) root.findViewById(R.id.buttonAutoJury);
        buttonPfeJury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyPfeActivity.class);
                startActivity(intent);
            }
        });
        return root;

    }



}