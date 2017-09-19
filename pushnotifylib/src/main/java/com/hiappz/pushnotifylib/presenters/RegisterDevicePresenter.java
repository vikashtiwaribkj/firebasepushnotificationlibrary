package com.hiappz.pushnotifylib.presenters;

import com.hiappz.pushnotifylib.apis.FirebaseApis;
import com.hiappz.pushnotifylib.helpers.LogHelper;
import com.hiappz.pushnotifylib.helpers.RetrofitConnectionHelper;
import com.hiappz.pushnotifylib.listeners.LifeCycleListener;
import com.hiappz.pushnotifylib.models.RegisterDeviceReqModel;
import com.hiappz.pushnotifylib.models.RegisterDeviceResModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by aj on 19/9/17.
 */

public class RegisterDevicePresenter extends PresenterFactory {
    private final String TAG = "RegisterDevicePresenter";
    private RegisterDeviceResModel deviceResModel = null;

    /**
     * This method calls register device api to our server using retrofir2.1 to make network call and RxJava2 for asynchronous call to api with subscriber-observable pattern
     * @param listener It is an interface which informs UI when an api response is available.
     * @param android_id AndroidId uniquely identifies user device. It is changed when user factory reset his device. It is required to sebscribe to our server.
     * @param firebaseToken Firebase token is received in FirebaseInstanceIdService. It is required to sebscribe to our server.
     * @return This method returns Observable instance of type Response which we define while writing api interface using retrofit2.1. It is required when we need to unsubscribe.
     */
    public Observable<Response<RegisterDeviceResModel>> registerDeviceToFirebase(final LifeCycleListener listener, final String android_id, final String firebaseToken) {
        LogHelper.d(TAG, "-->> registerDeviceToFirebase -->> executed");

        Observable<Response<RegisterDeviceResModel>> responseObservable = null;
        Retrofit retrofit = RetrofitConnectionHelper.getRetrofitInstance();
        FirebaseApis apis = retrofit.create(FirebaseApis.class);

        RegisterDeviceReqModel deviceReqModel = new RegisterDeviceReqModel();
        deviceReqModel.setSubId(firebaseToken);
        deviceReqModel.setDeviceId(android_id);
        deviceReqModel.setOldSubId("");
        deviceReqModel.setAppId("0HhblVA564hCUd6k2cSkhOmlDHEE2Q2Cs05DSRLvmkg=");
        deviceReqModel.setDeviceType("android");
        deviceReqModel.setCity("Noida");
        deviceReqModel.setState("Uttar Pradesh");
        deviceReqModel.setCountry("India");

        deviceResModel = new RegisterDeviceResModel();

        responseObservable = apis.subscribeDevice(deviceReqModel);
        responseObservable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<RegisterDeviceResModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogHelper.d(TAG, "-->> onSubscribe -->> executed -->> "+d.toString());
                    }

                    @Override
                    public void onNext(Response<RegisterDeviceResModel> value) {
                        if (value.isSuccessful() && value.code() == 200) {
                            deviceResModel = value.body();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerDeviceToFirebase(listener, android_id, firebaseToken);
                    }

                    @Override
                    public void onComplete() {
                        listener.onViewResumed(deviceResModel);
                    }
                });

        return responseObservable;
    }
}
