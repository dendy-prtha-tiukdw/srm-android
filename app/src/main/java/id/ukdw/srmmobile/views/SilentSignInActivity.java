package id.ukdw.srmmobile.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityLoginBinding;
import id.ukdw.srmmobile.viewmodels.LoginViewModel;

public class SilentSignInActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    String serverClientId = getString( R.string.server_client_id );
    private GoogleSignInClient mGoogleSignInClient;
    private ActivityLoginBinding binding;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate( savedInstanceState );
            setContentView( R.layout.splash);
            loginViewModel = ViewModelProviders.of( this ).get( LoginViewModel.class );
            binding = DataBindingUtil.setContentView( SilentSignInActivity.this, R.layout.activity_login);
            binding.setLifecycleOwner( this );
            binding.setLoginViewModel( loginViewModel );
            loginViewModel.validateServerClientID();

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                    .requestScopes( new Scope( Scopes.DRIVE_APPFOLDER ) )
                    .requestServerAuthCode( serverClientId )
                    .requestEmail()
                    .build();

            loginViewModel.validateServerClientID();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            silentSignIn();

        }

        private void silentSignIn(){
            mGoogleSignInClient.silentSignIn().addOnCompleteListener( this, new OnCompleteListener<GoogleSignInAccount>() {
                @Override
                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                    loginViewModel.prosesAuthCode( task );
                }
            } );
        }
    }
