package com.chubby.notsochubby.models.entities;

import java.util.Date;


public class CalendarEvents {
    private int id;
    private int day;
    private String eventName;
    private Date startDate;
    private Date endDate;

    public CalendarEvents(int day, String eventName, Date startDate, Date endDate) {
        this.day = day;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDay() {
        return day;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
