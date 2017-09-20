package com.hiappz.firebasepushnotify.firebaseutilities;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.hiappz.pushnotifylib.helpers.ExceptionHelper;
import com.hiappz.pushnotifylib.helpers.LogHelper;
import com.hiappz.pushnotifylib.utilities.TimeFormatHelper;
import com.hiappz.pushnotifylib.utilities.UtilityConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aj on 19/9/17.
 */

public class FirebaseAnalyticsHelper implements Runnable{
    private final String TAG = "FirebaseAnalyticsHelper";
    private FirebaseAnalytics firebaseAnalytics;
    private Context context;
    private String contentType, itemName, itemCotegory, contentDescription;

    public FirebaseAnalyticsHelper(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCotegory(String itemCotegory) {
        this.itemCotegory = itemCotegory;
    }

    public static class Builder{
        private Context context;
        private String contentType, itemName, itemCotegory, contentDescription;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setContentDescription(String contentDescription) {
            this.contentDescription = contentDescription;
            return this;
        }

        public Builder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder setItemCotegory(String itemCotegory) {
            this.itemCotegory = itemCotegory;
            return this;
        }

        public FirebaseAnalyticsHelper build(){
            FirebaseAnalyticsHelper instance = null;
            if (this.context != null ){
                instance = new FirebaseAnalyticsHelper(context);
                instance.setContext(context);
                instance.setItemName(itemName);
                instance.setItemCotegory(itemCotegory);
                instance.setContentType(contentType);
                instance.setContentDescription(contentDescription);
                return instance;
            }else {
                return instance;
            }
        }
    }



    @Override
    public void run() {
        int year = 0, month = 0, dayOfMonth= 0;
        String startDate = null;
        TimeFormatHelper timeFormatHelper = null;
        SimpleDateFormat simpleDateFormat = null;
        Date date = null;
        Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        startDate = year + "-" + month + "-" + dayOfMonth;
        simpleDateFormat = new SimpleDateFormat(UtilityConstant.mFormat1);
        try {
            date = simpleDateFormat.parse(startDate);
            startDate = simpleDateFormat.format(date);
            timeFormatHelper = new TimeFormatHelper();
            startDate = timeFormatHelper.convertDateFormmat(startDate, UtilityConstant.mFormat1, UtilityConstant.mFormat3);
        }catch (ParseException e){
            LogHelper.e(TAG, "run: -->> ParseException -->> "+e.getMessage());
            ExceptionHelper.handleParseException(TAG, e);
        }

        Bundle bundle = new Bundle(4);
        bundle.putString(FirebaseAnalytics.Param.START_DATE, startDate);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, itemCotegory);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        bundle.putString(FirebaseAnalytics.Param.CONTENT, contentDescription);

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
