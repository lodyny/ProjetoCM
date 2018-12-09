package com.chubby.notsochubby.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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