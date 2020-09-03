package id.ukdw.srmmobile.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.ukdw.srmmobile.data.local.db.DbHelper;
import id.ukdw.srmmobile.data.local.prefs.PreferencesHelper;
import id.ukdw.srmmobile.data.remote.AuthApi;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:30
 * <p>
 * Description : AppDataManager
 */
@Singleton
public class AppDataManager implements DataManager {
    private static final String TAG = AppDataManager.class.getSimpleName();
    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;


    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }


    @Override
    public void updateUserInfo(String accessToken, String idToken, String refreshToken,
                               String nomorInduk, LoggedInMode loggedInMode, String nama,
                               String email, String imageUrl, String role) {
        setAccessToken(accessToken);
        setIdToken(idToken);
        setRefreshToken(refreshToken);
        setCurrentNoInduk(nomorInduk);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(nama);
        setCurrentUserEmail(email);
        setCurrentUserImageURL(imageUrl);
        setCurrentUserRole(role);
    }

    @Override
    public void updateTokenInfo(String accessToken, String idToken) {
        setAccessToken(accessToken);
        setIdToken(idToken);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        //mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public String getIdToken() {
        return mPreferencesHelper.getIdToken();
    }

    @Override
    public void setIdToken(String idToken) {
        mPreferencesHelper.setIdToken(idToken);
    }

    @Override
    public String getRefreshToken() {
        return mPreferencesHelper.getRefreshToken();
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        mPreferencesHelper.setRefreshToken(refreshToken);
    }

    @Override
    public String getCurrentNoInduk() {
        return mPreferencesHelper.getCurrentNoInduk();
    }

    @Override
    public void setCurrentNoInduk(String noInduk) {
        mPreferencesHelper.setCurrentNoInduk(noInduk);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserImageURL() {
        return mPreferencesHelper.getCurrentUserImageURL();
    }

    @Override
    public void setCurrentUserImageURL(String imageURL) {
        mPreferencesHelper.setCurrentUserImageURL(imageURL);
    }

    @Override
    public String getCurrentUserRole() {
        return mPreferencesHelper.getCurrentUserRole();
    }

    @Override
    public void setCurrentUserRole(String role) {
        mPreferencesHelper.setCurrentUserRole(role);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }
}
