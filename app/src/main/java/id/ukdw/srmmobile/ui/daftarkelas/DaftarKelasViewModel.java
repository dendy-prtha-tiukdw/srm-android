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
                .listKelasGet( )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<List<KelasResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println();

                    }

                    @Override
                    public void onNext(ResponseWrapper<List<KelasResponse>> listResponseWrapper) {
//                        for (KelasResponse kelasresponse : listResponseWrapper.getData()) {
//
//                            System.out.println(kelasresponse.getNamaMataKuliah());
//                            System.out.println(kelasresponse.getHari() );
//                            System.out.println(kelasresponse.getSesi() );
////                            itemList.add( new RecyclerViewModelKelas( kelasresponse.getNamaMataKuliah()+ " " + kelasresponse.getGroup(), "" ) );
//
//                        }
//                        KelasResponse kelasResponse = (KelasResponse) listResponseWrapper.getData();
                        System.out.println("test nama mattkul di view model :  " +listResponseWrapper.getData().get( 0 ).getNamaMatakuliah());
                        System.out.println("test jam di view model : " +listResponseWrapper.getData().get( 0 ).getJam());
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
