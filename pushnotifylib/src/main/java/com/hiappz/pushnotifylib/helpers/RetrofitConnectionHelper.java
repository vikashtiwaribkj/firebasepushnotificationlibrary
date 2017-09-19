package com.hiappz.pushnotifylib.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.hiappz.pushnotifylib.apis.FirebaseApis;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aj on 19/9/17.
 */

public class RetrofitConnectionHelper {

    /**
     * This method returns retrofit2 instance ready to be consumed
     * @return
     */
    public static Retrofit getRetrofitInstance(){
        Retrofit retrofit = null;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        /*if (BuildConfig.DEBUG){
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }*/
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor);

        Gson gson = new Gson();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(FirebaseApis.registerDeviceBaseUrl)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.newThread()));

        return retrofit = retrofitBuilder.build();
    }

    /**
     * This method returns true if at the time of calling this method device is connected to internet and returns false if not
     * @param context Represents context in order to get ConnectivityManager system service instance
     * @return
     */
    public static boolean isInternetConnected(Context context){
        boolean connectionFlag = false;
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        connectionFlag = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected() == true ? true : false;
        return connectionFlag;
    }
}
