package id.ukdw.srmmobile.ui.login;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.LoginRequest;
import id.ukdw.srmmobile.data.model.api.response.LoginResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
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

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }

    public void onGoogleLoginClick(String serverAuthCode) {

        getDataManager().getAuthApi()
                .loginPost(new LoginRequest("google", serverAuthCode))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<LoginResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setIsLoading(true);
                    }

                    @Override
                    public void onNext(ResponseWrapper<LoginResponse> loginResponseResponseWrapper) {
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
                        getNavigator().openHomeActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getNavigator().handleError(e);
                        getNavigator().handleError(e);
                    }

                    @Override
                    public void onComplete() {
                        setIsLoading(false);
                    }
                });
    }
}
