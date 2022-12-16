package com.example.alexandre.sandaliashavaianas;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by Wilma on 10/11/2015.
 */
public class HavaianaApp extends Application {
    Bus mBus;

    @Override
    public void onCreate() {
        super.onCreate();
        mBus = new Bus();
    }

    public Bus getBus(){
        return mBus;
    }
}
