package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import fr.eseo.dis.android.vpmb.adapters.SubjectRecyclerViewAdapter;
import fr.eseo.dis.android.vp.projet_eseo.R;

public class ActivityVisitorListSubjects extends AppCompatActivity {


    private SubjectRecyclerViewAdapter subjectRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list_subjects);
        RecyclerView subjectsRecycler = (RecyclerView)findViewById(R.id.rv_filmList);
        subjectsRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        subjectsRecycler.setLayoutManager(llm);
        subjectRecyclerViewAdapter = new SubjectRecyclerViewAdapter(this);
        subjectsRecycler.setAdapter(subjectRecyclerViewAdapter);

    }
}
