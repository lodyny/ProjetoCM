package com.chubby.notsochubby.Models.Dao;

import com.chubby.notsochubby.Models.Entities.Meal;
import com.chubby.notsochubby.Models.Entities.News;
import com.chubby.notsochubby.Models.Entities.NewsAndCategory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
