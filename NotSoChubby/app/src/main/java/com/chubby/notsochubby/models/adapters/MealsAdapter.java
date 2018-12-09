package com.chubby.notsochubby.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chubby.notsochubby.models.entities.Meal;
import com.chubby.notsochubby.models.GlideApp;
import com.chubby.notsochubby.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MealsAdapter extends ListAdapter<Meal, MealsAdapter.MealViewHolder> {

    private OnItemClickListener listener;

    public MealsAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Meal> DIFF_CALLBACK = new DiffUtil.ItemCallback<Meal>() {
        @Override
        public boolean areItemsTheSame(@NonNull Meal oldItem, @NonNull Meal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Meal oldItem, @NonNull Meal newItem) {
            return oldItem.getMeal_intro().equals(newItem.getMeal_intro()) &&
                    oldItem.getMeal_name().equals(newItem.getMeal_name()) &&
                    oldItem.getMeal_pict().equals(newItem.getMeal_pict()) &&
                    oldItem.getMeal_recp().equals(newItem.getMeal_recp()) &&
                    oldItem.getCalories() == newItem.getCalories() &&
                    oldItem.getCat_id() == newItem.getCat_id();
        }
    };

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item, parent, false); //layout da row
        return new MealViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal currentMeal = getMealAt(position);
        Context ctx = holder.itemView.getContext();
        String current_category;
        switch(currentMeal.getCat_id()) {
            case 1:
                current_category = ctx.getResources().getString(R.string.fgm_meals_breakfast);
                break;
            case 2:
                current_category = ctx.getResources().getString(R.string.fgm_meals_lunch);
                break;
            case 3:
                current_category = ctx.getResources().getString(R.string.fgm_meals_dinner);
                break;
            default:
                current_category = ctx.getResources().getString(R.string.fgm_meals_supper);
                break;
        }
        holder.tvMealIntro.setText(currentMeal.getMeal_intro());
        holder.tvMealName.setText(currentMeal.getMeal_name());

        holder.tvMealCat.setText(current_category);

        GlideApp.with(holder.ivMeal.getContext())
                .load(currentMeal.getMeal_pict())
                .centerCrop()
                .into(holder.ivMeal);
    }

    private Meal getMealAt(int position) {
        return getItem(position);
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMealIntro;
        public TextView tvMealName;
        public TextView tvMealCat;
        public ImageView ivMeal;

        MealViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvMealIntro = itemView.findViewById(R.id.tvMealIntro);
            this.tvMealName = itemView.findViewById(R.id.tvMealName);
            this.tvMealCat = itemView.findViewById(R.id.tvMealCat);
            this.ivMeal = itemView.findViewById(R.id.ivMeal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Meal meal);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
