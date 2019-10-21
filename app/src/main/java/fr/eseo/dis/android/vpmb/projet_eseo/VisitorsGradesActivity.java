package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.VisitorsGradesRecyclerviewAdapter;

public class VisitorsGradesActivity extends AppCompatActivity {

    VisitorsGradesRecyclerviewAdapter visitorsGradesRecyclerviewAdapter;
    private static long projectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors_grades);
        Intent intent =getIntent();
        projectId = intent.getLongExtra("projectID",1000L);
        RecyclerView subjectsRecycler = (RecyclerView)findViewById(R.id.grade_list);
        subjectsRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        subjectsRecycler.setLayoutManager(llm);
        visitorsGradesRecyclerviewAdapter = new VisitorsGradesRecyclerviewAdapter(this);
        subjectsRecycler.setAdapter(visitorsGradesRecyclerviewAdapter);
    }

    public static long getProjectId() {
        return projectId;
    }
}
