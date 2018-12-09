package com.chubby.notsochubby.ViewModels;

import android.app.Application;
import android.util.Log;

import com.chubby.notsochubby.Models.ChubbyDatabase;
import com.chubby.notsochubby.Models.Dao.NewsDao;
import com.chubby.notsochubby.Models.Entities.NewsAndCategory;

import java.util.List;

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
