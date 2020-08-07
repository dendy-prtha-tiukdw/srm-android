package id.ukdw.srmmobile.viewmodels;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.model.User;
import id.ukdw.srmmobile.model.network.Post;
import id.ukdw.srmmobile.model.network.SrmApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.app.ActivityCompat.startActivityForResult;

/**
 * Created by Dendy Prtha on 7/28/2020.
 * description : TODO
 */

public class LoginViewModel extends ViewModel {
    public static final String TAG = "ServerAuthCodeActivity";
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    String authCode;
    private GoogleSignInClient mGoogleSignInClient;
    private MutableLiveData<User> userMutableLiveData;

    public MutableLiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void validateServerClientID() {
        String serverClientId = "527371133243-9tr4gvi7vic5g3b3p5fr10dtrgs4kvgo.apps.googleusercontent.com";
        String suffix = ".apps.googleusercontent.com";
        if (!serverClientId.trim().endsWith( suffix )) {
            String message = "Invalid server client ID in strings.xml, must end with " + suffix;
            Log.w( TAG, message );
        }
    }

    public void getAuthCode() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        prosesAuthCode( signInIntent );
    }

    public void prosesAuthCode(Intent signInIntent) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent( signInIntent );
        try {
            GoogleSignInAccount account = task.getResult( ApiException.class );
            authCode = account.getServerAuthCode();

            System.out.println( authCode );
            LoginPost();

            // TODO(developer): send code to server and exchange for access/refresh/ID tokens
        } catch (ApiException e) {
            Log.w( TAG, "Sign-in failed", e );

        }

    }

    private void LoginPost() {

    }

    public void onClick(View view) {
        User loginUser = new User( EmailAddress.getValue(), Password.getValue() );
        userMutableLiveData.setValue( loginUser );
    }


}
