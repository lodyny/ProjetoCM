
package com.chubby.notsochubby.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.chubby.notsochubby.models.ChubbyDatabase;
import com.chubby.notsochubby.models.dao.MealsDao;
import com.chubby.notsochubby.models.entities.Meal;


public class MealsDisplayViewModel extends AndroidViewModel {

    private MealsDao mealsDao;
    private LiveData<Meal> meals;

    public MealsDisplayViewModel(@NonNull Application application, int mealsId) {
        super(application);
        ChubbyDatabase database = ChubbyDatabase.getInstance(application);
        this.mealsDao = database.mealsDao();
        this.meals = mealsDao.getMealById(mealsId);
    }

    public LiveData<Meal> getMeals(){
        return meals;
    }
}
