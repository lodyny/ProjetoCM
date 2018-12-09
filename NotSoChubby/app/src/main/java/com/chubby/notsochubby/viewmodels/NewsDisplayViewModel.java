package com.chubby.notsochubby.viewmodels;

import android.app.Application;

import com.chubby.notsochubby.models.ChubbyDatabase;
import com.chubby.notsochubby.models.dao.NewsDao;
import com.chubby.notsochubby.models.entities.NewsAndCategory;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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
