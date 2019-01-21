package com.chubby.notsochubby.models.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.chubby.notsochubby.models.entities.Meal;
import com.chubby.notsochubby.models.entities.News;
import com.chubby.notsochubby.models.entities.NewsAndCategory;

import java.util.List;

@Dao
public interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Meal> meals);

    @Update
    void update(Meal... meal);

    @Delete
    void delete(Meal... meal);

    @Query("SELECT * FROM meals")
    LiveData<List<Meal>> getAllMeals();

    @Query("SELECT * FROM meals WHERE cat_id=:catId")
    LiveData<List<Meal>> getMealsFromCategory(final int catId);

    @Query("SELECT * FROM meals WHERE id=:mealsId")
    LiveData<Meal> getMealById(final int mealsId);

}
