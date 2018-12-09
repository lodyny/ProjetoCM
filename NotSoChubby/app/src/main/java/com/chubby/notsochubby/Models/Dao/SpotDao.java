package com.chubby.notsochubby.Models.Dao;

import com.chubby.notsochubby.Models.Entities.Spot;
import com.chubby.notsochubby.Models.Entities.SpotAndCategory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SpotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Spot> spots);

    @Update
    void update(Spot... spot);

    @Delete
    void delete(Spot... spot);

    @Query("SELECT spots.*, spots_categories.id as cat_id, spots_categories.name as cat_name " +
            "FROM spots INNER JOIN spots_categories ON spots.catId = spots_categories.id")
    LiveData<List<SpotAndCategory>> getAllSpots();

    @Query("SELECT spots.*, spots_categories.id as cat_id, spots_categories.name as cat_name " +
            "FROM spots INNER JOIN spots_categories ON spots.catId = spots_categories.id WHERE catId=:catId")
    LiveData<List<SpotAndCategory>> getSpotsFromCategory(final int catId);

    @Query("SELECT spots.*, spots_categories.id as cat_id, spots_categories.name as cat_name " +
            "FROM spots INNER JOIN spots_categories ON spots.catId = spots_categories.id WHERE spots.id=:spotId")
    LiveData<SpotAndCategory> getSpotsById(final int spotId);
}
