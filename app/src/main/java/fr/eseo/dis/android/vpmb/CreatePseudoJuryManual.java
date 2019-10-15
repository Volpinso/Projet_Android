package fr.eseo.dis.android.vpmb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.DataConverter;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.models.Liprj;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentComJury;

public class CreatePseudoJuryManual extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pseudo_jury_manual);



    }
}
