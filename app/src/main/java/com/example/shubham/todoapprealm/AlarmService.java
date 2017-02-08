package com.example.shubham.todoapprealm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by shubham on 8/2/17.
 */

public class AlarmService extends IntentService {

    private NotificationManager alarmNotificationManager;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public AlarmService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification("Wake Up! Wake Up!");

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
}

