package com.chubby.notsochubby.Models;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class ChubbyApplication extends Application {

    private String username;
    private String useremail;

    @Override
    public void onCreate() {
        super.onCreate();
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
