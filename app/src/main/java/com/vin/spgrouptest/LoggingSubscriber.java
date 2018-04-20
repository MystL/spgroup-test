package com.vin.spgrouptest;

import android.util.Log;

import rx.Subscriber;

public abstract class LoggingSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        Log.i(this.getClass().getCanonicalName(), "completed");

    }

    @Override
    public void onError(Throwable e) {
        if (e != null && e.getMessage() != null) {
            Log.e(this.getClass().getCanonicalName(), "error in logging subscriber : " + e.getMessage());
            e.printStackTrace();
        }
    }


}
