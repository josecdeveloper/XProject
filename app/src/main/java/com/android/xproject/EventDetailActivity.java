package com.android.xproject;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EventDetailActivity extends AppCompatActivity {
    private static final String TAG = "EventDetailActivity";

    private TextView eventDescriptionTV;
    private ImageView eventImageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        String eventName = (String) getIntent().getExtras().get("event_name");
        String eventDate = (String) getIntent().getExtras().get("event_date");
        String eventStart = (String) getIntent().getExtras().get("event_start");
        String eventImage = (String) getIntent().getExtras().get("event_image");
        String eventDescription = (String) getIntent().getExtras().get("event_description");
        String eventPrice = (String) getIntent().getExtras().get("event_price");


        eventDescriptionTV = (TextView) findViewById(R.id.eventDescriptionDetail);
        eventImageIV = (ImageView) findViewById(R.id.eventImageDetail);

        eventDescriptionTV.setText(eventDescription);
        Picasso.with(this)
                .load(eventImage)
                .into(eventImageIV);

        getSupportActionBar().setTitle(eventName);


        Log.d(TAG, "onCreate: EVENT_NAME" + eventName);




    }

}
