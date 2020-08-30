package id.ukdw.srmmobile.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import id.ukdw.srmmobile.SrmMobileApplication;
import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.di.module.AppModule;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.di.component
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:21
 * <p>
 * Description : AppComponent
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(SrmMobileApplication app);

    DataManager getDataManager();

    SchedulerProvider getSchedulerProvider();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}