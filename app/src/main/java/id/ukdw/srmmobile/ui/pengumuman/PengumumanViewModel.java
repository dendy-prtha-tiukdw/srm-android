package id.ukdw.srmmobile.ui.pengumuman;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.util.List;

import id.ukdw.srmmobile.data.DataManager;
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
    public PengumumanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);
    }
    public void getListPengumuman(){
        getDataManager().getUserApi( getDataManager().getCurrentAccessToken(),getDataManager().getCurrentRefreshToken() )
                .getPengumumanList()
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<List<PengumumanResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<List<PengumumanResponse>> listResponseWrapper) {
                        getNavigator().onGetListPengumuman(listResponseWrapper.getData());


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }


}
