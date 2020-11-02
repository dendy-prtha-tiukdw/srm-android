package id.ukdw.srmmobile.ui.detailkelas.addpengumumankelas;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.AddPengumumanRequest;
import id.ukdw.srmmobile.data.model.api.request.PengumumanDetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.AddPengumumanResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AddPengumumanKelasViewModel extends BaseViewModel<AddPengumumanKelasNavigator> {
    public AddPengumumanKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super( dataManager, schedulerProvider, googleSignInClient );
    }

    public void addPengumumanKelas(String group,String judulPengumuman, String namaMatkul, String pengumuman, String semester, String tahunAjaran) {
        getDataManager().getPengumumanApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .setAddPengumuman( new AddPengumumanRequest( group,judulPengumuman, namaMatkul, pengumuman, semester, tahunAjaran ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<AddPengumumanResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<AddPengumumanResponse> addPengumumanResponseResponseWrapper) {
                        System.out.println("TEST MASUK ON NEXT SEBLUM NAVIGATOR");
                        getNavigator().onSuccessAddPengumuman();
                        System.out.println("TEST MASUK ON NEXT SESUDAH NAVIGATOR");


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
