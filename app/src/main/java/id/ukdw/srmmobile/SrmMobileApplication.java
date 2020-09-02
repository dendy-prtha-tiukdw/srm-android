package id.ukdw.srmmobile;

import android.app.Application;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import javax.inject.Inject;

import id.ukdw.srmmobile.di.component.AppComponent;
import id.ukdw.srmmobile.di.component.DaggerAppComponent;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:20
 * <p>
 * Description : SrmMobileApplication
 */
public class SrmMobileApplication extends Application {

    public AppComponent appComponent;

    private GoogleSignInClient mGoogleSignInClient;

    @Inject
    GoogleSignInOptions googleSignInOptions;

    @Override
    public void onCreate() {
        super.onCreate();


        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

        appComponent.inject(this);

        /*
        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);*/
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    public GoogleSignInClient getGoogleSignInClient(){
        return mGoogleSignInClient;
    }
}
