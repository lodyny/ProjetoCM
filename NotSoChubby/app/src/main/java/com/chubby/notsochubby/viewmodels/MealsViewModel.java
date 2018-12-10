package com.chubby.notsochubby.viewmodels;

import android.app.Application;

import com.chubby.notsochubby.models.ChubbyDatabase;
import com.chubby.notsochubby.models.dao.MealsDao;
import com.chubby.notsochubby.models.entities.Meal;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MealsViewModel extends AndroidViewModel {

    private MealsDao mealsDao;
    private LiveData<List<Meal>> meals;

    public MealsViewModel(@NonNull Application application) {
        super(application);
        ChubbyDatabase database = ChubbyDatabase.getInstance(application);
        this.mealsDao = database.mealsDao();
        this.meals = mealsDao.getAllMeals();
    }

    public LiveData<List<Meal>> getAllMeals(){
        meals = mealsDao.getAllMeals();
        return meals;
    }

    public LiveData<List<Meal>> getMealsFromCategory(int catId){
        meals = mealsDao.getMealsFromCategory(catId);
        return meals;
    }

    public LiveData<List<Meal>> getMeals(){
        return meals;
    }
}