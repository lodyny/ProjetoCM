package com.chubby.notsochubby.Models;

import android.graphics.drawable.Drawable;

import com.chubby.notsochubby.MuscleType;

public class Exercise {
    private String description;
    private String repeats;
    private Drawable image;
    private MuscleType type;

    public Exercise(String desc, MuscleType type,String repeats, Drawable image ) {
        this.description = desc;
        this.repeats = repeats;
        this.image = image;
        this.type = type;
    }

    public MuscleType getType() { return type; }

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
