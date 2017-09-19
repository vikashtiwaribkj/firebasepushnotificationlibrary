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

    public Observable<Response<com.hiappz.pushnotifylib.models.RegisterDeviceResModel>> registerDeviceToFirebase(final LifeCycleListener listener, final String android_id, final String firebaseToken) {
        LogHelper.d(TAG, "-->> registerDeviceToFirebase -->> executed");

        Observable<Response<RegisterDeviceResModel>> responseObservable = null;
        Retrofit retrofit = RetrofitConnectionHelper.getRetrofitInstance();
        FirebaseApis apis = retrofit.create(FirebaseApis.class);

        RegisterDeviceReqModel deviceReqModel = new RegisterDeviceReqModel();
        deviceReqModel.setSubId(firebaseToken);
        deviceReqModel.setDeviceId(android_id);
        deviceReqModel.setOldSubId("");
        deviceReqModel.setAppId("BnbOptsmQPtHa9+4e+9kY7N6QbqlnxkALL852a4VP+k=");
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
