package com.chubby.notsochubby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;


import com.chubby.notsochubby.models.adapters.CalendarAdapter;
import com.chubby.notsochubby.models.entities.CalendarEvents;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.Locale;



public class CalendarFragment extends Fragment {
    private CalendarView calendarView;
    private View v;
    private RecyclerView recyclerView;
    private TextView textViewCurrDate;
    private TextView textViewDayOfWeek;


    public static CalendarFragment newInstance(){return new CalendarFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_calendar, container, false);
        setHasOptionsMenu(true);
        recyclerView = v.findViewById(R.id.recyclerViewCallendar);
        calendarView = v.findViewById(R.id.calendarView);
        textViewCurrDate = (TextView)v.findViewById(R.id.textViewCurrentDay);
        textViewCurrDate.setText(String.format(Locale.getDefault(), "%d",LocalDate.now().getDayOfMonth()));
        textViewDayOfWeek = (TextView)v.findViewById(R.id.textViewDayOfWeek);
        textViewDayOfWeek.setText(getDayOfWeek());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //TODO aceder a base de dados e retornar todos os eventos daquele dia
            }
        });
        ArrayList<CalendarEvents> eventNames = new ArrayList<>();
        CalendarAdapter mCalendarAdapter = new CalendarAdapter(getContext(),eventNames);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewCallendar);
        recyclerView.setClickable(true);
        recyclerView.setAdapter(mCalendarAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        return v;
    }

    private String getDayOfWeek(){
        switch (LocalDate.now().getDayOfWeek()){
            case MONDAY:
                return getString(R.string.calendar_monday);
            case TUESDAY:
                return getString(R.string.calendar_tuesday);
            case WEDNESDAY:
                return getString(R.string.calendar_wednesday);
            case THURSDAY:
                return getString(R.string.calendar_thursday);
            case FRIDAY:
                return getString(R.string.calendar_friday);
            case SATURDAY:
                return getString(R.string.calendar_saturday);
            case SUNDAY:
                return getString(R.string.calendar_sunday);
        }
        return null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_new_calendar_event, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_add_event){
            Fragment fragment = new CreateCalendarEventFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}