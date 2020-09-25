package id.ukdw.srmmobile.data.remote;

import io.reactivex.Single;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data.remote
 * <p>
 * User: dendy
 * Date: 03/09/2020
 * Time: 16:29
 * <p>
 * Description : ApiHelper
 */
public interface ApiHelper {

    AuthApi getAuthApi();

    UserApi getUserApi(String accessToken, String refreshToken);
}
