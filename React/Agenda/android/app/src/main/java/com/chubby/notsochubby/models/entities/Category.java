package com.chubby.notsochubby.models.entities;


import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;


public class Category {
    @PrimaryKey
    @Expose
    private int id;
    @Expose
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
