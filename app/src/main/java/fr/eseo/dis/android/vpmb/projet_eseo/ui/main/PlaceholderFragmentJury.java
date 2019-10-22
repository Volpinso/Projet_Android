package fr.eseo.dis.android.vpmb.projet_eseo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import fr.eseo.dis.android.vpmb.adapters.JuryRecyclerViewAdapter;
import fr.eseo.dis.android.vpmb.adapters.PFERecyclerViewAdapter;
import fr.eseo.dis.android.vpmb.models.Juries;
import fr.eseo.dis.android.vpmb.models.Liprj;
import fr.eseo.dis.android.vpmb.models.Myjur;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentJury extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    JuryRecyclerViewAdapter juryRecyclerViewAdapter;

    public static PlaceholderFragmentJury newInstance(int index) {
        PlaceholderFragmentJury fragment = new PlaceholderFragmentJury();
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
        if(LoginActivity.getJuryList().size() != 0) {
            View root = inflater.inflate(R.layout.fragment_jury_jury, container, false);
            RecyclerView juryRecycler = (RecyclerView) root.findViewById(R.id.rv_juryList);
            juryRecycler.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(RecyclerView.VERTICAL);
            juryRecycler.setLayoutManager(llm);
            juryRecyclerViewAdapter = new JuryRecyclerViewAdapter(this);
            juryRecycler.setAdapter(juryRecyclerViewAdapter);
            return root;
        }
        else {
            View root = inflater.inflate(R.layout.fragment_jury_jury_empty, container, false);
            return root;
        }
    }
}