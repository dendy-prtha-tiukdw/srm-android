package id.ukdw.srmmobile.ui.profile;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.profil
 * <p>
 * User: dendy
 * Date: 21/09/2020
 * Time: 15:27
 * <p>
 * Description : ProfileNavigator
 */
public interface ProfileNavigator {

    void onGetProfileCompleted(String nama, String nim, String jenisKelamin,String email, String ulrImage);
}
