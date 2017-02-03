package com.android.xproject;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose on 1/30/2017.
 */

@IgnoreExtraProperties
public class EventModel {

    public String address;
    public String date;
    public String description;
    public String end;
    public String image;
    public Double latitude;
    public String location;
    public Object longitude;
    public String name;
    public String price;
    public String short_description;
    public String start;

    public EventModel () { }

    public EventModel(String address, String date,
                      String description, String end, String image,
                      Double latitude, String location,
                      Object longitude, String name, String price,
                      String short_description, String start) {
        this.start = start;
        this.end = end;
        this.address = address;
        this.date = date;
        this.description = description;
        this.image = image;
        this.latitude = latitude;
        this.location = location;
        this.longitude = longitude;
        this.name = name;
        this.price = price;
        this.short_description = short_description;
    }






}
