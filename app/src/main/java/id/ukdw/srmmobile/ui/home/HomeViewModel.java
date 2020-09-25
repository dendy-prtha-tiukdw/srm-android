package id.ukdw.srmmobile.ui.home;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.LogoutRequest;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.home
 * <p>
 * User: dendy
 * Date: 03/09/2020
 * Time: 10:45
 * <p>
 * Description : HomeViewModel
 */
public class HomeViewModel extends BaseViewModel<HomeNavigator> {
    private static final String TAG = HomeViewModel.class.getSimpleName();

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void logOut() {
        getDataManager().getAuthApi()
                .signOutPost(new LogoutRequest(getDataManager().getCurrentAccessToken()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setIsLoading(true);
                    }

                    @Override
                    public void onNext(ResponseWrapper responseWrapper) {
                        getDataManager().clearUserInfo();
                        getNavigator().openLoginActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getNavigator().handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setUpSideNavProfile() {
        getNavigator().onSetNavProfile(getDataManager().getCurrentUserName(),
                getDataManager().getCurrentUserEmail(), getDataManager().getCurrentUserImageURL());
    }
}
