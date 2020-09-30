package id.ukdw.srmmobile.ui.detailkelas;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;

public class DetailKelasViewModel extends BaseViewModel {
    public DetailKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);
    }

    public void getDetailKelas(String Matkul, String Group, String Semester, String tahunAjaran) {
        getDataManager().getAuthApi();
    }
}
