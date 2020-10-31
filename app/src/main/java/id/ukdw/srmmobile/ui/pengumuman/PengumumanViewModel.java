package id.ukdw.srmmobile.ui.pengumuman;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.util.List;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.PengumumanRequest;
import id.ukdw.srmmobile.data.model.api.response.PengumumanResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.pengumuman
 * <p>
 * User: dendy
 * Date: 21/09/2020
 * Time: 15:37
 * <p>
 * Description : PengumumanViewModel
 */
public class PengumumanViewModel extends BaseViewModel<PengumumanNavigator> {
    private String TAG = PengumumanViewModel.class.getSimpleName();

    public PengumumanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);
    }

    public void getListPengumuman(String text) {

        getDataManager().getUserApi(getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken())
                .getPengumumanList(new PengumumanRequest(text))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<List<PengumumanResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getNavigator().isLoading(true);
                    }

                    @Override
                    public void onNext(ResponseWrapper<List<PengumumanResponse>> listResponseWrapper) {
                        getNavigator().onGetListPengumuman(listResponseWrapper.getData());


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        getNavigator().isLoading(false);
                    }

                    @Override
                    public void onComplete() {
                        getNavigator().isLoading(false);
                    }
                });
    }


}
