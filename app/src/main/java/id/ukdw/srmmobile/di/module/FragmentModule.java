package id.ukdw.srmmobile.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import dagger.Module;
import dagger.Provides;
import id.ukdw.srmmobile.ViewModelProviderFactory;
import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.calendar.KalenderViewModel;
import id.ukdw.srmmobile.ui.daftarkelas.DaftarKelasViewModel;
import id.ukdw.srmmobile.ui.pengumuman.PengumumanViewModel;
import id.ukdw.srmmobile.ui.profile.ProfileViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.di.module
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:35
 * <p>
 * Description : FragmentModule
 */
@Module
public class FragmentModule {
    private BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Provides
    ProfileViewModel provideAboutViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<ProfileViewModel> supplier = () -> new ProfileViewModel(dataManager, schedulerProvider, googleSignInClient);
        ViewModelProviderFactory<ProfileViewModel> factory = new ViewModelProviderFactory<>(ProfileViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ProfileViewModel.class);
    }

    @Provides
    DaftarKelasViewModel provideDaftarKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<DaftarKelasViewModel> supplier = () -> new DaftarKelasViewModel(dataManager, schedulerProvider, googleSignInClient);
        ViewModelProviderFactory<DaftarKelasViewModel> factory = new ViewModelProviderFactory<>(DaftarKelasViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(DaftarKelasViewModel.class);
    }

    @Provides
    PengumumanViewModel providePengumumanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<PengumumanViewModel> supplier = () -> new PengumumanViewModel(dataManager, schedulerProvider, googleSignInClient);
        ViewModelProviderFactory<PengumumanViewModel> factory = new ViewModelProviderFactory<>(PengumumanViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(PengumumanViewModel.class);
    }

    @Provides
    KalenderViewModel provideKalenderViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        Supplier<KalenderViewModel> supplier = () -> new KalenderViewModel(dataManager,schedulerProvider,googleSignInClient);
        ViewModelProviderFactory<KalenderViewModel> factory = new ViewModelProviderFactory<KalenderViewModel>( KalenderViewModel.class, supplier );
        return new ViewModelProvider( fragment,factory ).get( KalenderViewModel.class );
    }
}
