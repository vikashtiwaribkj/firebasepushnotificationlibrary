package com.hiappz.pushnotifylib.utilities;

/**
 * Created by aj on 19/9/17.
 */

public interface FirebaseAnalyticsInterface {
    /**
     * This method send analytics report to firebase server which can be shown in firebase console
     * @param itemName Represents name of the Activity / Fragment / Receiver / Service / Provider
     * @param itemCategory Represents user action to bent to firebase
     * @param contentDescription Represents action description that what do the action performs.
     */
    public abstract void sendAnalytics(String itemName, String itemCategory, String contentDescription);
}
