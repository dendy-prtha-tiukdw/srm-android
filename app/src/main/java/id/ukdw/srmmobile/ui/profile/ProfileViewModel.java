package id.ukdw.srmmobile.ui.profile;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.ProfilRequest;
import id.ukdw.srmmobile.data.model.api.response.ProfilResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends BaseViewModel<ProfileNavigator> {
    private static final String TAG = ProfileViewModel.class.getSimpleName();

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public String onFragmentProfil() {
        setIsLoading(true);
        String idToken = getDataManager().getIdToken();

        Observable<ResponseWrapper<ProfilResponse>> profilObservable = getDataManager().getAuthApi()
                .profilGet(new ProfilRequest(idToken));
        profilObservable.subscribeOn(Schedulers.io());
        profilObservable.observeOn(AndroidSchedulers.mainThread());
        profilObservable.subscribe(new Observer<ResponseWrapper<ProfilResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseWrapper<ProfilResponse> profilResponseResponseWrapper) {
                ProfilResponse profilResponse = profilResponseResponseWrapper.getData();
                getDataManager().profileUserInfo(
                        profilResponse.getNomorInduk(),
                        profilResponse.getNama(),
                        profilResponse.getEmail(),
                        profilResponse.getJenisKelamin(),
                        profilResponse.getImageProfile()
                );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return idToken;
    }
}


