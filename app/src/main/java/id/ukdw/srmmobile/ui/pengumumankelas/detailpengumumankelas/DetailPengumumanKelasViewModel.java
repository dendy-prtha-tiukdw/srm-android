package id.ukdw.srmmobile.ui.pengumumankelas.detailpengumumankelas;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.AddPengumumanRequest;
import id.ukdw.srmmobile.data.model.api.request.DeletePengumumanKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdatePengumumanKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DetailPengumumanKelasViewModel extends BaseViewModel<DetailPengumumanKelasNavigator> {
    private String TAG = DetailPengumumanKelasViewModel.class.getSimpleName();

    public DetailPengumumanKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);
    }

    public void addPengumumanKelas(String group,String judulMatakuliah, String namaMatkul, String pengumuman, String semester, String tahunAjaran) {
        getDataManager().getPengumumanApi(getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken())
                .setAddPengumuman(new AddPengumumanRequest(group,judulMatakuliah, namaMatkul, pengumuman, semester, tahunAjaran))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> response) {
                        Log.i(TAG, "onNext: " + response);
                        getNavigator().onSuccessAddPengumuman(response.getData());

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

    public void UpdatePengumumanKelas(String id, String judulPengumumanKelas, String isiPengumumanKelas){
        getDataManager().getPengumumanApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .setUpdatePengumuman( new UpdatePengumumanKelasRequest( id,judulPengumumanKelas,isiPengumumanKelas ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> response) {
                        getNavigator().onSuccessUpdatePengumuman(response.getData());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );

    }
    public void deletePengumumanKelas(String id){
        getDataManager().getPengumumanApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .setDeletePengumuman( new DeletePengumumanKelasRequest( id ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> response) {
                        getNavigator().onSuccessDeletePengumuman(response.getData());

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
