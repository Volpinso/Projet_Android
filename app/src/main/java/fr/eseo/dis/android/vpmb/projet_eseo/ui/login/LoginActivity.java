package fr.eseo.dis.android.vpmb.projet_eseo.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;
import fr.eseo.dis.android.vpmb.models.Juries;
import fr.eseo.dis.android.vpmb.models.Liprj;
import fr.eseo.dis.android.vpmb.models.Logon;
import fr.eseo.dis.android.vpmb.models.Myjur;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.ComMemberActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.JuryMemberActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentJury;

import static fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentPfe.getAppContext;

public class LoginActivity extends AppCompatActivity {

    private List<Projects> projectListBDD = new ArrayList<>();
    private static List<Juries> myJuriesList = new ArrayList<>();

    private LoginViewModel loginViewModel;
    private static String token;
    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginActivity.username = username;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        LoginActivity.token = token;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    try {
                        loginViewModel.login(usernameEditText.getText().toString(),
                                passwordEditText.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                try {
                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url = RequestModel.loginRequest(usernameEditText.getText().toString(), passwordEditText.getText().toString());

                    // Request a string response from the provided URL.
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    //textView.setText("Response: " + response.toString());

                                    Gson gson = new Gson();
                                    Logon responseModel = gson.fromJson(String.valueOf(response),
                                            Logon.class);

                                    if(responseModel.getError()!=null && responseModel.getToken()==null){
                                        showLoginFailed(AppCompatActivity.RESULT_CANCELED);
                                    }else{
                                        try {
                                            juryRequest(usernameEditText.getText().toString(), responseModel.getToken());
                                            setToken(responseModel.getToken());
                                            setUsername(usernameEditText.getText().toString());
                                            loginViewModel.login(usernameEditText.getText().toString(),
                                                    passwordEditText.getText().toString());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    System.out.println("error");

                                }
                            });
                    queue.add(jsonObjectRequest);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        if(!model.getDisplayName().equals("jpo")) {
            Intent intent = new Intent(LoginActivity.this, JuryMemberActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(LoginActivity.this, ComMemberActivity.class);
            startActivity(intent);

            AppDataBase db = AppDataBase.getAppDatabase(this.getApplicationContext());
            boolean d = db.isOpen();
            Log.d("database",String.valueOf(d));
            db.pseudoJuryDAO().insert(new PseudoJury(1));
        }
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        String wrongPassword = getString(R.string.wrong_password);
        Toast.makeText(getApplicationContext(), wrongPassword, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public static List<Juries> getJuryList() {
        return myJuriesList;
    }

    public static void setJuryList(List<Juries> myJuriesList) {
        LoginActivity.myJuriesList = myJuriesList;
    }

    public void juryRequest(String username, String token){
        try {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = RequestModel.getMyJuriesRequest(username, token);
            System.out.println(url);
            // Request a string response from the provided URL.
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            Myjur responseModel = gson.fromJson(String.valueOf(response),
                                    Myjur.class);
                            for(int i = 0 ; i < responseModel.getJuries().length; i++){
                                myJuriesList.add(responseModel.getJuries()[i]);
                            }
                            setJuryList(myJuriesList);
                            System.out.println(getJuryList());

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            myJuriesList = null;
                            System.out.println("error");

                        }
                    });
            queue.add(jsonObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            Thread.sleep(3000);
        }
        catch (Exception e){

        }
    }
}
