package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.VisitorsGradesRecyclervViewComAdapter;
import fr.eseo.dis.android.vpmb.db.AppDataBase;

public class VisitorsGradesActivity extends AppCompatActivity {

    VisitorsGradesRecyclervViewComAdapter visitorsGradesRecyclervViewComAdapter;
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
        visitorsGradesRecyclervViewComAdapter = new VisitorsGradesRecyclervViewComAdapter(this);
        subjectsRecycler.setAdapter(visitorsGradesRecyclervViewComAdapter);

        TextView textView = (TextView) findViewById(R.id.textView6);
        if(AppDataBase.getINSTANCE(this).gradeDAO().
                loadSubjectGrade(VisitorsGradesActivity.getProjectId()).isEmpty()){
            textView.setVisibility(View.VISIBLE);
        }
    }

    public static long getProjectId() {
        return projectId;
    }
}
