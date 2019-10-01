package fr.eseo.dis.android.vp.projet_eseo.data;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vp.projet_eseo.data.model.LoggedInUser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource extends Application{


    public Result<LoggedInUser> login(String username, String password) {

        //try {
            // TODO: handle loggedInUser authentication

            LoggedInUser user =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            username);
            return new Result.Success<>(user);
            // TODO: handle loggedInUser authentication
        //} catch (Exception e) {
        //    return new Result.Error(new IOException("Error logging in", e));
        //}
    }

    public void logout() {
        // TODO: revoke authentication
    }


}


