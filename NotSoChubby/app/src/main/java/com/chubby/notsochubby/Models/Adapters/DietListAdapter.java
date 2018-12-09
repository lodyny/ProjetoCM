package com.chubby.notsochubby.Models.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

public class DietListAdapter extends ArrayAdapter {

    private String mealName;
    private String foodName;
    private String desc;
    private String calories;

    public DietListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
