package id.ukdw.srmmobile.di.component;

import dagger.Component;
import id.ukdw.srmmobile.di.module.ActivityModule;
import id.ukdw.srmmobile.di.scope.ActivityScope;
import id.ukdw.srmmobile.ui.detailkelas.DetailKelasActivity;
import id.ukdw.srmmobile.ui.detailkelas.DetailKelasPengumumanActivity;
import id.ukdw.srmmobile.ui.detailkelas.addpengumumankelas.AddPengumumanKelasActivity;
import id.ukdw.srmmobile.ui.home.HomeActivity;
import id.ukdw.srmmobile.ui.login.LoginActivity;
import id.ukdw.srmmobile.ui.splash.SplashActivity;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.di.component
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:14
 * <p>
 * Description : ActivityComponent
 */
@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(LoginActivity activity);

    void inject(HomeActivity activity);

    void inject(SplashActivity splashActivity);

    void inject(DetailKelasActivity detailKelasActivity);

    void inject(DetailKelasPengumumanActivity detailKelasPengumumanActivity);

    void inject(AddPengumumanKelasActivity addPengumumanKelasActivity);
}
