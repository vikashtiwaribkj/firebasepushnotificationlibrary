package com.hiappz.firebasepushnotify.firebaseutilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hiappz.firebasepushnotify.MainActivity;
import com.hiappz.firebasepushnotify.R;
import com.hiappz.pushnotifylib.helpers.ExceptionHelper;
<<<<<<< HEAD
=======
import com.hiappz.pushnotifylib.helpers.LogHelper;
>>>>>>> master
import com.hiappz.pushnotifylib.utilities.FirebaseNotification;

/**
 * Created by aj on 19/9/17.
 */

public class FirebaseMessagingServiceHelper extends FirebaseMessagingService {
    private final String TAG = "FirebaseMsgServiceHlper";
    private FirebaseNotification firebaseNotification;
    private NotificationCompat.Builder notificationCompatBuilder;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

<<<<<<< HEAD
=======
        LogHelper.d(TAG, "onMessageReceived -->> executed");

>>>>>>> master
        Bitmap largeIconBitmap = null;
        Uri defaultSoundUri = null;
        long[] pattern = {500, 500, 500, 500, 500};

        try {
            firebaseNotification = (FirebaseNotification) Class.forName(FirebaseNotification.class.getName()).newInstance();

            largeIconBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            firebaseNotification.setLargeIconBitmap(largeIconBitmap);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                firebaseNotification.setSmallIcon(R.mipmap.ic_launcher);
            } else {
                // Lollipop specific setColor method goes here.
                firebaseNotification.setSmallIcon(R.drawable.notification_small_icon);
            }

            firebaseNotification.setDefaultSoundUri(defaultSoundUri);
            firebaseNotification.setPattern(pattern);
            firebaseNotification.setAutoCancel(true);
            firebaseNotification.setColor(ContextCompat.getColor( getApplicationContext(), R.color.colorPureWhite));

            if (remoteMessage.getData() != null) {
                notificationCompatBuilder = firebaseNotification.setUpNotification(getApplicationContext(), remoteMessage.getData());
            }

            firebaseNotification.sendNotification(getApplicationContext(), notificationCompatBuilder, MainActivity.class);

        } catch (InstantiationException e) {
            ExceptionHelper.handleInstantiationException(TAG, e);
        } catch (IllegalAccessException e) {
            ExceptionHelper.handleIllegalAccessException(TAG, e);
        } catch (ClassNotFoundException e) {
            ExceptionHelper.handleClassNotFoundException(TAG, e);
        }
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }
}
