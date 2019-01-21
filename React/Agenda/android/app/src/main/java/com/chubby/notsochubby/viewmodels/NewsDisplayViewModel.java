package com.chubby.notsochubby.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.chubby.notsochubby.models.ChubbyDatabase;
import com.chubby.notsochubby.models.dao.NewsDao;
import com.chubby.notsochubby.models.entities.NewsAndCategory;



public class NewsDisplayViewModel extends AndroidViewModel {

    private NewsDao newsDao;
    private LiveData<NewsAndCategory> news;

    public NewsDisplayViewModel(@NonNull Application application, int newsId) {
        super(application);
        ChubbyDatabase database = ChubbyDatabase.getInstance(application);
        this.newsDao = database.newsDao();
        this.news = newsDao.getNewsById(newsId);
    }

    public LiveData<NewsAndCategory> getNews(){
        return news;
    }
}
