package com.chubby.notsochubby.Models.Entities;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class NewsAndCategory {
    @Embedded
    public News news;

    @Embedded (prefix = "cat_")
    public NewsCategory category;

    public News getNews() {
        return news;
    }

    public NewsCategory getCategory() {
        return category;
    }
}
