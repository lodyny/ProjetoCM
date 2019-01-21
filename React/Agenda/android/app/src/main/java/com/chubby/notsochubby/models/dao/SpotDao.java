package com.chubby.notsochubby.models.dao;

import com.chubby.notsochubby.models.entities.Spot;
import com.chubby.notsochubby.models.entities.SpotAndCategory;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
