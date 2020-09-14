package id.ukdw.srmmobile.data;

import id.ukdw.srmmobile.data.local.db.DbHelper;
import id.ukdw.srmmobile.data.local.prefs.PreferencesHelper;
import id.ukdw.srmmobile.data.remote.ApiHelper;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:27
 * <p>
 * Description : DataManager
 */
public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }

    void updateUserInfo(
            String accessToken,
            String idToken,
            String refreshToken,
            String nomorInduk,
            LoggedInMode loggedInMode,
            String nama,
            String email,
            String imageUrl,
            String role);

    void updateTokenInfo(
            String accessToken,
            String idToken);

    void clearUserInfo();

    void profileUserInfo(
            String nomorInduk,
            String name,
            String email,
            String jenisKelamin,
            String imageProfile);
}
