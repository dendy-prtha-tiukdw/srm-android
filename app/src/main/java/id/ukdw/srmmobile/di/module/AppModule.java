package id.ukdw.srmmobile.di.module;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.ukdw.srmmobile.data.AppDataManager;
import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.local.db.AppDatabase;
import id.ukdw.srmmobile.data.local.db.AppDbHelper;
import id.ukdw.srmmobile.data.local.db.DbHelper;
import id.ukdw.srmmobile.data.local.prefs.AppPreferencesHelper;
import id.ukdw.srmmobile.data.local.prefs.PreferencesHelper;
import id.ukdw.srmmobile.di.DatabaseInfo;
import id.ukdw.srmmobile.di.PreferenceInfo;
import id.ukdw.srmmobile.utils.AppConstants;
import id.ukdw.srmmobile.utils.rx.AppSchedulerProvider;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.di.module
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:22
 * <p>
 * Description : AppModule
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        //return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
          //      .build();
        return new AppDatabase();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    GoogleSignInOptions provideGoogleSignInClient() {
        return new GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestScopes( new Scope( Scopes.DRIVE_APPFOLDER ) )
                .requestServerAuthCode( AppConstants.GOOGLE_SERVER_CLIENT_ID )
                .requestEmail()
                .build();
    }
}
