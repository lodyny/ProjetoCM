package com.chubby.notsochubby.models.entities;

import androidx.room.Embedded;

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
