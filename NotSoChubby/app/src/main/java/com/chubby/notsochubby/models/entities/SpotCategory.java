package com.chubby.notsochubby.models.entities;


import androidx.room.Entity;

@Entity(tableName = "spots_categories")
public class SpotCategory extends Category {
    public SpotCategory(int id, String name) {
        super(id, name);
    }
}
