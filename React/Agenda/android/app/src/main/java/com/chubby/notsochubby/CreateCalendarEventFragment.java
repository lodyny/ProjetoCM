package com.chubby.notsochubby;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;



public class CreateCalendarEventFragment extends Fragment {
    private View v;
    private TextView textView;
    private DatePickerDialog dateDialog;
    private TimePickerDialog timeDialog;
    private StringBuilder date;
    private Calendar calendar;
    private int mMonth;
    private int mDay;
    private int mYear;
    private Fragment fragment;
    private FragmentManager fm;
    private FragmentTransaction ft;

    private View.OnClickListener textViewDateClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            putDates(v);
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_create_update_calendar_event,null);
        setHasOptionsMenu(true);
        TextView textViewStartDate=(TextView)v.findViewById(R.id.textViewStartDatePicker);
        textViewStartDate.setOnClickListener(textViewDateClick);
        TextView textViewEndDate = (TextView)v.findViewById(R.id.textViewEndDatePicker);
        textViewEndDate.setOnClickListener(textViewDateClick);
        calendar = Calendar.getInstance();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void putDates(View v){
        date = new StringBuilder();
        textView = (TextView)v;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
        dateDialog = new DatePickerDialog(getContext(), R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mDay = dayOfMonth;
                mMonth = month + 1;
                mYear = year;
                date.append(dayOfMonth+"/"+month+"/"+year);
                final int hour =calendar.get(Calendar.HOUR);
                int minutes = calendar.get(Calendar.MINUTE);
                timeDialog = new TimePickerDialog(getContext(),R.style.DialogTheme ,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.append(" "+hourOfDay+":"+minute);
                        textView.setText(date.toString());
                    }
                },hour,minutes,true);
                timeDialog.show();
            }
        },mYear,mMonth,mDay);
        dateDialog.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_calendar_event, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_save_event:
            //TODO aceder a BD e por os registos la ou editalos
            return true;
            case R.id.menu_cancel_event:
                fragment = new CalendarFragment();
                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container,fragment);
                ft.commit();
                return true;
        }
        return false;
    }
}
