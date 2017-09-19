package com.hiappz.pushnotifylib.presenters;

import com.hiappz.pushnotifylib.utilities.UtilityConstant;

/**
 * Created by aj on 19/9/17.
 */

public abstract class PresenterFactory {
    private static PresenterFactory presenterFacory;

    /**
     * This is a factory method where all presenters are instantiated based on the integer valule of factory code. This method must be used to instantiate all presenters in project.
     * @param factoryCode Factory code represents which presenter is to be instantiated.
     * @return Returns PresenterFactory instance. Every presenter must extend PresenterFactory.
     */
    public static PresenterFactory getInstance(int factoryCode){
        switch (factoryCode){
            case UtilityConstant.REGISTER_DEVICE_PRSENTER:
                presenterFacory = new RegisterDevicePresenter();
                return presenterFacory;

        }
        return presenterFacory;
    }
}
