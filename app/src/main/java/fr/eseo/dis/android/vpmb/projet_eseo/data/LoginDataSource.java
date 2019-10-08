package fr.eseo.dis.android.vpmb.projet_eseo.data;

import android.app.Application;

import fr.eseo.dis.android.vpmb.projet_eseo.data.model.LoggedInUser;

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


