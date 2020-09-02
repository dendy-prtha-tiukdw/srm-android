package id.ukdw.srmmobile.utils.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.utils.rx
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 15:59
 * <p>
 * Description : implement SchedulerProvider
 */
public class AppSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
