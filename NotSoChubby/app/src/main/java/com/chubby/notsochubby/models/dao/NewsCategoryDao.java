package com.chubby.notsochubby.models.dao;

import com.chubby.notsochubby.models.entities.NewsCategory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NewsCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<NewsCategory> categories);

    @Update
    void update(NewsCategory... category);

    @Delete
    void delete(NewsCategory... category);

    @Query("SELECT * FROM news_categories ORDER BY name")
    LiveData<List<NewsCategory>> getAllCategories();

    @Query("SELECT * FROM news_categories WHERE id=:catId")
    LiveData<NewsCategory> getCategoryById(final int catId);

}
