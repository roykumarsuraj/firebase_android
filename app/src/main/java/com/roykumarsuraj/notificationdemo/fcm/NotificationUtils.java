package com.roykumarsuraj.notificationdemo.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.roykumarsuraj.notificationdemo.MainActivity;
import com.roykumarsuraj.notificationdemo.R;
import com.roykumarsuraj.notificationdemo.fcm.NotificationBean;

import java.util.HashMap;
import java.util.Map;

public class NotificationUtils {

    private static final String CHANNEL_ID  = "notification_channel_primary";
    private static final String CHANNEL_NAME  = "1100";
    private Map<String, Class> activityMap =  new HashMap<>();
    private Context context;

    public NotificationUtils(Context context){
    this.context = context;
    activityMap.put("mainActivity", MainActivity.class);
    }

    void displayNotification(NotificationBean nb, Intent resultIntent){
        String title = nb.getTitle();
        String message = nb.getMessage();
        String action = nb.getAction();
        String action_destination = nb.getActiondestination();

        PendingIntent pendingIntent;
        if(action != null && action.equals("url")){
            Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(action_destination));
            pendingIntent = PendingIntent.getActivity(context,0,notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else if (action != null && action.equals("activity") && activityMap.containsKey(action_destination)) {
            resultIntent = new Intent(context,activityMap.get(action_destination));
            pendingIntent = PendingIntent.getActivity(context,0,resultIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else{
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context,0,resultIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
        Notification notification;

        notification = notificationBuilder.setTicker(title).setWhen(0)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify((int)System.currentTimeMillis(), notification);

    }
}
