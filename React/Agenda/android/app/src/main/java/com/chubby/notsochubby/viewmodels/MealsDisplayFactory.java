package com.chubby.notsochubby.viewmodels;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


public class MealsDisplayFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int mealsId;

    public MealsDisplayFactory(Application application, int mealsId) {
        mApplication = application;
        this.mealsId = mealsId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MealsDisplayViewModel(mApplication, mealsId);
    }
}

