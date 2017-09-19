package com.hiappz.pushnotifylib.utilities;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.util.Map;

/**
 * Created by aj on 19/9/17.
 */

public interface NotificationFactory {
    public abstract void fireNotification(Map<String, String> dataPayload, Context context);
    public abstract NotificationCompat.Builder setUpNotification(Context context, NotificationCompat.Builder notificationCompatBuilder);
    public abstract void sendNotification(Context context, NotificationCompat.Builder notificationCompatBuilder, Class activityClass);
}
