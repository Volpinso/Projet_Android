package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.PFERecyclerViewAdapter;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentPfe;

public class PfeDetailsActivity extends AppCompatActivity {

    private static String fullB64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int projectId = intent.getIntExtra("projectId", 1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pfe_details);

        setTitle(PlaceholderFragmentPfe.getProjectList().get(projectId).getTitle());

        Button buttonFull = (Button) findViewById(R.id.full_screen);

        TextView description = (TextView) findViewById(R.id.textView);
        description.setText(PlaceholderFragmentPfe.getProjectList().get(projectId).getDescrip());

        TextView supervisor = (TextView) findViewById(R.id.supervisor);
        if (PlaceholderFragmentPfe.getProjectList().get(projectId).getSupervisor().getSurname() != null) {
            supervisor.setText("Tuteur : " + PlaceholderFragmentPfe.getProjectList().get(projectId).getSupervisor().getSurname() + " "
                    + PlaceholderFragmentPfe.getProjectList().get(projectId).getSupervisor().getForename());
        } else {
            supervisor.setText("Tuteur : Pas de tuteur pour ce projet");
        }

        TextView team = (TextView) findViewById(R.id.team_label);
        if (PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents() != null) {
           String equipe = "Team : ";
           for (int i =0 ; i < PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents().length - 1; i++){
               equipe = equipe + PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents()[i].getSurname() + " " +
                       PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents()[i].getForename() + ", ";
           }
            equipe = equipe + PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents()[PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents().length -1].getSurname() + " " +
                    PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents()[PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents().length -1].getForename();
           team.setText(equipe);
        } else {
            team.setText("Team : Pas d'équipe pour ce projet");
        }

        ImageView imageView = (ImageView) this.findViewById(R.id.poster_image);

        if(!PFERecyclerViewAdapter.getThumbnail().equals("No Poster")) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(convertB64toImage(PFERecyclerViewAdapter.getThumbnail()), 0,
                    convertB64toImage(PFERecyclerViewAdapter.getThumbnail()).length));
        }
        else {
            TextView posterLabel = (TextView) findViewById(R.id.poster_label);
            posterLabel.setText("Poster : No Poster");
            buttonFull.setEnabled(false);
        }
        buttonFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFullB64("");
                try {
                    Intent intent = getIntent();
                    int projectId = intent.getIntExtra("projectId", 1000);
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(v.getContext());
                    String url = RequestModel.getPoster(LoginActivity.getUsername(),PlaceholderFragmentPfe.getProjectList().get(projectId).getProjectId(), "FLB64", LoginActivity.getToken());

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.contains("Invalid Credentials")){
                                        setFullB64("No Poster");
                                    }
                                    else{
                                        setFullB64(response);
                                    }
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
                try {
                    Thread.sleep(7000);
                }
                catch (Exception e){

                }
                Intent intent = new Intent(PfeDetailsActivity.this, PosterFullActivity.class);
                startActivity(intent);
            }
        });
    }

    public byte[] convertB64toImage(String base64){

        byte[] imageAsBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);

        return  imageAsBytes;

    }

    public static String getFullB64(){
        return fullB64;
    }

    public static void setFullB64(String fullB64){
        PfeDetailsActivity.fullB64 = fullB64;
    }
}
