package com.roykumarsuraj.notificationdemo.fcm;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.roykumarsuraj.notificationdemo.MainActivity;

import java.util.Map;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            super.onMessageReceived(remoteMessage);
            Map<String, String> data =  remoteMessage.getData();
            handleData(data);
        }else{
            handleNotification(remoteMessage.getNotification());
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    private void handleNotification(RemoteMessage.Notification notification){
        String title = notification.getTitle();
        String message = notification.getBody();

        NotificationBean nb = new NotificationBean();
        nb.setTitle(title);
        nb.setMessage(message);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.displayNotification(nb,intent);
    }

    private void handleData(Map<String, String> data) {

        String title = data.get("title");
        String message = data.get("message");
        String action = data.get("action");
        String actiondestination = data.get("action_destination");

        NotificationBean nb = new NotificationBean();
        nb.setTitle(title);
        nb.setMessage(message);
        nb.setAction(action);
        nb.setActiondestination(actiondestination);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.displayNotification(nb,intent);

    }
}
