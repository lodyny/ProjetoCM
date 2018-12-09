package com.chubby.notsochubby.models.dao;

import com.chubby.notsochubby.models.entities.News;
import com.chubby.notsochubby.models.entities.NewsAndCategory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
