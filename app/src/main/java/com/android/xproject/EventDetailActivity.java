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

    public static final String EVENT_NAME = "event_name";
    public static final String EVENT_DATE = "event_date";
    public static final String EVENT_DESCRIPTION = "event_description";
    public static final String EVENT_START = "event_start";
    public static final String EVENT_IMAGE = "event_image";
    public static final String EVENT_PRICE = "event_price";
    public static final String EVENT_END = "event_end";
    public static final String EVENT_ADDRESS = "event_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        String eventName = (String) getIntent().getExtras().get(EVENT_NAME);
        String eventDate = (String) getIntent().getExtras().get(EVENT_DATE);
        String eventStart = (String) getIntent().getExtras().get(EVENT_START);
        String eventImage = (String) getIntent().getExtras().get(EVENT_IMAGE);
        String eventDescription = (String) getIntent().getExtras().get(EVENT_DESCRIPTION);
        String eventPrice = (String) getIntent().getExtras().get(EVENT_PRICE);


        eventDescriptionTV = (TextView) findViewById(R.id.eventDescriptionDetail);
        eventImageIV = (ImageView) findViewById(R.id.eventImageDetail);

        eventDescriptionTV.setText(eventDescription);
        Picasso.with(this)
                .load(eventImage)
                .placeholder(R.drawable.ic_photo_placeholder)
                .into(eventImageIV);

        getSupportActionBar().setTitle(eventName);


        Log.d(TAG, "onCreate: EVENT_NAME" + eventName);




    }

}
