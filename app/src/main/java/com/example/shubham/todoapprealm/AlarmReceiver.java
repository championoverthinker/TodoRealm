package com.example.shubham.todoapprealm;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.NotificationCompat;

/**
 * Created by shubham on 8/2/17.
 */

public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        if (alarmUri == null) {
//            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }
//        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//        ringtone.play();

//        ComponentName comp = new ComponentName(context.getPackageName(),
//                AlarmService.class.getName());
//        startWakefulService(context,(intent.setComponent(comp)));
//        setResultCode(Activity.RESULT_OK);
        Intent i=new Intent(context,MyIntentService.class);
        i.putExtra("Notif Message",intent.getStringExtra("Notif Message"));
        context.startService(i);
//        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
//                new Intent(context, MainActivity.class), 0);
//
//        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(context);
//        alamNotificationBuilder.setSmallIcon(android.R.drawable.ic_dialog_email).setContentTitle(intent.getStringExtra("Notif Message")).setContentText(intent.getStringExtra("Notif Message")).setContentIntent(contentIntent);
//
//
//        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(1,alamNotificationBuilder.build());
    }
}
