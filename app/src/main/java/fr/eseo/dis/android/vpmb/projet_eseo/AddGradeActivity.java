package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentPfe;

public class AddGradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int projectId = intent.getIntExtra("projectId", 1000);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        TextView title = (TextView) findViewById(R.id.text_view_title_grade);
        title.setText(PlaceholderFragmentPfe.getProjectList().get(projectId).getTitle());

        TextView descriptionComment = (TextView) findViewById(R.id.text_view_description_grade);
        descriptionComment.setText(PlaceholderFragmentPfe.getProjectList().get(projectId).getDescrip());

    }
}
