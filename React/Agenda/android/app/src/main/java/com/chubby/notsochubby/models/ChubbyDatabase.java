package com.chubby.notsochubby.models;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.chubby.notsochubby.models.dao.MealsDao;
import com.chubby.notsochubby.models.dao.NewsCategoryDao;
import com.chubby.notsochubby.models.dao.NewsDao;
import com.chubby.notsochubby.models.dao.SpotCategoryDao;
import com.chubby.notsochubby.models.dao.SpotDao;
import com.chubby.notsochubby.models.entities.Meal;
import com.chubby.notsochubby.models.entities.News;
import com.chubby.notsochubby.models.entities.NewsCategory;
import com.chubby.notsochubby.models.entities.Spot;
import com.chubby.notsochubby.models.entities.SpotCategory;
import com.chubby.notsochubby.models.utils.Converters;


@Database(entities = {Spot.class, News.class, NewsCategory.class, SpotCategory.class, Meal.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ChubbyDatabase extends RoomDatabase {

    private static final String DB_NAME = "notsochubby.db";
    private static ChubbyDatabase instance;

    public abstract NewsDao newsDao();
    public abstract NewsCategoryDao newsCategoryDao();
    public abstract SpotDao spotDao();
    public abstract SpotCategoryDao spotCategoryDao();
    public abstract MealsDao mealsDao();

    public static ChubbyDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (ChubbyDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ChubbyDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }
}
