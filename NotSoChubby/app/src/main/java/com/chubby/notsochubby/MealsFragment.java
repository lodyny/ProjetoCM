package com.chubby.notsochubby;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chubby.notsochubby.models.adapters.MealsAdapter;
import com.chubby.notsochubby.models.entities.Meal;
import com.chubby.notsochubby.viewmodels.MealsViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MealsFragment extends Fragment {

    Button decreaseCaloriesBtn;
    Button increaseCaloriesBtn;
    TextView caloriesText;

    public MealsFragment() {
        // Required empty public constructor
    }

    public static MealsFragment newInstance() {
        return new MealsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private boolean mBold = false;
    private boolean mItalic = false;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.test:
                if(item.isChecked()){
                    // If item already checked then unchecked it
                    item.setChecked(false);
                    mBold = false;
                }else{
                    // If item is unchecked then checked it
                    item.setChecked(true);
                    mBold = true;
                }
                updateTextView();
                return true;
        }
        return true;

    }

    protected void updateTextView(){
        //
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meals, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.rvMeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final MealsAdapter adapter = new MealsAdapter();
        recyclerView.setAdapter(adapter);
        MealsViewModel mealsViewModel = ViewModelProviders.of(this).get(MealsViewModel.class);

        decreaseCaloriesBtn = v.findViewById(R.id.fgm_decreaseButton);
        decreaseCaloriesBtn.setOnClickListener(decreaseCaloriesAct);

        increaseCaloriesBtn = v.findViewById(R.id.fgm_increaseButton);
        increaseCaloriesBtn.setOnClickListener(increaseCaloriesAct);

        caloriesText = v.findViewById(R.id.fgm_calories);


        mealsViewModel.getMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                adapter.submitList(meals);
            }
        });

        adapter.setOnItemClickListener(new MealsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meal meal) {
                Intent intent = new Intent(getActivity(), MealActivity.class);
                Bundle b = new Bundle();
                b.putInt(MealActivity.KEY, meal.getId());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        mealsViewModel.getMealsFromCategory(1);

        return v;
    }

    private Button.OnClickListener decreaseCaloriesAct = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int currentValue = Integer.parseInt(caloriesText.getText().toString());
            if(currentValue > 200){
                int newValue = currentValue - 200;
                caloriesText.setText(String.valueOf(newValue));
            }
        }
    };

    private Button.OnClickListener increaseCaloriesAct = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int currentValue = Integer.parseInt(caloriesText.getText().toString());
            if(currentValue < 4000){
                int newValue = currentValue + 200;
                caloriesText.setText(String.valueOf(newValue));
            }
        }
    };


}
