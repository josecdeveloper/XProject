package com.android.xproject;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Jose on 1/30/2017.
 */

public class EventModel {


    public String date;
    public String description;
    public String name;
    public String photo;
//    public String location;
    public String media;


    public EventModel() { }

    public EventModel(String date, String description, String name, String photoUrl, String media) {
        this.date = date;
        this.description = description;
        this.name = name;
        this.photo = photoUrl;
//        this.location = location;
        this.media = media;
    }
}
