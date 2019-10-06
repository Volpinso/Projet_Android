package fr.eseo.dis.android.vp.projet_eseo.ui.login;

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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import fr.eseo.dis.android.vp.models.Logon;
import fr.eseo.dis.android.vp.models.RequestModel;
import fr.eseo.dis.android.vp.projet_eseo.ActivityVisitorListSubjects;
import fr.eseo.dis.android.vp.projet_eseo.ComMemberActivity;
import fr.eseo.dis.android.vp.projet_eseo.JuryMemberActivity;
import fr.eseo.dis.android.vp.projet_eseo.MainActivity;
import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vp.projet_eseo.ui.login.LoginViewModel;
import fr.eseo.dis.android.vp.projet_eseo.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

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
                    System.out.println(url);
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
        if(!model.getDisplayName().equals("aubinseb")) {
            Intent intent = new Intent(LoginActivity.this, JuryMemberActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(LoginActivity.this, ComMemberActivity.class);
            startActivity(intent);
        }
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        String wrongPassword = getString(R.string.wrong_password);
        Toast.makeText(getApplicationContext(), wrongPassword, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
