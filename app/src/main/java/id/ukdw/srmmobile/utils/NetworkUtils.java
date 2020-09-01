package id.ukdw.srmmobile.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.utils
 * <p>
 * User: dendy
 * Date: 29/08/2020
 * Time: 16:17
 * <p>
 * Description : NetworkUtils
 */
public final class NetworkUtils {
    private NetworkUtils() {
        // This class is not publicly instantiable
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}
