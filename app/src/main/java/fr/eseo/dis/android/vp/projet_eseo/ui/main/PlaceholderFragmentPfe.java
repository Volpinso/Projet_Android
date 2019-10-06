package fr.eseo.dis.android.vp.projet_eseo.ui.main;

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

import fr.eseo.dis.android.vp.projet_eseo.AllPfeActivity;
import fr.eseo.dis.android.vp.projet_eseo.MainActivity;
import fr.eseo.dis.android.vp.projet_eseo.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentPfe extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private SharedPreferences sharedPreferences;

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
}