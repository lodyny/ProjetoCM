package com.chubby.notsochubby;

import android.graphics.drawable.Drawable;

public class Exercise {
    private String description;
    private String repeats;
    private Drawable image;

    public Exercise(String desc, String repeats, Drawable image ) {
        this.description = desc;
        this.repeats = repeats;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public String getRepeats() {
        return repeats;
    }

    public Drawable getImage() {
        return image;
    }
}
