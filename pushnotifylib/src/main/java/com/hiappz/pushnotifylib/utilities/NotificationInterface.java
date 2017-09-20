package com.hiappz.pushnotifylib.utilities;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.util.Map;

/**
 * Created by aj on 19/9/17.
 */

public interface NotificationInterface {
    /**
     * This method constructs / sets up notification and returns NotificationCompat.Builder (support library v4) instance
     * @param context Represents application context instance
     * @param dataPayload It is a map that represents payload sent from the firebase server
     * @return returns ready to be used NotificationCompat.Builder instance.
     */
    public abstract NotificationCompat.Builder setUpNotification(Context context, Map<String, String> dataPayload);

    /**
     * This method sends notificaion to system try in notification bar as well as in notification drawer
     * @param context Represents application context instance
     * @param notificationCompatBuilder Builder class of NotificationCompat from support library v4. It is being used to apply custom requirements on notification
     * @param activityClass Represents Class instance of the activity to be opened to show the notification. It is contained in payLoad click_action key.
     */
    public abstract void sendNotification(Context context, NotificationCompat.Builder notificationCompatBuilder, Class activityClass);
}
