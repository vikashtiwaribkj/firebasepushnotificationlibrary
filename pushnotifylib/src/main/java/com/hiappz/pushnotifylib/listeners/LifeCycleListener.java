package com.hiappz.pushnotifylib.listeners;

/**
 * Created by aj on 19/9/17.
 */

public interface LifeCycleListener {
    void onViewResumed(Object object);
    void onViewAttached();
    void onViewDetached();
}
