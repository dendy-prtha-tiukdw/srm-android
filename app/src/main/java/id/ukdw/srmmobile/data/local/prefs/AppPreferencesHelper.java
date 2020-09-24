package id.ukdw.srmmobile.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.di.PreferenceInfo;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data.local.prefs
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 22:32
 * <p>
 * Description : AppPreferencesHelper
 */
public class AppPreferencesHelper implements PreferencesHelper {
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_ID_TOKEN = "PREF_KEY_ID_TOKEN";
    private static final String PREF_KEY_REFRESH_TOKEN = "PREF_KEY_REFRESH_TOKEN";
    private static final String PREF_KEY_NO_INDUK = "PREF_KEY_NO_INDUK";
    private static final String PREF_KEY_NAME = "PREF_KEY_NAME";
    private static final String PREF_KEY_EMAIL = "PREF_KEY_EMAIL";
    private static final String PREF_KEY_IMAGE_URL = "PREF_KEY_IMAGE_URL";
    private static final String PREF_KEY_ROLE = "PREF_KEY_ROLEL";
    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getCurrentAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getCurrentIdToken() {
        return mPrefs.getString(PREF_KEY_ID_TOKEN, null);
    }

    @Override
    public void setCurrentIdToken(String idToken) {
        mPrefs.edit().putString(PREF_KEY_ID_TOKEN, idToken).apply();
    }

    @Override
    public String getCurrentRefreshToken() {
        return mPrefs.getString(PREF_KEY_REFRESH_TOKEN, null);
    }

    @Override
    public void setCurrentRefreshToken(String refreshToken) {
        mPrefs.edit().putString(PREF_KEY_REFRESH_TOKEN, refreshToken).apply();
    }

    @Override
    public String getCurrentNoInduk() {
        return mPrefs.getString(PREF_KEY_NO_INDUK, null);
    }

    @Override
    public void setCurrentNoInduk(String noInduk) {
        mPrefs.edit().putString(PREF_KEY_NO_INDUK, noInduk).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_EMAIL, email).apply();
    }

    @Override
    public String getCurrentUserImageURL() {
        return mPrefs.getString(PREF_KEY_IMAGE_URL, null);
    }

    @Override
    public void setCurrentUserImageURL(String imageURL) {
        mPrefs.edit().putString(PREF_KEY_IMAGE_URL, imageURL).apply();
    }

    @Override
    public String getCurrentUserRole() {
        return mPrefs.getString(PREF_KEY_ROLE, null);
    }

    @Override
    public void setCurrentUserRole(String role) {
        mPrefs.edit().putString(PREF_KEY_ROLE, role).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }
}
