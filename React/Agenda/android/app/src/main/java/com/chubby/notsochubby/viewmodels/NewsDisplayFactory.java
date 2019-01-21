package com.chubby.notsochubby.viewmodels;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class NewsDisplayFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private int newsId;

    public NewsDisplayFactory(Application application, int newsId) {
        mApplication = application;
        this.newsId = newsId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewsDisplayViewModel(mApplication, newsId);
    }
}