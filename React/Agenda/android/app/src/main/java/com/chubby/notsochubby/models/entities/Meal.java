package com.chubby.notsochubby.models.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;


@Entity(tableName = "meals",
        indices = @Index(value = "cat_id"))
public class Meal {
    @PrimaryKey
    private int id;
    private String meal_name;
    private String meal_intro;
    private String meal_recp;
    private String meal_pict;
    private int calories;
    private int cat_id;

    public Meal(int id, String meal_name, String meal_intro, String meal_recp, String meal_pict, int calories, int cat_id) {
        this.id = id;
        this.meal_name = meal_name;
        this.meal_intro = meal_intro;
        this.meal_recp = meal_recp;
        this.meal_pict = meal_pict;
        this.calories = calories;
        this.cat_id = cat_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public void setMeal_intro(String meal_intro) {
        this.meal_intro = meal_intro;
    }

    public void setMeal_recp(String meal_recp) {
        this.meal_recp = meal_recp;
    }

    public void setMeal_pict(String meal_pict) {
        this.meal_pict = meal_pict;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getId() {
        return id;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public String getMeal_intro() {
        return meal_intro;
    }

    public String getMeal_recp() {
        return meal_recp;
    }

    public String getMeal_pict() {
        return meal_pict;
    }

    public int getCalories() {
        return calories;
    }

    public int getCat_id() {
        return cat_id;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", meal_name='" + meal_name + '\'' +
                ", meal_intro='" + meal_intro + '\'' +
                ", meal_recp='" + meal_recp + '\'' +
                ", meal_pict='" + meal_pict + '\'' +
                ", calories=" + calories +
                ", cat_id=" + cat_id +
                '}';
    }
}
