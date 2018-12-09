package com.chubby.notsochubby.models;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class ChubbyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }
}
