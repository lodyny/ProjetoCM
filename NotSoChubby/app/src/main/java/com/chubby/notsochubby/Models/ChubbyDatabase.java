package com.chubby.notsochubby.Models;


import android.content.Context;

import com.chubby.notsochubby.Models.Dao.NewsCategoryDao;
import com.chubby.notsochubby.Models.Dao.NewsDao;
import com.chubby.notsochubby.Models.Dao.SpotCategoryDao;
import com.chubby.notsochubby.Models.Dao.SpotDao;
import com.chubby.notsochubby.Models.Entities.News;
import com.chubby.notsochubby.Models.Entities.NewsCategory;
import com.chubby.notsochubby.Models.Entities.Spot;
import com.chubby.notsochubby.Models.Entities.SpotCategory;
import com.chubby.notsochubby.Models.Utils.Converters;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Spot.class, News.class, NewsCategory.class, SpotCategory.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ChubbyDatabase extends RoomDatabase {

    private static final String DB_NAME = "notsochubby.db";
    private static ChubbyDatabase instance;

    public abstract NewsDao newsDao();
    public abstract NewsCategoryDao newsCategoryDao();
    public abstract SpotDao spotDao();
    public abstract SpotCategoryDao spotCategoryDao();

    public static ChubbyDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (ChubbyDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ChubbyDatabase.class, DB_NAME).build();
                }
            }
        }
        return instance;
    }
}
