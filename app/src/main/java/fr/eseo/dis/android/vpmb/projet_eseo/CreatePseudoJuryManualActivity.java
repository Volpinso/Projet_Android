package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.CreatePseudoJuryAdapter;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.models.Project;

public class CreatePseudoJuryManualActivity extends AppCompatActivity implements View.OnClickListener {


    CreatePseudoJuryAdapter createPseudoJuryAdapter;
    private List<ClipData.Item> currentSelectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sélectionnez les sujets");
        setContentView(R.layout.activity_create_pseudo_jury_manual);
        RecyclerView pfeRecycler = (RecyclerView)findViewById(R.id.rv_pfe);
        pfeRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        pfeRecycler.setLayoutManager(llm);
        createPseudoJuryAdapter = new CreatePseudoJuryAdapter(this);
        pfeRecycler.setAdapter(createPseudoJuryAdapter);
        final Button button = (Button) findViewById(R.id.subject_selected);
        button.setEnabled(true);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        List<Project> projectsJury = createPseudoJuryAdapter.getProjectSelected();

        AppDataBase.insertProjectJury(projectsJury, CreatePseudoJuryManualActivity.this);

        showJurySucces(AppCompatActivity.RESULT_OK);

        }

    private void showJurySucces(@StringRes Integer successString) {
        String success = getString(R.string.JuryProjectSuccess);
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
