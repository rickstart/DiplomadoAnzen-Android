package com.mobintum.feedplaces.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mobintum.feedplaces.database.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo on 26/05/17.
 * www.mobintum.com
 */

public class Notification {
    public static final String TABLE_NAME = "Notification";
    public static final String NOTIFICATION_ID = "notificationId";
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String TIME = "time";

    private String title;
    private String body;
    private long time;

    public Notification(String title, String body, long time) {
        this.title = title;
        this.body = body;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static List<Notification> getNotifications(Context context){
        List<Notification> notifications = new ArrayList<>();
        Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor!=null){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
                String body = cursor.getString(cursor.getColumnIndexOrThrow(BODY));
                long time = cursor.getLong(cursor.getColumnIndexOrThrow(TIME));
                notifications.add(new Notification(title,body,time));
            }
        }
        return notifications;
    }

    public static long insert(Context context, Notification notification){
        ContentValues cv = new ContentValues();
        cv.put(TITLE, notification.getTitle());
        cv.put(BODY, notification.getBody());
        cv.put(TITLE, notification.getTime());
        return DatabaseAdapter.getDB(context).insert(TABLE_NAME,null,cv);
    }
}
