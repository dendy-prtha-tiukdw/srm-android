package id.ukdw.srmmobile.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Scope;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityMainBinding;
import id.ukdw.srmmobile.model.User;
import id.ukdw.srmmobile.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    SignInButton SignIn;
    private LoginViewModel loginViewModel;
    private ActivityMainBinding binding;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        loginViewModel = ViewModelProviders.of( this ).get( LoginViewModel.class );
        binding = DataBindingUtil.setContentView( LoginActivity.this, R.layout.activity_login );
        binding.setLifecycleOwner( this );
        binding.setLoginViewModel( loginViewModel );

        SignIn = findViewById( R.id.sign_in_button );

        loginViewModel.validateServerClientID();

        String serverClientId = getString( R.string.server_client_id );
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestScopes( new Scope( Scopes.DRIVE_APPFOLDER ) )
                .requestServerAuthCode( serverClientId )
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( this, gso );


        SignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.getAuthCode();
            }
        } );


    }

}