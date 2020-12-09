package id.ukdw.srmmobile.ui.daftarkelas;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.util.List;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.DetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.DetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.KelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.daftarkelas
 * <p>
 * User: dendy
 * Date: 21/09/2020
 * Time: 15:21
 * <p>
 * Description : DaftarKelasViewModel
 */
public class DaftarKelasViewModel extends BaseViewModel<DaftarKelasNavigator> {
    public DaftarKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);
    }

    public void getListKelas() {

        getDataManager().getUserApi(getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken())
                .getListKelas()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<List<KelasResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setIsLoading(true);

                    }

                    @Override
                    public void onNext(ResponseWrapper<List<KelasResponse>> listResponseWrapper) {
                        getNavigator().updateListDaftarKelas(listResponseWrapper.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage().matches( "failed to connect .*" )){
                            getNavigator().onGetError(  );
                        }
                        else {
                            getNavigator().onServerError();
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
