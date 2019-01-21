package com.chubby.notsochubby.models.entities;



import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = "spots", foreignKeys = @ForeignKey(entity = SpotCategory.class,
        parentColumns = "id",
        childColumns = "catId",
        onDelete = CASCADE),
        indices = @Index(value = "catId"))
public class Spot {
    @PrimaryKey
    private int id;
    private int catId;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String country;
    private double geolat;
    private double geolon;

    public Spot(int id, int catId, String name, String address, String zipcode, String city, String country, double geolat, double geolon) {
        this.id = id;
        this.catId = catId;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.geolat = geolat;
        this.geolon = geolon;
    }

    public int getId() {
        return id;
    }

    public int getCatId() {
        return catId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getGeolat() {
        return geolat;
    }

    public double getGeolon() {
        return geolon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGeolat(double geolat) {
        this.geolat = geolat;
    }

    public void setGeolon(double geolon) {
        this.geolon = geolon;
    }

    public String getFullAddress(){
        return address + " " + zipcode + " " + city;
    }

}
