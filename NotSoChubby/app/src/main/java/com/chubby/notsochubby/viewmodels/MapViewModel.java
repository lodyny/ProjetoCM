package com.chubby.notsochubby.viewmodels;

import android.app.Application;

import com.chubby.notsochubby.models.ChubbyDatabase;
import com.chubby.notsochubby.models.dao.SpotDao;
import com.chubby.notsochubby.models.entities.SpotAndCategory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MapViewModel extends AndroidViewModel {
    private SpotDao spotDao;
    private LiveData<List<SpotAndCategory>> spots;


    public MapViewModel(@NonNull Application application) {
        super(application);
        ChubbyDatabase database = ChubbyDatabase.getInstance(application);
        this.spotDao = database.spotDao();
        this.spots = spotDao.getAllSpots();
    }

    public LiveData<List<SpotAndCategory>> getSpotsByCategory(int catId){
        spots = spotDao.getSpotsFromCategory(catId);
        return spots;
    }

    public LiveData<List<SpotAndCategory>> getAllSpots(){
        spots = spotDao.getAllSpots();
        return spots;
    }

    public LiveData<List<SpotAndCategory>> getSpots(){
        return spots;
    }
}
