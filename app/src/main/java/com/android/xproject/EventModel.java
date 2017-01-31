package com.android.xproject;

/**
 * Created by Jose on 1/30/2017.
 */

public class EventModel {


    public static String date;
    public static String description;
    public static String name;
    public static String photoUrl;
    public static String location;
    public static String media;
    public static String mediaUrl;

    public EventModel(String date, String description, String name, String photoUrl, String location, String media, String mediaUrl) {
        this.date = date;
        this.description = description;
        this.name = name;
        this.photoUrl = photoUrl;
        this.location = location;
        this.media = media;
        this.mediaUrl = mediaUrl;
    }
}
