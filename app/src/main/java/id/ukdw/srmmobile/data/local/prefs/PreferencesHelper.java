package id.ukdw.srmmobile.data.local.prefs;

import id.ukdw.srmmobile.data.DataManager;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data.local.prefs
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 22:31
 * <p>
 * Description : Preferences helper contract
 */
public interface PreferencesHelper {

    String getCurrentAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentIdToken();

    void setCurrentIdToken(String idToken);

    String getCurrentRefreshToken();

    void setCurrentRefreshToken(String refreshToken);

    String getCurrentNoInduk();

    void setCurrentNoInduk(String noInduk);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getCurrentUserImageURL();

    void setCurrentUserImageURL(String imageURL);

    String getCurrentUserRole();

    void setCurrentUserRole(String role);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);
}
