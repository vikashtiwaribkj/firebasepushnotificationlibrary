package com.hiappz.firebasepushnotify;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hiappz.pushnotifylib.helpers.LogHelper;
import com.hiappz.firebasepushnotify.firebaseutilities.FirebaseAnalyticsHelper;
import com.hiappz.pushnotifylib.utilities.FirebaseAnalyticsInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements FirebaseAnalyticsInterface{
    private final String TAG = "MainActivity";
    private ExecutorService executorService;
    private FirebaseAnalyticsHelper.Builder firebaseAnalyticsHelperBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogHelper.d(TAG, "onCreate: -->> executed");

        sendAnalytics(TAG, "OnCreated", "MainActivity created");

    }

    @Override
    public void sendAnalytics(String itemName, String itemCategory, String contentDescription) {
        executorService = Executors.newCachedThreadPool();
        firebaseAnalyticsHelperBuilder = new FirebaseAnalyticsHelper.Builder()
                .setContext(this)
                .setItemName(itemName)
                .setItemCotegory(itemCategory)
                .setContentType("ActivityHome")
                .setContentDescription(contentDescription);

        executorService.execute(firebaseAnalyticsHelperBuilder.build());
    }
}
