package id.ukdw.srmmobile.di.component;

import dagger.Component;
import id.ukdw.srmmobile.di.module.FragmentModule;
import id.ukdw.srmmobile.di.scope.FragmentScope;

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
}
