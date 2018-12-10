package com.chubby.notsochubby;

import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chubby.notsochubby.models.entities.Meal;
import com.chubby.notsochubby.models.GlideApp;
import com.chubby.notsochubby.viewmodels.MealsDisplayFactory;
import com.chubby.notsochubby.viewmodels.MealsDisplayViewModel;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MealActivity extends AppCompatActivity {

    public static final String KEY = "KEYMEALID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        int mealId = -1;
        if (b != null)
            mealId = b.getInt(KEY);
        final WeakReference<MealActivity> activityReference = new WeakReference<>(this);
        MealsDisplayViewModel mealsViewModel = ViewModelProviders.of(this, new MealsDisplayFactory(this.getApplication(), mealId)).get(MealsDisplayViewModel.class);
        mealsViewModel.getMeals().observe(this, new Observer<Meal>() {
            @Override
            public void onChanged(Meal meal) {
                if(meal != null) {
                    ImageView ivMeal = findViewById(R.id.ivPict);
                    TextView tvName = findViewById(R.id.tvmealName);
                    TextView tvMealIntro = findViewById(R.id.tvMealIntro);
                    TextView tvMealRecp = findViewById(R.id.tvMealRecp);
                    TextView tvMealCal = findViewById(R.id.tvMealCal);

                    GlideApp.with(activityReference.get())
                            .load(meal.getMeal_pict())
                            .centerCrop()
                            .into(ivMeal);
                    tvName.setText(meal.getMeal_name());
                    tvMealIntro.setText(meal.getMeal_intro());
                    String cals = meal.getCalories() + " kcal";
                    tvMealCal.setText(cals);

                    String recpt = "<b>Confection</b> <br/>" + meal.getMeal_recp();
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        tvMealRecp.setText(Html.fromHtml(recpt, Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        tvMealRecp.setText(Html.fromHtml(recpt));
                    }
                } else {
                    Toast.makeText(activityReference.get(), "Não foi possível carregar a ementa.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
