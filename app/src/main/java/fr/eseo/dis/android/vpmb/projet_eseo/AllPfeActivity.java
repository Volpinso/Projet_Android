package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.PFERecyclerViewAdapter;



public class AllPfeActivity extends AppCompatActivity {

    PFERecyclerViewAdapter PfeRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list_subjects);
        RecyclerView pfeRecycler = (RecyclerView)findViewById(R.id.rv_filmList);
        pfeRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        pfeRecycler.setLayoutManager(llm);
        PfeRecyclerViewAdapter = new PFERecyclerViewAdapter(this);
        pfeRecycler.setAdapter(PfeRecyclerViewAdapter);

    }
}
