package fr.eseo.dis.android.vpmb.projet_eseo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import java.util.Random;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.projet_eseo.ComMemberActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.CreatePseudoJuryManual;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.DataConverter;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.models.Porte;
import fr.eseo.dis.android.vpmb.models.RequestModel;
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
        context = getContext();

        if(AppDataBase.getINSTANCE(context).projectDAO().loadAll().isEmpty() ) {

            //Return up to five subject non confidential
            try {
                // Instantiate the RequestQueue.
                String token = LoginActivity.getToken();
                RequestQueue queue = Volley.newRequestQueue(context);
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
                                        AppDataBase.getINSTANCE(context).projectDAO().insert(projectToSave.get(i));

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
        View root = inflater.inflate(R.layout.fragment_com_member_jury, container, false);

        Button buttonAllPfe = (Button) root.findViewById(R.id.buttonManualJury);
        buttonAllPfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), CreatePseudoJuryManual.class);
                startActivity(intent);

        }
        }
        );
        Button buttonPfeJury = (Button) root.findViewById(R.id.buttonAutoJury);
        buttonPfeJury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //take a random number of elements we want for the new jury
                Random rand = new Random();

                List<Project> listOfProjects = AppDataBase.getINSTANCE(PlaceholderFragmentComJury.this.context).projectDAO().loadAll();
                int randomSize = rand.nextInt(listOfProjects.size()-1)+1;
                System.out.println(randomSize);
                List<fr.eseo.dis.android.vpmb.db.models.Project> randomList = getRandomElementList(listOfProjects, randomSize);


                AppDataBase.insertProjectJury(randomList, PlaceholderFragmentComJury.this.context);

                showJurySucces(AppCompatActivity.RESULT_OK);


            }
        });
        return root;

    }

    // Function select an element base on index and return
    // an element
    public List<fr.eseo.dis.android.vpmb.db.models.Project> getRandomElementList(List<fr.eseo.dis.android.vpmb.db.models.Project> list,
                                                                              int totalItems)
    {
        Random rand = new Random();

        //Create a copy of the list parameter

        List<fr.eseo.dis.android.vpmb.db.models.Project> projectListCopy = list;

        // create a temporary list for storing
        // selected element
        List<fr.eseo.dis.android.vpmb.db.models.Project> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {

            // take a random index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list
            newList.add(projectListCopy.get(randomIndex));

            // Remove selected element from orginal list
            projectListCopy.remove(randomIndex);
        }

        return newList;
    }

    private void showJurySucces(@StringRes Integer successString) {
        String success = getString(R.string.JuryProjectSuccess);
        Toast.makeText(context, success, Toast.LENGTH_SHORT).show();

    }

    private void showSuccesProjectLoaded(@StringRes Integer successString) {
        String success = getString(R.string.SubjectLoaded);
        Toast.makeText(context, success, Toast.LENGTH_SHORT).show();

    }

}