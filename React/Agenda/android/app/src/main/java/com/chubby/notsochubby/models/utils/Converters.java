package com.chubby.notsochubby.models.utils;

import android.arch.persistence.room.TypeConverter;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;



public class Converters {
    @TypeConverter
    public static LocalDateTime fromTimestamp(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(value, formatter);
    }

    @TypeConverter
    public static String calendarToTimestamp(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    public static String calendarToDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

}