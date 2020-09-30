package id.ukdw.srmmobile.ui.daftarkelas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.KelasRequest;
import id.ukdw.srmmobile.data.model.api.request.ProfilRequest;
import id.ukdw.srmmobile.data.model.api.response.KelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.data.model.api.response.detailkelasResponse;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
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


    public DaftarKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super( dataManager, schedulerProvider );
    }

    public void getListKelas() {

        getDataManager().getUserApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .listKelasGet()
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<List<KelasResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setIsLoading( true );

                    }

                    @Override
                    public void onNext(ResponseWrapper<List<KelasResponse>> listResponseWrapper) {

                        getNavigator().updateListDaftarKelas( listResponseWrapper.getData() );


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
