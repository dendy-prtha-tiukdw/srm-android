package id.ukdw.srmmobile.ui.login;

import android.util.Log;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.LoginRequest;
import id.ukdw.srmmobile.data.model.api.response.LoginResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }

    public void onGoogleLoginClick(String serverAuthCode) {
        setIsLoading(true);
        getDataManager().getAuthApi()
                .loginPost(new LoginRequest("google", serverAuthCode)).
                enqueue(new Callback<ResponseWrapper<LoginResponse>>() {
                    @Override
                    public void onResponse(Call<ResponseWrapper<LoginResponse>> call,
                                           Response<ResponseWrapper<LoginResponse>> response) {
                        /*todo : add flow control to check if code is OK or not. then extract the body to further process
                         *  Need to check if refresh token is exist. If not user need to relogin by invalidate using access token*/
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body().getData();
                            //safe to shared preferances
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
                            getNavigator().openHomeActivity();
                            Log.i(TAG, "onResponse: " + response.body().getData());
                        }else {
                            Log.e(TAG, "onResponse: " + response.message());
                        }
                        setIsLoading(false);
                    }

                    @Override
                    public void onFailure(Call<ResponseWrapper<LoginResponse>> call, Throwable t) {
                        setIsLoading(false);
                    }
                });
    }
}
