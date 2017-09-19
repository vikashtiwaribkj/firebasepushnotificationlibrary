package com.hiappz.pushnotifylib.utilities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.hiappz.pushnotifylib.helpers.ExceptionHelper;
import com.hiappz.pushnotifylib.helpers.LogHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

/**
 * Created by aj on 19/9/17.
 */

public class FirebaseNotification implements NotificationFactory {
    private final String TAG = "FirebaseNotification";
    private static final int NOTIFICATION_ID = 100;

    private final String cidKey = "cid", cridKey = "crid", idKey = "id", titleKey = "title", bodyKey = "body", iconKey = "icon",
            clickActionKey = "click_action";

    private String idValue = null, titleValue = null, bodyValue = null, campaignIdValue, campaignReportIdValue,
            iconValue = null, clickActionValue = null;
    private int navigationId;
    private boolean isComingPushNotification = false;

    @Override
    public void fireNotification(Map<String, String> dataPayload, Context context) {
//        notificationHelper = new FirebaseNotificationHelper.Builder().

        LogHelper.d(TAG, "fireNotification: -->> dataPayload -->> "+dataPayload);

        try {
            titleValue = dataPayload.get(titleKey);
            bodyValue = dataPayload.get(bodyKey);
            iconValue = dataPayload.get(iconKey);
            clickActionValue = dataPayload.get(clickActionKey);
            idValue = dataPayload.get(idKey);
            campaignIdValue = dataPayload.get(cidKey);
            campaignReportIdValue = dataPayload.get(cridKey);

            navigationId = Integer.valueOf(idValue);
        } catch (NumberFormatException e) {
            ExceptionHelper.handleNumberFormatException(TAG, e);
        } catch (Exception e) {
            ExceptionHelper.handleException(TAG, e);
        }

//        sendNotification(context, ActivityPushNotificationMain.class);

    }

    @Override
    public NotificationCompat.Builder setUpNotification(Context context, NotificationCompat.Builder notificationCompatBuilder) {
        Bitmap remote_picture = null;

        notificationCompatBuilder.setContentTitle(titleValue != null ? titleValue: "")
                .setContentText(bodyValue != null ? bodyValue : "");
//                .setSubText("Notification SubText")

        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL((iconValue)).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (iconValue != null && !iconValue.equals("")) {
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                    .bigPicture(remote_picture);
//                    .setSummaryText("BigPicture Summary Text");

            notificationCompatBuilder.setStyle(bigPictureStyle);
        }

        return notificationCompatBuilder;
    }

    @Override
    public void sendNotification(Context context, NotificationCompat.Builder notificationCompatBuilder, Class activityClass) {

        isComingPushNotification = true;

        Intent actionIntent = new Intent(context, activityClass);
        actionIntent.putExtra(UtilityConstant.COME_FROM_PUSH_NOTIFICATION_KEY, isComingPushNotification);
        actionIntent.putExtra(UtilityConstant.CAMPAIGN_ID_KEY, campaignIdValue != null ? campaignIdValue : "");
        actionIntent.putExtra(UtilityConstant.CAMPAIGN_REPORT_ID_KEY, campaignReportIdValue != null ? campaignReportIdValue : "");
        actionIntent.putExtra(UtilityConstant.REQUIRED_UTILITY_ID_KEY, navigationId);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationCompatBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationCompatBuilder.build());
    }
}
