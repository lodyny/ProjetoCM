package com.chubby.notsochubby.models.entities;

import android.arch.persistence.room.Entity;

@Entity(tableName = "news_categories")
public class NewsCategory extends Category {
    public NewsCategory(int id, String name) {
        super(id, name);
    }
}
