package id.ukdw.srmmobile.data.local.db;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data.local.db
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 22:30
 * <p>
 * Description : AppDbHelper
 */
@Singleton
public class AppDbHelper implements DbHelper {
    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }
}
