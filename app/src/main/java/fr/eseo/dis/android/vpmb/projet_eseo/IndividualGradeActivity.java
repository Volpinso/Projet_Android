package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.GradeRecyclerViewJuryAdapter;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.models.StudentAnnotation;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;

public class IndividualGradeActivity extends AppCompatActivity {

    private List<StudentAnnotation> studentAnnotationList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_grade);
        setTitle(getString(R.string.GiveGrade));
        final Intent intent = getIntent();
        final int studentId = intent.getIntExtra("studentId", 1000);
        TextView studentName = (TextView) findViewById(R.id.text_view_name_grade);
        studentName.setText(intent.getStringExtra("studentName"));

        TextView studentGrade = (TextView) findViewById(R.id.text_view_ancien_grade);
        studentGrade.setText(getString(R.string.OldGrade) + " " + intent.getStringExtra("studentGrade"));

        if(AppDataBase.getINSTANCE(this).studentAnnotationDAO().loadStudentAnnotatinExist(
                GradeRecyclerViewJuryAdapter.getProjectId(), studentId, LoginActivity.getUsername()) != null) {
            studentAnnotationList = AppDataBase.getINSTANCE(this).studentAnnotationDAO().loadStudentAnnotatinExist(
                    GradeRecyclerViewJuryAdapter.getProjectId(), studentId, LoginActivity.getUsername());
        }

        if(studentAnnotationList.size() != 0){
            TextView oldCommentary = (TextView) findViewById(R.id.text_old_comment);
            oldCommentary.setText(getString(R.string.OldComment) + " " + studentAnnotationList.get(0).getComment());
        } else {
            TextView oldCommentary = (TextView) findViewById(R.id.text_old_comment);
            oldCommentary.setText(getString(R.string.OldComment) + " " + getString(R.string.NoComment));
        }

        Button buttonAddGrade = (Button) findViewById(R.id.button_add_new_grade);
        buttonAddGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner) findViewById(R.id.edit_text_new_grade);
                String grade = spinner.getSelectedItem().toString();

                try {
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(v.getContext());
                    String url = RequestModel.setStudentGrade(
                            LoginActivity.getUsername(), GradeRecyclerViewJuryAdapter.getProjectId(), studentId,
                            Integer.parseInt(grade), LoginActivity.getToken()
                    );

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Handle error
                                }
                            });

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                EditText comment = (EditText) findViewById(R.id.edit_text_new_comment_grade);
                String commentText = comment.getText().toString();

                if(studentAnnotationList.size() == 0) {
                    StudentAnnotation studentAnnotation = new StudentAnnotation(studentId, LoginActivity.getUsername(),
                            GradeRecyclerViewJuryAdapter.getProjectId(), commentText);
                    AppDataBase.getINSTANCE(v.getContext()).studentAnnotationDAO().insert(studentAnnotation);
                    Intent gotoScreenVar = new Intent(IndividualGradeActivity.this, JuryMemberActivity.class);
                    String success = getString(R.string.GradeSaved);
                    Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
                    gotoScreenVar.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(gotoScreenVar);
                }
                else{
                    AppDataBase.getINSTANCE(v.getContext()).studentAnnotationDAO().
                            updateStudentAnnotationPoster(commentText, GradeRecyclerViewJuryAdapter.getProjectId(), studentId, LoginActivity.getUsername());
                    Intent gotoScreenVar = new Intent(IndividualGradeActivity.this, JuryMemberActivity.class);
                    String success = getString(R.string.GradeSaved);
                    Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
                    gotoScreenVar.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(gotoScreenVar);
                }

            }
        });
    }
}
