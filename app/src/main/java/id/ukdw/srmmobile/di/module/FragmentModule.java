package id.ukdw.srmmobile.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import id.ukdw.srmmobile.ViewModelProviderFactory;
import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseFragment;
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
    ProfileViewModel provideAboutViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<ProfileViewModel> supplier = () -> new ProfileViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<ProfileViewModel> factory = new ViewModelProviderFactory<>(ProfileViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ProfileViewModel.class);
    }

    @Provides
    DaftarKelasViewModel provideDaftarKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<DaftarKelasViewModel> supplier = () -> new DaftarKelasViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<DaftarKelasViewModel> factory = new ViewModelProviderFactory<>(DaftarKelasViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(DaftarKelasViewModel.class);
    }

    @Provides
    PengumumanViewModel providePengumumanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<PengumumanViewModel> supplier = () -> new PengumumanViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<PengumumanViewModel> factory = new ViewModelProviderFactory<>(PengumumanViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(PengumumanViewModel.class);
    }
}
