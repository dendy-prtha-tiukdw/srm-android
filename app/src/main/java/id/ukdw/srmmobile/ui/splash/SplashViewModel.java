package id.ukdw.srmmobile.ui.splash;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.RefreshAccessTokenRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdateFcmRequest;
import id.ukdw.srmmobile.data.model.api.response.ProfilResponse;
import id.ukdw.srmmobile.data.model.api.response.RefreshAccessTokenResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.splash
 * <p>
 * User: dendy
 * Date: 03/09/2020
 * Time: 10:25
 * <p>
 * Description : SplashViewModel
 */
public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    private String TAG = SplashViewModel.class.getSimpleName();

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);
    }

    public void decideNextActivity() {

        int loggedInMode = getDataManager().getCurrentUserLoggedInMode();
        if (loggedInMode == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getNavigator().openLoginActivity();
        } else {
            String refreshToken = getDataManager().getCurrentRefreshToken();

            //safeguard. if refresh token null then we ask user to relogin.
            if (refreshToken == null) {
                getDataManager().clearUserInfo();
                getNavigator().openLoginActivity();
                return;
            }
            getDataManager()
                    .getAuthApi().refreshAccessTokenPost(new RefreshAccessTokenRequest(refreshToken))
                    .subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui()).
                    subscribe(new Observer<ResponseWrapper<RefreshAccessTokenResponse>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            setIsLoading(true);
                        }

                        @Override
                        public void onNext(ResponseWrapper<RefreshAccessTokenResponse> refreshAccessTokenResponseResponseWrapper) {
                            RefreshAccessTokenResponse refreshAccessTokenResponse = refreshAccessTokenResponseResponseWrapper.getData();
                            //safe to shared preferances
                            getDataManager().updateTokenInfo(
                                    refreshAccessTokenResponse.getAccessToken(),
                                    refreshAccessTokenResponse.getIdToken()
                            );
                            getFcmToken();
                            getNavigator().openMainActivity();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage());
                            getNavigator().openLoginActivity();
                        }

                        @Override
                        public void onComplete() {
                            //setIsLoading(false);
                        }
                    });
        }
    }

    private void getFcmToken() {
        FirebaseInstanceId.getInstance()
                .getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }

                    // Get new Instance ID token
                    String token = task.getResult().getToken();

                    // Log and toast
                    Log.d(TAG, "InstanceID Token: " + token);
                    updateFcmToken(token);
                });
    }

    private void updateFcmToken(String fcmToken) {
        getDataManager().getUserApi(getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken())
                .updateFcmToken(new UpdateFcmRequest(fcmToken))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<Boolean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<Boolean> fcmResponseWrapper) {
                        Log.d(TAG, "InstanceID Token: " + fcmResponseWrapper.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        setIsLoading(false);
                    }
                });
    }
}
