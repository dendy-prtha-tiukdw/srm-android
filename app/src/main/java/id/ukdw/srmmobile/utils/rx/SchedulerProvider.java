package id.ukdw.srmmobile.utils.rx;

import io.reactivex.Scheduler;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.utils.rx
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 15:56
 * <p>
 * Description : SchedulerProvider
 */
public interface SchedulerProvider {
    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
