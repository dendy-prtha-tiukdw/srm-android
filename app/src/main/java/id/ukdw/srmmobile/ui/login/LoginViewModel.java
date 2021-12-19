package id.ukdw.srmmobile.ui.login;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.LoginRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdateFcmRequest;
import id.ukdw.srmmobile.data.model.api.response.LoginResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.utils.AppConstants;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.views.login
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 23:04
 * <p>
 * Description : LoginViewModel
 */
public class LoginViewModel extends BaseViewModel<LoginNavigator> {
    private static final String TAG = LoginViewModel.class.getSimpleName();

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);

    }

    public void onGoogleLoginClick(String serverAuthCode) {
        getDataManager().getAuthApi()
                .loginPost(new LoginRequest(serverAuthCode, AppConstants.CLIENT_TYPE))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<LoginResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setIsLoading(true);
                    }
                    @Override
                    public void onNext(
                            ResponseWrapper<LoginResponse> loginResponseResponseWrapper) {
                        LoginResponse loginResponse = loginResponseResponseWrapper.getData();
                        getDataManager().updateUserInfo(
                                loginResponse.getAccessToken(),
                                loginResponse.getIdToken(),
                                loginResponse.getRefreshToken(),
                                loginResponse.getNomorInduk(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_GOOGLE,
                                loginResponse.getNama(),
                                loginResponse.getEmail(),
                                loginResponse.getImageUrl(),
                                loginResponse.getRole()
                        );
                        getFcmToken();
                        getNavigator().openHomeActivity();
                    }
                    @Override
                    public void onError(Throwable e) {
                        getNavigator().handleError(e);
                    }
                    @Override
                    public void onComplete() {
                        setIsLoading(false);
                    }
                });
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
                    String token = Objects.requireNonNull(task.getResult()).getToken();

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
                        //setIsLoading(false);
                    }
                });
    }
}
