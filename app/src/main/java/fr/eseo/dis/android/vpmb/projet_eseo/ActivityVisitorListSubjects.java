package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;
import java.util.Random;

import fr.eseo.dis.android.vpmb.adapters.VisitorSubjectRecyclerViewAdapter;
import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;
import fr.eseo.dis.android.vpmb.db.models.Visitor;

public class ActivityVisitorListSubjects extends AppCompatActivity {


    private VisitorSubjectRecyclerViewAdapter visitorSubjectRecyclerViewAdapter;
    private long numVisitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list_subjects);
        RecyclerView subjectsRecycler = (RecyclerView)findViewById(R.id.rv_filmList);
        subjectsRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        subjectsRecycler.setLayoutManager(llm);
        visitorSubjectRecyclerViewAdapter = new VisitorSubjectRecyclerViewAdapter(this);
        subjectsRecycler.setAdapter(visitorSubjectRecyclerViewAdapter);

        List<Visitor> visitorsInDB = AppDataBase.getINSTANCE(ActivityVisitorListSubjects.this).visitorDAO().loadAll();

        //Select a random pseudoJury
        List<PseudoJury> jury = AppDataBase.getINSTANCE(ActivityVisitorListSubjects.this).pseudoJuryDAO().loadAll();

        Random random = new Random();
        int randomJury = random.nextInt(jury.size());

        PseudoJury pseudoJuryChosen = jury.get(randomJury);

        if(visitorsInDB.isEmpty()){
            AppDataBase.getINSTANCE(ActivityVisitorListSubjects.this).visitorDAO().insert(new Visitor(0, pseudoJuryChosen.getIdPseudoJury()));
        }else{
            long idVisitor = jury.size();
            AppDataBase.getINSTANCE(ActivityVisitorListSubjects.this).visitorDAO().insert(new Visitor(idVisitor, pseudoJuryChosen.getIdPseudoJury()));

        }
    }
}
