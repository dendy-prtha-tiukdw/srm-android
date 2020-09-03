package id.ukdw.srmmobile.ui.splash;

import android.util.Log;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.RefreshAccessTokenRequest;
import id.ukdw.srmmobile.data.model.api.response.RefreshAccessTokenResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void decideNextActivity() {
        setIsLoading(true);
        int loggedInMode = getDataManager().getCurrentUserLoggedInMode();
        if (loggedInMode == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getNavigator().openLoginActivity();
        } else {
            String refreshToken = getDataManager().getRefreshToken();

            //safeguard. if refresh token null then we ask user to relogin.
            if (refreshToken == null) {
                getDataManager().clearUserInfo();
                getNavigator().openLoginActivity();
                return;
            }
            getDataManager().getAuthApi().
                    refreshAccessTokenPost(new RefreshAccessTokenRequest(refreshToken)).
                    enqueue(new Callback<ResponseWrapper<RefreshAccessTokenResponse>>() {
                        @Override
                        public void onResponse(Call<ResponseWrapper<RefreshAccessTokenResponse>> call,
                                               Response<ResponseWrapper<RefreshAccessTokenResponse>> response) {
                            //If the request was successfully delivered but there was a server problem you can check response.isSuccessful().
                            if (response.isSuccessful()) {
                                RefreshAccessTokenResponse refreshAccessTokenResponse = response.body().getData();
                                //safe to shared preferances
                                getDataManager().updateTokenInfo(
                                        refreshAccessTokenResponse.getAccessToken(),
                                        refreshAccessTokenResponse.getIdToken()
                                );
                                getNavigator().openMainActivity();
                                Log.i(TAG, "onResponse: " + response.body().getData());
                            } else {
                                // If it returns false, check response.code() and handle the error.
                                switch (response.code()) {
                                    default:
                                        Log.e(TAG, "onResponse: " + response.message());
                                        break;
                                }
                                getDataManager().clearUserInfo();
                                getNavigator().openLoginActivity();
                            }
                            setIsLoading(false);
                        }

                        @Override
                        public void onFailure(Call<ResponseWrapper<RefreshAccessTokenResponse>> call, Throwable t) {
                            //onFailure() is called if and only if there were problems with the client.
                            setIsLoading(false);
                        }
                    });

        }
    }
}
