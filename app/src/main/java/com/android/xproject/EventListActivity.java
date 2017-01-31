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

public class EventListActivity extends AppCompatActivity {
    private static final String TAG = "EventListActivity";
    public static String CATEGORY_KEY = "index";
    public static String CATEGORY_NAME = "category_name";
    private RecyclerView recyclerView;
    public String categoryName;

    private DatabaseReference rootRef;
    private DatabaseReference categoryRef;
    private ImageView eventImageUrl;

    private TextView categoryNameTextView;
//    private String photoUrl = "https://firebasestorage.googleapis.com/v0/b/my-awesome-project-f3c97.appspot.com/o/raceTrack.jpg?alt=media&token=2dcb437f-bafa-4fa7-b885-9df16c27b426";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        rootRef = FirebaseDatabase.getInstance().getReference().child("Categories");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        categoryName = getIntent().getStringExtra(CATEGORY_NAME);
        Log.d(TAG, "initRecyclerView: CATEGORY_NAME: " + categoryName);

        eventImageUrl = (ImageView) findViewById(R.id.eventImageUrl);

        categoryNameTextView = (TextView) findViewById(R.id.categoryNameTextView);
        categoryNameTextView.setText(categoryName);
        categoryRef = rootRef.child(categoryName); // ex. Music
        DatabaseReference eventRef = categoryRef.child("0");

        Log.d(TAG, "onCreate: EVENTREF" + eventRef);

        eventRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EventModel model = dataSnapshot.getValue(EventModel.class);
                String photoUrl = model.photo;
                Log.d(TAG, "onDataChange: " + photoUrl);
                Picasso.with(getApplicationContext())
                        .load(model.photo)
                        .into(eventImageUrl);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
