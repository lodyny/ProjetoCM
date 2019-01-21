package com.chubby.notsochubby.models;

import android.app.Application;

import com.chubby.notsochubby.BuildConfig;
import com.facebook.react.ReactApplication;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

public class ChubbyApplication extends Application implements ReactApplication {

    private String username;
    private String useremail;


    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new RNGestureHandlerPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        AndroidThreeTen.init(this);
    }

    public void setAuthentication(String user, String email){
        username = user;
        useremail = email;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail(){
        return useremail;
    }
}
