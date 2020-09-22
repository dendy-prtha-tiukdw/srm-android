package id.ukdw.srmmobile.ui.daftarkelas;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.daftarkelas
 * <p>
 * User: dendy
 * Date: 21/09/2020
 * Time: 15:21
 * <p>
 * Description : DaftarKelasViewModel
 */
public class DaftarKelasViewModel extends BaseViewModel<DaftarKelasNavigator> {
    public DaftarKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
