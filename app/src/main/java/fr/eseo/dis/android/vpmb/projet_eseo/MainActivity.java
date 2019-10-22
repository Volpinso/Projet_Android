package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;

import fr.eseo.dis.android.vpmb.connexion.ConnexionManager;
import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Application PFE");

        /*try {
            ConnexionManager.trustCertificate();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }*/

        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(ConnexionManager.getSocketFactory(this.getApplicationContext()));
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


        Button button6 = (Button) findViewById(R.id.buttonVisitor);
        System.out.println(AppDataBase.getINSTANCE(MainActivity.this).pseudoJuryDAO().loadAll().isEmpty());



            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(AppDataBase.getINSTANCE(MainActivity.this).pseudoJuryDAO().loadAll().isEmpty()) {

                        String success = getString(R.string.NoPseudoJury);
                        Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(MainActivity.this, ActivityVisitorListSubjects.class);
                        startActivity(intent);
                    }
                }
            });

        Button buttonLogin = (Button) findViewById(R.id.buttonMembreESEO);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logo_eseo:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.eseo.fr"));
                startActivity(browserIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity","onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestroy()");
    }



}
