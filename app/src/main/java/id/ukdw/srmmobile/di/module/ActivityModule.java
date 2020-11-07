package id.ukdw.srmmobile.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import dagger.Module;
import dagger.Provides;
import id.ukdw.srmmobile.ViewModelProviderFactory;
import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.pengumumankelas.DetailKelasPengumumanViewModel;
import id.ukdw.srmmobile.ui.detailkelas.DetailKelasViewModel;
import id.ukdw.srmmobile.ui.pengumumankelas.detailpengumumankelas.DetailPengumumanKelasViewModel;
import id.ukdw.srmmobile.ui.kegiatankelas.detailkegiatankelas.DetailKegiatanKelasViewModel;
import id.ukdw.srmmobile.ui.kegiatankelas.DetailkelasLihatKegiatanViewModel;
import id.ukdw.srmmobile.ui.home.HomeViewModel;
import id.ukdw.srmmobile.ui.login.LoginViewModel;
import id.ukdw.srmmobile.ui.splash.SplashViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.di.module
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:14
 * <p>
 * Description : ActivityModule
 */
@Module
public class ActivityModule {
    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<LoginViewModel> supplier = () -> new LoginViewModel( dataManager, schedulerProvider, googleSignInClient );
        ViewModelProviderFactory<LoginViewModel> factory = new ViewModelProviderFactory<>( LoginViewModel.class, supplier );
        return new ViewModelProvider( activity, factory ).get( LoginViewModel.class );
    }

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<SplashViewModel> supplier = () -> new SplashViewModel( dataManager, schedulerProvider, googleSignInClient );
        ViewModelProviderFactory<SplashViewModel> factory = new ViewModelProviderFactory<>( SplashViewModel.class, supplier );
        return new ViewModelProvider( activity, factory ).get( SplashViewModel.class );
    }

    @Provides
    HomeViewModel provideHomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<HomeViewModel> supplier = () -> new HomeViewModel( dataManager, schedulerProvider, googleSignInClient );
        ViewModelProviderFactory<HomeViewModel> factory = new ViewModelProviderFactory<>( HomeViewModel.class, supplier );
        return new ViewModelProvider( activity, factory ).get( HomeViewModel.class );
    }

    @Provides
    DetailKelasViewModel provideDetailKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<DetailKelasViewModel> supplier = () -> new DetailKelasViewModel( dataManager, schedulerProvider, googleSignInClient );
        ViewModelProviderFactory<DetailKelasViewModel> factory = new ViewModelProviderFactory<>( DetailKelasViewModel.class, supplier );
        return new ViewModelProvider( activity, factory ).get( DetailKelasViewModel.class );
    }

    @Provides
    DetailKelasPengumumanViewModel provideDetailKelasPengumumanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<DetailKelasPengumumanViewModel> supplier = () -> new DetailKelasPengumumanViewModel( dataManager, schedulerProvider, googleSignInClient );
        ViewModelProviderFactory<DetailKelasPengumumanViewModel> factory = new ViewModelProviderFactory<>( DetailKelasPengumumanViewModel.class, supplier );
        return new ViewModelProvider( activity, factory ).get( DetailKelasPengumumanViewModel.class );
    }

    @Provides
    DetailPengumumanKelasViewModel provideAddPengumumanKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<DetailPengumumanKelasViewModel> supplier = () -> new DetailPengumumanKelasViewModel(dataManager,schedulerProvider,googleSignInClient);
        ViewModelProviderFactory<DetailPengumumanKelasViewModel> factory = new ViewModelProviderFactory<>( DetailPengumumanKelasViewModel.class, supplier );
        return new ViewModelProvider( activity, factory ).get( DetailPengumumanKelasViewModel.class );
    }

    @Provides
    DetailkelasLihatKegiatanViewModel provideDetailKelasLihatKegiatanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient){
        Supplier<DetailkelasLihatKegiatanViewModel> supplier = () -> new DetailkelasLihatKegiatanViewModel(dataManager,schedulerProvider,googleSignInClient);
        ViewModelProviderFactory<DetailkelasLihatKegiatanViewModel> factory = new ViewModelProviderFactory<>( DetailkelasLihatKegiatanViewModel.class, supplier );
        return new ViewModelProvider( activity,factory ).get( DetailkelasLihatKegiatanViewModel.class );
    }

    @Provides
    DetailKegiatanKelasViewModel provideDetailKegiatanKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient){
        Supplier<DetailKegiatanKelasViewModel> supplier = () -> new DetailKegiatanKelasViewModel(dataManager,schedulerProvider,googleSignInClient);
        ViewModelProviderFactory<DetailKegiatanKelasViewModel> factory = new ViewModelProviderFactory<>( DetailKegiatanKelasViewModel.class, supplier );
        return  new ViewModelProvider( activity,factory ).get( DetailKegiatanKelasViewModel.class );
    }

}
