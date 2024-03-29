package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;
import java.util.Random;

import fr.eseo.dis.android.vpmb.adapters.VisitorSubjectRecyclerViewAdapter;
import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;
import fr.eseo.dis.android.vpmb.db.models.Visitor;

public class VisitorListSubjectsActivity extends AppCompatActivity {


    private VisitorSubjectRecyclerViewAdapter visitorSubjectRecyclerViewAdapter;

    private long numVisitor;
    private static long pseudoJuryVisitorId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list_subjects);
        RecyclerView subjectsRecycler = (RecyclerView)findViewById(R.id.subject_list);
        subjectsRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        subjectsRecycler.setLayoutManager(llm);
        visitorSubjectRecyclerViewAdapter = new VisitorSubjectRecyclerViewAdapter(this);
        subjectsRecycler.setAdapter(visitorSubjectRecyclerViewAdapter);

        List<Visitor> visitorsInDB = AppDataBase.getINSTANCE(VisitorListSubjectsActivity.this).visitorDAO().loadAll();

        //Select a random pseudoJury
        List<PseudoJury> jury = AppDataBase.getINSTANCE(VisitorListSubjectsActivity.this).pseudoJuryDAO().loadAll();

        Random random = new Random();
        int randomJury = random.nextInt(jury.size());

        pseudoJuryVisitorId = jury.get(randomJury).getIdPseudoJury();

        if(visitorsInDB.isEmpty()){
            numVisitor = 0;
            AppDataBase.getINSTANCE(VisitorListSubjectsActivity.this).visitorDAO().insert(new Visitor(numVisitor, pseudoJuryVisitorId));
        }else{
            numVisitor = jury.size();
            AppDataBase.getINSTANCE(VisitorListSubjectsActivity.this).visitorDAO().insert(new Visitor(numVisitor, pseudoJuryVisitorId));

        }
    }


    public long getNumVisitor() {
        return numVisitor;
    }

    public static long getPseudoJuryVisitorId() {
        return pseudoJuryVisitorId;
    }
}
