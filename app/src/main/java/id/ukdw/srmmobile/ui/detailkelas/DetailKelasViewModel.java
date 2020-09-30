package id.ukdw.srmmobile.ui.detailkelas;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;

public class DetailKelasViewModel extends BaseViewModel {
    public DetailKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super( dataManager, schedulerProvider );
    }

    public void getDetailKelas (String Matkul, String Group, String Semester,String tahunAjaran){
        getDataManager().getAuthApi();
    }
}
