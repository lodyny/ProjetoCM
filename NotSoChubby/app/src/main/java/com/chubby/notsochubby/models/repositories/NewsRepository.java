package com.chubby.notsochubby.models.repositories;

import android.app.Application;

import com.chubby.notsochubby.models.ChubbyDatabase;
import com.chubby.notsochubby.models.dao.NewsDao;
import com.chubby.notsochubby.models.entities.NewsAndCategory;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NewsRepository {
    private NewsDao newsDao;

    public NewsRepository(Application application){
        ChubbyDatabase database = ChubbyDatabase.getInstance(application);
        this.newsDao = database.newsDao();
    }

    public LiveData<List<NewsAndCategory>> getAllNews(){
        return newsDao.getAllNews();
    }

    public LiveData<List<NewsAndCategory>> getLimitNews(int limit){
        return newsDao.getNewsLimit(limit);
    }

    public LiveData<List<NewsAndCategory>> getNewsFromCategory(int catId){
        return newsDao.getNewsFromCategory(catId);
    }

    public LiveData<List<NewsAndCategory>> getNewsFromCategoryLimit(int limit, int catId){
        return newsDao.getNewsFromCategoryLimit(limit, catId);
    }
}
