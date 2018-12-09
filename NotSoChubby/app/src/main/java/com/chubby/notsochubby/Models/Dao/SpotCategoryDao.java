package com.chubby.notsochubby.Models.Dao;

import com.chubby.notsochubby.Models.Entities.SpotCategory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
