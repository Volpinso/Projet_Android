package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentPfe;

public class CommentaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int projectId = intent.getIntExtra("projectId", 1000);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentary);

        TextView title = (TextView) findViewById(R.id.text_view_title_comment);
        title.setText(PlaceholderFragmentPfe.getProjectList().get(projectId).getTitle());

        TextView descriptionComment = (TextView) findViewById(R.id.text_view_description_comment);
        descriptionComment.setText(PlaceholderFragmentPfe.getProjectList().get(projectId).getDescrip());

    }
}
