package id.ukdw.srmmobile.ui.login;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.views.login
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 23:03
 * <p>
 * Description : LoginNavigator
 */
public interface LoginNavigator {
    void handleError(Throwable throwable);

    void login();

    void openHomeActivity();
}
