package com.hiappz.pushnotifylib.helpers;

import android.util.Log;

<<<<<<< HEAD
import com.hiappz.pushnotifylib.BuildConfig;

=======
>>>>>>> master
/**
 * Created by aj on 19/9/17.
 */

public class LogHelper {
<<<<<<< HEAD
    private static boolean isBuildDebug = false;

    static {
=======
    private static boolean isBuildDebug = true;

   /* static {
>>>>>>> master
        if (BuildConfig.DEBUG){
            isBuildDebug = true;
        }else {
            isBuildDebug = false;
        }
    }
<<<<<<< HEAD

=======
*/
>>>>>>> master
    public static void d(String TAG, String msg){
        if (isBuildDebug){
            Log.d(TAG, "d: -->> "+msg);
        }
    }

    public static void e(String TAG, String msg){
        if (isBuildDebug){
            Log.e(TAG, "e: -->> "+msg);
        }
    }

    public static void w(String TAG, String msg){
        if (isBuildDebug){
            Log.w(TAG, "w: -->> "+msg);
        }
    }

    public static void i(String TAG, String msg){
        if (isBuildDebug){
            Log.w(TAG, "i: -->> "+msg);
        }
    }
}
