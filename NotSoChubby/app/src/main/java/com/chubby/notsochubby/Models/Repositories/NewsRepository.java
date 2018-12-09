package com.chubby.notsochubby.Models.Repositories;

import android.app.Application;

import com.chubby.notsochubby.Models.ChubbyDatabase;
import com.chubby.notsochubby.Models.Dao.NewsDao;
import com.chubby.notsochubby.Models.Entities.NewsAndCategory;

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
