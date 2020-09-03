package id.ukdw.srmmobile.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivitySplashBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.home.HomeActivity;
import id.ukdw.srmmobile.ui.login.LoginActivity;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.splash
 * <p>
 * User: dendy
 * Date: 03/09/2020
 * Time: 10:27
 * <p>
 * Description : SplashActivity
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel>
        implements SplashNavigator {

    @Override
    public int getBindingVariable() {
        return BR.splashViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = HomeActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.decideNextActivity();
    }
}
