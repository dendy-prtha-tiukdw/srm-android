package id.ukdw.srmmobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivitySignupBinding;
import id.ukdw.srmmobile.data.model.network.ResourceProvider;
import id.ukdw.srmmobile.viewmodels.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {
    SignInButton SignUp;
    private SignUpViewModel signUpViewModel;
    private ActivitySignupBinding binding;
    private static final int RC_GET_AUTH_CODE = 9003;
    private GoogleSignInClient mGoogleSignInClient;
    EditText nomorInduk;
    String nomorIndukTest;
    String radio;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private ResourceProvider mResourceProvider;
    public ResourceProvider getResourceProvider() {
        if (mResourceProvider == null)
            mResourceProvider = new ResourceProvider(this);

        return mResourceProvider;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpViewModel = new SignUpViewModel( this );
        binding = DataBindingUtil.setContentView( SignUpActivity.this, R.layout.activity_signup);
        binding.setLifecycleOwner( this );
        binding.setSignUpViewModel( signUpViewModel );

        SignUp = findViewById(R.id.sign_up_button);
        nomorInduk = findViewById( R.id.editTextNIM );
        radioGroup = findViewById(R.id.radioGroup);



        signUpViewModel.validateServerClientID();


        String serverClientId = getString( R.string.server_client_id );
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestScopes( new Scope( Scopes.DRIVE_APPFOLDER ) )
                .requestServerAuthCode( serverClientId )
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( this, gso );

        SignUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomorIndukTest = nomorInduk.getText().toString();
                Intent signInIntent1 = mGoogleSignInClient.getSignInIntent();
                startActivityForResult( signInIntent1, RC_GET_AUTH_CODE );

            }
        } );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        radio = (String) radioButton.getText();
        if (requestCode == RC_GET_AUTH_CODE) {
            // [START get_auth_code]
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent( data );
            signUpViewModel.prosesAuthCode(task,nomorIndukTest,radio);

            // [END get_auth_code]
            signUpViewModel.getStatus().observe( this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    //Intent toLogin = new Intent( SignUpActivity.this, LoginActivity.class );
                    //startActivity( toLogin );
                }
            } );

        }
    }


}
