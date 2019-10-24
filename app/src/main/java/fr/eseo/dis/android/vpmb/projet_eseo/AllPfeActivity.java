package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.PFERecyclerViewAdapter;



public class AllPfeActivity extends AppCompatActivity {

    PFERecyclerViewAdapter PfeRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list_subjects);
        RecyclerView pfeRecycler = (RecyclerView)findViewById(R.id.subject_list);
        pfeRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        pfeRecycler.setLayoutManager(llm);
        PfeRecyclerViewAdapter = new PFERecyclerViewAdapter(this);
        pfeRecycler.setAdapter(PfeRecyclerViewAdapter);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.poster_position_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_position:
                Intent intentComment = new Intent(this, PosterLocationActivity.class);
                startActivity(intentComment);
                return (true);
        }
        return (super.onOptionsItemSelected(item));

    }
}
