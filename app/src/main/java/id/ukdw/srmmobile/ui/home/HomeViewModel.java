package id.ukdw.srmmobile.ui.home;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.home
 * <p>
 * User: dendy
 * Date: 03/09/2020
 * Time: 10:45
 * <p>
 * Description : HomeViewModel
 */
public class HomeViewModel extends BaseViewModel<HomeNavigator> {
    private static final String TAG = HomeViewModel.class.getSimpleName();

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
