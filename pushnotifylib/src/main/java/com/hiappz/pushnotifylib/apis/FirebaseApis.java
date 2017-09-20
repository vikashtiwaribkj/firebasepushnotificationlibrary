package com.hiappz.pushnotifylib.apis;

import com.hiappz.pushnotifylib.models.RegisterDeviceReqModel;
import com.hiappz.pushnotifylib.models.RegisterDeviceResModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by aj on 19/9/17.
 */

public interface FirebaseApis {
    //    http://apipush.sia.co.in/api/push/token/v/subscribe/
    public static String registerDeviceBaseUrl = "http://apipush.sia.co.in/";

    @POST("api/push/token/v/subscribe/")
    public abstract Observable<Response<RegisterDeviceResModel>> subscribeDevice(@Body RegisterDeviceReqModel deviceReqModel);
}
