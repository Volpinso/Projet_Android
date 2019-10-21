package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.models.Grade;

public class GradeFormVisitor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_form_visitor);
        setTitle(R.string.GradePosterVisitor);
        final Spinner spinner = (Spinner) findViewById(R.id.grade_spinner);
        final EditText edit = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button_grade);
        System.out.println(AppDataBase.getINSTANCE(GradeFormVisitor.this).gradeDAO().loadAll());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long idNotation=0;
                List<Grade> grades = AppDataBase.getINSTANCE(GradeFormVisitor.this).gradeDAO().loadAll();
                if(!grades.isEmpty()){
                    idNotation=grades.size();
                }

                if (!"".equals(spinner.getSelectedItem().toString())){
                    AppDataBase.getINSTANCE(GradeFormVisitor.this).gradeDAO().insert(new Grade(idNotation, Double.parseDouble(String.valueOf(spinner.getSelectedItemId())), edit.getText().toString(), ActivityVisitorListSubjects.getPseudoJuryVisitorId()));

                    showNotationSucces(AppCompatActivity.RESULT_OK, getString(R.string.GradeSaved));
                }
            }
        });

    }

    private void showNotationSucces(@StringRes Integer successString, String message) {
        String success = message;
        Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }

}