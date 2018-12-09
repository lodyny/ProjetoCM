package com.chubby.notsochubby.Models.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chubby.notsochubby.Models.Entities.CalendarEvents;
import com.chubby.notsochubby.R;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    private ArrayList<CalendarEvents> mEventNames;
    private Context mContext;

    public CalendarAdapter(Context context, ArrayList<CalendarEvents> eventNames) {
        this.mEventNames = eventNames;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CalendarAdapter.CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.layout_calendar_list_template,viewGroup,false);
        return new CalendarAdapter.CalendarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder calendarViewHolder, int i) {
        String name = mEventNames.get(i).getEventName();
        calendarViewHolder.mTextViewName.setText(name);
    }


    @Override
    public int getItemCount() {
        return mEventNames.size();
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        public CalendarViewHolder(View v) {
            super(v);
            mTextViewName = v.findViewById(R.id.textViewDesc);
        }
    }
}
