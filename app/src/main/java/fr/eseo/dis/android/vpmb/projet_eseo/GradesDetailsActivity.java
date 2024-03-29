package fr.eseo.dis.android.vpmb.projet_eseo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.GradesDetailsJuryAdapter;


public class GradesDetailsActivity extends AppCompatActivity {

    GradesDetailsJuryAdapter gradesDetailsJuryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String title = intent.getStringExtra("projectName");
        setTitle(title);
        setContentView(R.layout.activity_grades_details);
        RecyclerView pfeRecycler = (RecyclerView)findViewById(R.id.rv_filmList);
        pfeRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        pfeRecycler.setLayoutManager(llm);
        gradesDetailsJuryAdapter = new GradesDetailsJuryAdapter(this);
        pfeRecycler.setAdapter(gradesDetailsJuryAdapter);

    }
}
