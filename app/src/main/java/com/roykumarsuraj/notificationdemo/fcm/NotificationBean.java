package com.roykumarsuraj.notificationdemo.fcm;

public class NotificationBean {

    String title = "";
    String message = "";
    String action = "";
    String actiondestination = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActiondestination() {
        return actiondestination;
    }

    public void setActiondestination(String actiondestination) {
        this.actiondestination = actiondestination;
    }
}
