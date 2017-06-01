package com.mobintum.feedplaces.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mobintum.feedplaces.R;
import com.mobintum.feedplaces.activities.MainActivity;
import com.mobintum.feedplaces.models.Notification;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ricardo on 26/05/17.
 * www.mobintum.com
 */

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Notification.insert(getApplicationContext(),new Notification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),remoteMessage.getSentTime()));
        sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }


    private void sendNotification(String title, String body){
        Log.e("DEBUG S", title);
        Log.e("DEBUG S", body);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("notification",true);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,100,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification.build());
    }
}
