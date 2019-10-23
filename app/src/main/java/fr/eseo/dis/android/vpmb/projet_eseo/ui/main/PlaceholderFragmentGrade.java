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



import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.GradeRecyclerViewJuryAdapter;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentGrade extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    GradeRecyclerViewJuryAdapter pfeRecyclerView;

    public static PlaceholderFragmentGrade newInstance(int index) {
        PlaceholderFragmentGrade fragment = new PlaceholderFragmentGrade();
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
            View root = inflater.inflate(R.layout.fragment_jury_grade, container, false);

            RecyclerView pfeRecycler = (RecyclerView) root.findViewById(R.id.rv_pfe);
            pfeRecycler.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(RecyclerView.VERTICAL);
            pfeRecycler.setLayoutManager(llm);
            pfeRecyclerView = new GradeRecyclerViewJuryAdapter(this);
            pfeRecycler.setAdapter(pfeRecyclerView);

            return root;
        } else {
            View root = inflater.inflate(R.layout.fragment_jury_grade_empty, container, false);
            return root;
        }
    }

}