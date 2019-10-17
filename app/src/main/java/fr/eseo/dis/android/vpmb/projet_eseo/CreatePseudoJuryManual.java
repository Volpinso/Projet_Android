package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
import fr.eseo.dis.android.vpmb.adapters.CreatePseudoJuryAdapter;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.DataConverter;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.db.models.ProjectJury;
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;
import fr.eseo.dis.android.vpmb.models.Liprj;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentComJury;

public class CreatePseudoJuryManual extends AppCompatActivity implements View.OnClickListener {


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
        //Find last jury id
        List<PseudoJury> pseudoJuries = AppDataBase.getINSTANCE(CreatePseudoJuryManual.this).pseudoJuryDAO().loadAll();
        long idLastPseudoJury = -1;
        System.out.println(pseudoJuries.toString());
        if (!pseudoJuries.isEmpty()) {
            idLastPseudoJury = pseudoJuries.get(pseudoJuries.size() - 1).getIdPseudoJury();
        }


        if (idLastPseudoJury != -1) {
            System.out.println(pseudoJuries);
            System.out.println(idLastPseudoJury);
            AppDataBase.getINSTANCE(CreatePseudoJuryManual.this).pseudoJuryDAO().insert(new PseudoJury(idLastPseudoJury + 1));

            for (int i = 0; i < projectsJury.size(); i++) {
                //Insert new jury and juryProject
                AppDataBase.getINSTANCE(CreatePseudoJuryManual.this).projectJuryDAO().insert(new ProjectJury(projectsJury.get(i).getIdProject(), idLastPseudoJury + 1));
            }
        } else {
            AppDataBase.getINSTANCE(CreatePseudoJuryManual.this).pseudoJuryDAO().insert(new PseudoJury(0));

            for (int i = 0; i < projectsJury.size(); i++) {
                //Insert new jury and juryProject
                AppDataBase.getINSTANCE(CreatePseudoJuryManual.this).projectJuryDAO().insert(new ProjectJury(projectsJury.get(i).getIdProject(), 0));
            }

        }


        }

}
