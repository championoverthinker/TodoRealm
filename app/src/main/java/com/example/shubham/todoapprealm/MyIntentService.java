package com.example.shubham.todoapprealm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification(intent.getStringExtra("Notif Message"));

    }


    private void sendNotification(String msg) {
        Log.d("AlarmService", "Preparing to send notification...: " + msg);


        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(this);
        alamNotificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_email).setContentText(msg).setContentIntent(contentIntent);


        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

}
