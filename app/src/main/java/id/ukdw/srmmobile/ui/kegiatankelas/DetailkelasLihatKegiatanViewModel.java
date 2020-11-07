package id.ukdw.srmmobile.ui.kegiatankelas;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.util.List;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.KegiatanDetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.KegiatanDetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DetailkelasLihatKegiatanViewModel extends BaseViewModel<DetailKelasLihatKegiatNavigator> {
    public DetailkelasLihatKegiatanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super( dataManager, schedulerProvider, googleSignInClient );
    }
    public void getDetailKelasListKegiatan(String group, String matkul, String Semester, String tahunAjaran){
        getDataManager().getKegiatanApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .getKegiatanDetailKelas( new KegiatanDetailKelasRequest( group,matkul,Semester,tahunAjaran ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<List<KegiatanDetailKelasResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<List<KegiatanDetailKelasResponse>> listResponseWrapper) {
                        getNavigator().onGetListKegiatanKelas( listResponseWrapper.getData() );

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    public boolean checkRole(){
        if (getDataManager().getCurrentUserRole().equalsIgnoreCase( "ROLE_MAHASISWA" )){
            return false;
        }
        else {
            return true;
        }
    }
}
