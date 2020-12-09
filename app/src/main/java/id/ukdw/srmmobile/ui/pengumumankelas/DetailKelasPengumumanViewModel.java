package id.ukdw.srmmobile.ui.pengumumankelas;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.util.List;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.PengumumanDetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.PengumumanDetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DetailKelasPengumumanViewModel extends BaseViewModel<DetailKelasPengumumanNavigator> {
    public DetailKelasPengumumanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super( dataManager, schedulerProvider, googleSignInClient );
    }

    public void getDetailKelasListPengumuman (String Matkul, String Group, String Semester, String tahunAjaran){
        getDataManager().getPengumumanApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .getPengumumanDetailKelas( new PengumumanDetailKelasRequest( Group,Matkul,Semester,tahunAjaran ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<List<PengumumanDetailKelasResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<List<PengumumanDetailKelasResponse>> listResponseWrapper) {
                        getNavigator().onGetListDetailKelasPengumuman( listResponseWrapper.getData() );
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
