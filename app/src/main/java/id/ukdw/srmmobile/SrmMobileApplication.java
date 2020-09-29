package id.ukdw.srmmobile;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

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

    private static final String TAG = SrmMobileApplication.class.getSimpleName();
    public AppComponent appComponent;

    private GoogleSignInClient mGoogleSignInClient;

    @Inject
    GoogleSignInOptions googleSignInOptions;

    public static SrmMobileApplication get(Context context) {
        return (SrmMobileApplication) context.getApplicationContext();
    }

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

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        Log.d(TAG, "InstanceID Token: "+token);
                    }
                });
    }

    public GoogleSignInClient getGoogleSignInClient(){
        return mGoogleSignInClient;
    }

    public AppComponent getComponent(){
        return appComponent;
    }
}
