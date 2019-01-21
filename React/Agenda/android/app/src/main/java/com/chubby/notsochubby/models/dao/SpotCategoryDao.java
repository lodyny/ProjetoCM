package com.chubby.notsochubby.models.dao;

import com.chubby.notsochubby.models.entities.SpotCategory;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface SpotCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<SpotCategory> categories);

    @Update
    void update(SpotCategory... category);

    @Delete
    void delete(SpotCategory... category);

    @Query("SELECT * FROM spots_categories ORDER BY name")
    LiveData<List<SpotCategory>> getAllCategories();

    @Query("SELECT * FROM spots_categories WHERE id=:catId")
    LiveData<SpotCategory> getCategoryById(final int catId);

}
