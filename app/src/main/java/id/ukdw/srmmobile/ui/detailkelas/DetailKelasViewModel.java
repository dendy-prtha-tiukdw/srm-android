package id.ukdw.srmmobile.ui.detailkelas;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.DetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.DetailkelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DetailKelasViewModel extends BaseViewModel<DetailKelasNavigator> {
    public DetailKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super( dataManager, schedulerProvider );
    }

    public void getDetailKelas (String Matkul, String Group, String Semester,String tahunAjaran){
        getDataManager().getUserApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .detailKelas(new DetailKelasRequest( Group,Matkul,Semester,tahunAjaran ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<DetailkelasResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setIsLoading( true );
                    }

                    @Override
                    public void onNext(ResponseWrapper<DetailkelasResponse> detailkelasResponseResponseWrapper) {
                        DetailkelasResponse detailkelasResponse = detailkelasResponseResponseWrapper.getData();
                        getNavigator().onGetDetailKelasCompleted(
                                detailkelasResponse.getNamaMatakuliah(),
                                detailkelasResponse.getGroup(),
                                detailkelasResponse.getHari(),
                                detailkelasResponse.getTahunAjaran(),
                                detailkelasResponse.getSemester(),
                                detailkelasResponse.getSesi(),
                                detailkelasResponse.getNamaDosen()

                        );
                        setIsLoading( false );

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
