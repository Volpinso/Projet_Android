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
import fr.eseo.dis.android.vpmb.adapters.PFERecyclerViewComAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentComPfe extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    PFERecyclerViewComAdapter pfeRecyclerView;

    public static PlaceholderFragmentComPfe newInstance(int index) {
        PlaceholderFragmentComPfe fragment = new PlaceholderFragmentComPfe();
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

        View root = inflater.inflate(R.layout.fragment_com_member_pfe, container, false);

        RecyclerView pfeRecycler = (RecyclerView) root.findViewById(R.id.rv_pfe);
        pfeRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        pfeRecycler.setLayoutManager(llm);
        pfeRecyclerView = new PFERecyclerViewComAdapter(this);
        pfeRecycler.setAdapter(pfeRecyclerView);

        return root;
    }

}