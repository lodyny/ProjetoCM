package com.chubby.notsochubby.viewmodels;

import android.app.Application;

import com.chubby.notsochubby.models.ChubbyDatabase;
import com.chubby.notsochubby.models.dao.NewsDao;
import com.chubby.notsochubby.models.entities.NewsAndCategory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NewsViewModel extends AndroidViewModel {


    private NewsDao newsDao;
    private LiveData<List<NewsAndCategory>> news;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        ChubbyDatabase database = ChubbyDatabase.getInstance(application);
        this.newsDao = database.newsDao();
        this.news = newsDao.getNewsLimit(10);
    }

    public LiveData<List<NewsAndCategory>> getAllNews(){
        news = newsDao.getAllNews();
        return news;
    }

    public LiveData<List<NewsAndCategory>> getLimitNews(int limit){
        news = newsDao.getNewsLimit(limit);
        return news;
    }

    public LiveData<List<NewsAndCategory>> getNewsFromCategory(int catId){
        news = newsDao.getNewsFromCategory(catId);
        return news;
    }

    public LiveData<List<NewsAndCategory>> getNewsFromCategoryLimit(int limit, int catId){
        news = newsDao.getNewsFromCategoryLimit(limit, catId);
        return news;
    }

    public LiveData<List<NewsAndCategory>> getNews(){
        return news;
    }

}
