package fr.eseo.dis.android.vpmb.projet_eseo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.MyPFERecyclerViewAdapter;


public class MyPfeActivity extends AppCompatActivity {

    MyPFERecyclerViewAdapter myPFERecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list_subjects);
        RecyclerView pfeRecycler = (RecyclerView)findViewById(R.id.subject_list);
        pfeRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        pfeRecycler.setLayoutManager(llm);
        myPFERecyclerViewAdapter = new MyPFERecyclerViewAdapter(this);
        pfeRecycler.setAdapter(myPFERecyclerViewAdapter);

    }
}
