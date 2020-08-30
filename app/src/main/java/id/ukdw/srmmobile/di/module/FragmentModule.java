package id.ukdw.srmmobile.di.module;

import dagger.Module;
import id.ukdw.srmmobile.ui.base.BaseFragment;

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
}
