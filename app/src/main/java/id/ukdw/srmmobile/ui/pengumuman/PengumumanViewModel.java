package id.ukdw.srmmobile.ui.pengumuman;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.pengumuman
 * <p>
 * User: dendy
 * Date: 21/09/2020
 * Time: 15:37
 * <p>
 * Description : PengumumanViewModel
 */
public class PengumumanViewModel extends BaseViewModel<PengumumanNavigator> {
    public PengumumanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
