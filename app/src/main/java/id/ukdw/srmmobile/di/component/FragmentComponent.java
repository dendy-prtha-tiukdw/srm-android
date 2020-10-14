package id.ukdw.srmmobile.di.component;

import dagger.Component;
import id.ukdw.srmmobile.di.module.FragmentModule;
import id.ukdw.srmmobile.di.scope.FragmentScope;
import id.ukdw.srmmobile.ui.calendar.KalenderFragment;
import id.ukdw.srmmobile.ui.daftarkelas.DaftarKelasFragment;
import id.ukdw.srmmobile.ui.pengumuman.PengumumanFragment;
import id.ukdw.srmmobile.ui.profile.ProfileFragment;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.di.component
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:35
 * <p>
 * Description : FragmentComponent
 */

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(DaftarKelasFragment daftarKelasFragment);

    void inject(ProfileFragment profileFragment);

    void inject(PengumumanFragment pengumumanFragment);

    void inject(KalenderFragment kalenderFragment);
}
