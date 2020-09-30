package id.ukdw.srmmobile;

import android.app.Service;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import id.ukdw.srmmobile.ui.home.HomeActivity;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile
 * <p>
 * User: dendy
 * Date: 27/09/2020
 * Time: 14:52
 * <p>
 * Description : FirebaseCloudMessagingService
 */
public class FirebaseCloudMessagingService extends FirebaseMessagingService {
    public String TAG = "FIREBASE MESSAGING";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            startActivity(new Intent(this, HomeActivity.class));

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {

            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }
}
