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

    public void addPengumumanKelas(String group, String namaMatkul, String pengumuman, String semester, String tahunAjaran){
        getDataManager().getPengumumanApi("ya29.A0AfH6SMCImc3IKD-5TSqN1kkcpTUgRj9tf5A6iVKx5k1u1H2O6OtgD1Qg8wMK-hZjcurYT7NlgmGkK5a2nLNNisdCQ96d6gFcws7dreFA5hs8wuPTJMY7chWEVoVtxOU8rh-ssMQlfqcaEaC9WqViKH13Q07q0rZteoCuR4WfqhZp",
                "1//0g6lIFZmzKmbaCgYIARAAGBASNwF-L9IrfP7ugYs6wo_76Qvme3tt1ZtDF-qh89e20_uwTCQFYnWbDiSMfR1p5v6_SP0rfVZ_Vus" )
                .setAddPengumuman( new AddPengumumanRequest( group,namaMatkul,pengumuman,semester,tahunAjaran ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<AddPengumumanResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<AddPengumumanResponse> addPengumumanResponseResponseWrapper) {
                        System.out.println("test");
                        AddPengumumanResponse addPengumumanResponse = addPengumumanResponseResponseWrapper.getData();
                        getNavigator().onSuccessAddPengumuman( addPengumumanResponse.getData() );

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
