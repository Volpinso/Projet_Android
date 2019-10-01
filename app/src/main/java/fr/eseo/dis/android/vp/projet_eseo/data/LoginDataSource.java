package fr.eseo.dis.android.vp.projet_eseo.data;

import android.app.Application;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import fr.eseo.dis.android.vp.projet_eseo.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource extends Application {


    public Result<LoggedInUser> login(String username, String password) {

        //try {
            // TODO: handle loggedInUser authentication
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="https://192.168.4.240/pfe/webservice.php?q=LOGON&user=" + username + "&pass="+ password;

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            //textView.setText("Response is: "+ response.substring(0,500));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //textView.setText("That didn't work!");
                }
            });
            queue.add(stringRequest);
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            username);
            return new Result.Success<>(username);
            // TODO: handle loggedInUser authentication
        //} catch (Exception e) {
        //    return new Result.Error(new IOException("Error logging in", e));
        //}
    }

    public void logout() {
        // TODO: revoke authentication
    }
}


