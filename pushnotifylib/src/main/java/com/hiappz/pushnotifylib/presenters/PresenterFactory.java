package com.hiappz.pushnotifylib.presenters;

import com.hiappz.pushnotifylib.utilities.UtilityConstant;

/**
 * Created by aj on 19/9/17.
 */

public abstract class PresenterFactory {
    private static PresenterFactory presenterFacory;

    public static PresenterFactory getInstance(int factoryCode){
        switch (factoryCode){
            case UtilityConstant.REGISTER_DEVICE_PRSENTER:
                presenterFacory = new RegisterDevicePresenter();
                return presenterFacory;

        }
        return presenterFacory;
    }
}
