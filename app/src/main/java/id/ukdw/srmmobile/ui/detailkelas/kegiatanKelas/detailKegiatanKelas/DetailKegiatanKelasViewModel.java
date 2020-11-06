package id.ukdw.srmmobile.ui.detailkelas.kegiatanKelas.detailKegiatanKelas;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.AddKegiatanRequest;
import id.ukdw.srmmobile.data.model.api.request.DeleteKegiatanRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdateKegiatanKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DetailKegiatanKelasViewModel extends BaseViewModel<DetailKegiatanKelasNavigator> {

    public DetailKegiatanKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super( dataManager, schedulerProvider, googleSignInClient );
    }
    public void addkegiatanKelas(String group,String judulKegiatan, String kegiatan, String namaMatkul,
                                 String semester, String tahunAjaran,String tanggalBerakhir) {
        getDataManager().getKegiatanApi(getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken())
                .setAddKegiatan(new AddKegiatanRequest(group,judulKegiatan, kegiatan, namaMatkul, semester, tahunAjaran,tanggalBerakhir))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> response) {
                        getNavigator().onSuccessAddKegiatan(response.getData());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "onError: ");
                    }
                });
    }

    public void UpdatekegiatanKelas(String idKegiatan, String isComplete, String judulKegiatan, String Kegiatan, String tanggalBerakhir, String tanggalDibuat){
        getDataManager().getKegiatanApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .setUpdateKegiatan( new UpdateKegiatanKelasRequest( idKegiatan,isComplete,judulKegiatan,Kegiatan,tanggalBerakhir,tanggalDibuat ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> response) {
                        getNavigator().onSuccessUpdateKegiatan(response.getData());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );

    }
    public void deleteKegiatanKelas(String id){
        getDataManager().getKegiatanApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .setDeleteKegiatan( new DeleteKegiatanRequest( id ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> response) {
                        getNavigator().onSuccessDeleteKegiatan(response.getData());

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
