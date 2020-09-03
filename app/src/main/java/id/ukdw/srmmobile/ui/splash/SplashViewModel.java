package id.ukdw.srmmobile.ui.splash;

import android.util.Log;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.LoginRequest;
import id.ukdw.srmmobile.data.model.api.request.RefreshAccessTokenRequest;
import id.ukdw.srmmobile.data.model.api.response.LoginResponse;
import id.ukdw.srmmobile.data.model.api.response.RefreshAccessTokenResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.data.model.network.RetrofitBuilder;
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
        setIsLoading(false);
        int loggedInMode = getDataManager().getCurrentUserLoggedInMode();
        if (loggedInMode == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getNavigator().openLoginActivity();
        } else {
            String refreshToken = getDataManager().getRefreshToken();

            //safeguard. if refresh token null then we ask user to relogin.
            if (refreshToken == null) {
                getNavigator().openLoginActivity();
                return;
            }
            RetrofitBuilder.getAuthApi().
                    refreshAccessTokenPost(new RefreshAccessTokenRequest(refreshToken)).
                    enqueue(new Callback<ResponseWrapper<RefreshAccessTokenResponse>>() {
                        @Override
                        public void onResponse(Call<ResponseWrapper<RefreshAccessTokenResponse>> call,
                                               Response<ResponseWrapper<RefreshAccessTokenResponse>> response) {
                            if (response.body() != null) {
                                RefreshAccessTokenResponse refreshAccessTokenResponse = response.body().getData();
                                //safe to shared preferances
                                getDataManager().updateTokenInfo(
                                        refreshAccessTokenResponse.getAccessToken(),
                                        refreshAccessTokenResponse.getIdToken()
                                );
                                setIsLoading(false);
                                getNavigator().openMainActivity();
                                Log.i(TAG, "onResponse: " + response.body().getData());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseWrapper<RefreshAccessTokenResponse>> call, Throwable t) {
                            setIsLoading(false);
                        }
                    });

        }
    }
}
