package id.ukdw.srmmobile.ui.splash;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.RefreshAccessTokenRequest;
import id.ukdw.srmmobile.data.model.api.response.RefreshAccessTokenResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
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

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void decideNextActivity() {

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
                            getNavigator().openMainActivity();

                        }

                        @Override
                        public void onError(Throwable e) {
                            getNavigator().openLoginActivity();
                        }

                        @Override
                        public void onComplete() {
                            setIsLoading(false);
                        }
                    });

        }
    }
}
