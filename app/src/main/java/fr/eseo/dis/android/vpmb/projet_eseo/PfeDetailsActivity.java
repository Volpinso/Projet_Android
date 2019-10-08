package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentPfe;

public class PfeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int projectId = intent.getIntExtra("projectId", 1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pfe_details);
        setTitle(PlaceholderFragmentPfe.getProjectList().get(projectId).getTitle());
        TextView textView1 = (TextView) findViewById(R.id.textView);
        textView1.setText(PlaceholderFragmentPfe.getProjectList().get(projectId).getDescrip());
    }
}
