package fr.eseo.dis.android.vpmb.projet_eseo;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.MyPFERecyclerViewAdapter;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentPfe;

public class MyPfeDetailsActivity extends AppCompatActivity {

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
            supervisor.setText(getString(R.string.Tutor) + " " + PlaceholderFragmentPfe.getProjectList().get(projectId).getSupervisor().getSurname() + " "
                    + PlaceholderFragmentPfe.getProjectList().get(projectId).getSupervisor().getForename());
        } else {
            supervisor.setText(getString(R.string.Tutor) + " " + R.string.NoSupervisor);
        }

        TextView team = (TextView) findViewById(R.id.team_label);
        if (PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents() != null) {
           String equipe = getString(R.string.Team) + " ";
           for (int i =0 ; i < PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents().length - 1; i++){
               equipe = equipe + PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents()[i].getSurname() + " " +
                       PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents()[i].getForename() + ", ";
           }
            equipe = equipe + PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents()[PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents().length -1].getSurname() + " " +
                    PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents()[PlaceholderFragmentPfe.getProjectList().get(projectId).getStudents().length -1].getForename();
           team.setText(equipe);
        } else {
            team.setText(getString(R.string.Team) + " " + getString(R.string.NoTeam));
        }

        ImageView imageView = (ImageView) this.findViewById(R.id.poster_image);

        if(!MyPFERecyclerViewAdapter.getThumbnail().equals("No Poster")) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(convertB64toImage(MyPFERecyclerViewAdapter.getThumbnail()), 0,
                    convertB64toImage(MyPFERecyclerViewAdapter.getThumbnail()).length));
        }
        else {
            TextView posterLabel = (TextView) findViewById(R.id.poster_label);
            posterLabel.setText("Poster : " + getString(R.string.NoPoster));
            buttonFull.setEnabled(false);
        }
        buttonFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = getIntent();
                    int projectId = intent.getIntExtra("projectId", 1000);
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(v.getContext());
                    String url = RequestModel.getPoster(LoginActivity.getUsername(), projectId, "FLB64", LoginActivity.getToken());
                    System.out.println(url);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.contains("Invalid Credentials")){
                                        setFullB64("No Poster");
                                    }
                                    else{
                                        System.out.println(response);
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
                Intent intent = new Intent(MyPfeDetailsActivity.this, PosterFullActivity.class);
                startActivity(intent);
            }
        });
    }

    public byte[] convertB64toImage(String base64){

        byte[] imageAsBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);

        return  imageAsBytes;

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pfedetail_menu, menu);
        return true;
    }

    public static String getFullB64(){
        return fullB64;
    }

    public static void setFullB64(String fullB64){
        MyPfeDetailsActivity.fullB64 = fullB64;
    }
}
