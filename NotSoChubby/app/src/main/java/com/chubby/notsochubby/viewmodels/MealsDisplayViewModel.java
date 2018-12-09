
package com.chubby.notsochubby.ViewModels;

        import android.app.Application;

        import com.chubby.notsochubby.Models.ChubbyDatabase;
        import com.chubby.notsochubby.Models.Dao.MealsDao;
        import com.chubby.notsochubby.Models.Entities.Meal;

        import androidx.annotation.NonNull;
        import androidx.lifecycle.AndroidViewModel;
        import androidx.lifecycle.LiveData;

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
