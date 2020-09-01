package id.ukdw.srmmobile.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import id.ukdw.srmmobile.ViewModelProviderFactory;
import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.login.LoginViewModel;
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
    LoginViewModel provideLoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<LoginViewModel> supplier = () -> new LoginViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<LoginViewModel> factory = new ViewModelProviderFactory<>(LoginViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(LoginViewModel.class);
    }
}
