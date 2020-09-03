package id.ukdw.srmmobile.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityLoginBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.home.HomeActivity;
import id.ukdw.srmmobile.ui.base.BaseActivity;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.login
 * <p>
 * User: dendy
 * Date: 30/08/2020
 * Time: 7:08
 * <p>
 * Description : LoginActivity
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>
        implements LoginNavigator {
    private static final int RC_GET_AUTH_CODE = 9003;
    private ActivityLoginBinding mActivityLoginBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        mActivityLoginBinding.signInButton.setOnClickListener(v -> {
            //getSrmMobileApplication().getGoogleSignInClient().getSignInIntent();
            startActivityForResult(getSrmMobileApplication().getGoogleSignInClient().getSignInIntent(), RC_GET_AUTH_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == RC_GET_AUTH_CODE) {
            try {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent( data );
                mViewModel.onGoogleLoginClick(task.getResult().getServerAuthCode());
            }catch (Exception ex){

            }

        }
    }

    @Override
    public int getBindingVariable() {
        return BR.loginViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void handleError(Throwable throwable) {
        //handle error
    }

    @Override
    public void login() {

    }

    @Override
    public void openHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
