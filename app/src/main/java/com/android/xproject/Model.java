package com.android.xproject;

/**
 * Created by Jose on 1/30/2017.
 */

public class Model {


    public String description;
    public String location;
    public String name;
    public String photoUrl;

    public Model() {

    }

    public Model(String description, String location, String name, String photoUrl) {
        this.description = description;
        this.location = location;
        this.name = name;
        this.photoUrl = photoUrl;
    }
}
