package com.chubby.notsochubby.Models.Entities;

import androidx.room.Entity;

@Entity(tableName = "news_categories")
public class NewsCategory extends Category {
    public NewsCategory(int id, String name) {
        super(id, name);
    }
}
