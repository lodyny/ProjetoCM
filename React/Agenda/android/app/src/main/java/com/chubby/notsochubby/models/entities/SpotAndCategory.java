package com.chubby.notsochubby.models.entities;


import android.arch.persistence.room.Embedded;

public class SpotAndCategory {
    @Embedded
    public Spot spot;

    @Embedded (prefix = "cat_")
    public SpotCategory category;

    public Spot getNews() {
        return spot;
    }

    public SpotCategory getCategory() {
        return category;
    }
}
