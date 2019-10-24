package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.models.AnnotationPoster;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentPfe;

public class AddGradeActivity extends AppCompatActivity {

    private List<AnnotationPoster> annotationPosterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final int projectId = intent.getIntExtra("projectId", 1000);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        TextView title = (TextView) findViewById(R.id.text_view_title_grade);
        title.setText(PlaceholderFragmentPfe.getProjectList().get(projectId).getTitle());

        TextView descriptionComment = (TextView) findViewById(R.id.text_view_description_grade);
        descriptionComment.setText(PlaceholderFragmentPfe.getProjectList().get(projectId).getDescrip());

        if(AppDataBase.getINSTANCE(this).annotationPosterDAO().loadAnnotationExist(projectId, LoginActivity.getUsername()) != null) {
            annotationPosterList = AppDataBase.getINSTANCE(this).annotationPosterDAO().loadAnnotationExist(projectId, LoginActivity.getUsername());
        }

        if(annotationPosterList.size() != 0){
            TextView oldCommentary = (TextView) findViewById(R.id.text_view_ancien_grade);
            oldCommentary.setText(getString(R.string.OldGrade) + " " + annotationPosterList.get(0).getGrade());
        } else {
            TextView oldCommentary = (TextView) findViewById(R.id.text_view_ancien_grade);
            oldCommentary.setText(getString(R.string.OldGrade) + " " + "Pas encore de note pour ce projet");
        }

        Button addGradeButton = (Button) findViewById(R.id.button_add);
        addGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerGrade = (Spinner) findViewById(R.id.edit_text_new_grade);
                long grade = Long.parseLong(spinnerGrade.getSelectedItem().toString());
                if(annotationPosterList.size() == 0) {
                    AnnotationPoster annotationPoster = new AnnotationPoster(projectId, LoginActivity.getUsername(), "", grade);
                    AppDataBase.getINSTANCE(v.getContext()).annotationPosterDAO().insert(annotationPoster);
                    showJurySucces(AppCompatActivity.RESULT_OK);
                }
                else{
                    AppDataBase.getINSTANCE(v.getContext()).annotationPosterDAO().
                            updateGradePoster(grade, projectId, LoginActivity.getUsername());
                    showJurySucces(AppCompatActivity.RESULT_OK);
                }
            }
        });

    }


    private void showJurySucces(@StringRes Integer successString) {
        String success = getString(R.string.GradeSaved);
        Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }    }
}
