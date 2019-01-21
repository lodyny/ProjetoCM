package com.chubby.notsochubby.models.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.chubby.notsochubby.models.entities.News;
import com.chubby.notsochubby.models.entities.NewsAndCategory;

import java.util.List;


@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<News> news);

    @Update
    void update(News... news);

    @Delete
    void delete(News... news);

    @Query("SELECT news.*, news_categories.id as cat_id, news_categories.name as cat_name " +
            "FROM news INNER JOIN news_categories ON news.catId = news_categories.id ORDER BY publishAt DESC")
    LiveData<List<NewsAndCategory>> getAllNews();

    @Query("SELECT news.*, news_categories.id as cat_id, news_categories.name as cat_name " +
            "FROM news INNER JOIN news_categories ON news.catId = news_categories.id ORDER BY publishAt DESC LIMIT :limit")
    LiveData<List<NewsAndCategory>> getNewsLimit(final int limit);

    @Query("SELECT news.*, news_categories.id as cat_id, news_categories.name as cat_name " +
            "FROM news INNER JOIN news_categories ON news.catId = news_categories.id WHERE catId=:catId ORDER BY publishAt DESC")
    LiveData<List<NewsAndCategory>> getNewsFromCategory(final int catId);

    @Query("SELECT news.*, news_categories.id as cat_id, news_categories.name as cat_name " +
            "FROM news INNER JOIN news_categories ON news.catId = news_categories.id WHERE news.id=:newsId")
    LiveData<NewsAndCategory> getNewsById(final int newsId);

    @Query("SELECT news.*, news_categories.id as cat_id, news_categories.name as cat_name " +
            "FROM news INNER JOIN news_categories ON news.catId = news_categories.id WHERE catId=:catId ORDER BY publishAt DESC LIMIT :limit")
    LiveData<List<NewsAndCategory>> getNewsFromCategoryLimit(final int catId, final int limit);

}
