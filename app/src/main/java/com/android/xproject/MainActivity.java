package com.android.xproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "#MainActivity";
    private DatabaseReference root;
    private DatabaseReference pagesRoot;
    private DatabaseReference eventRef;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = FirebaseDatabase.getInstance().getReference().child(Const.PROJECT_ROOT);
//
//        categoriesRef = databaseReference.child("Categories");
//
//        categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfCategories);
//
//        listView = (ListView) findViewById(R.id.listview);
//        listView.setAdapter(categoriesAdapter);
//
//        categoriesRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                //Get list of all children in the Categories reference
//                Set<String> set = new HashSet<String>();
//                Iterator i = dataSnapshot.getChildren().iterator();
//                while(i.hasNext()) {
//                    set.add(((DataSnapshot)i.next()).getKey());
//                }
//                listOfCategories.clear();
//                listOfCategories.addAll(set);
//
//                categoriesAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent event = new Intent(getApplicationContext(), EventDetailActivity.class);
//                event.putExtra(EventDetailActivity.CATEGORY_KEY, position);
//                event.putExtra(EventDetailActivity.CATEGORY_NAME, ((TextView)view).getText().toString());
//                startActivity(event);
//            }
//        });


//        DatabaseReference culture = categoriesRef.child("Culture").child("0");
//
//        culture.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Model model = dataSnapshot.getValue(Model.class);
//                System.out.println(model.description);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.err.println(databaseError);
//            }
//        });
        pagesRoot = root.child(Const.PAGES);
        Log.d(TAG, "onCreate: Pages Root: " + pagesRoot);

        pagesRoot.addChildEventListener(pagesEventListener);

        getSupportActionBar().setTitle("Current Events");

    }

    ChildEventListener pagesEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            String key = dataSnapshot.getKey();
            eventRef = pagesRoot.child(key);

            EventModel model = dataSnapshot.getValue(EventModel.class);

            Log.d(TAG, "onChildAdded: EVENT_REF" + eventRef);
            Log.d(TAG, "onChildAdded: KEY: " + key);
            Log.d(TAG, "onChildAdded: Event Name: " + model.name);

            recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            FirebaseRecyclerAdapter<EventModel, EventViewHolder> adapter =
                    new FirebaseRecyclerAdapter<EventModel, EventViewHolder>(
                            EventModel.class,
                            R.layout.item_view,
                            EventViewHolder.class,
                            pagesRoot

                    ) {
                        @Override
                        protected void populateViewHolder(EventViewHolder viewHolder, EventModel model, int position) {
                            final String name = model.name;
                            final String start = model.start;
                            final String address = model.address;
                            final String end = model.end;
                            final String date = model.date;
                            final String image = model.image;
                            final String description = model.description;
                            Double latitude = model.latitude;
                            Object longitude = model.longitude;
                            String location = model.location;
                            final String price = model.price;

                            Log.d(TAG, "populateViewHolder: " + name);

                            viewHolder.eventNameTV.setText(name);
                            Picasso.with(getApplicationContext())
                                    .load(image)
                                    .into(viewHolder.eventImageIV);
                            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent eventDetails = new Intent(getApplicationContext(), EventDetailActivity.class);
                                    eventDetails.putExtra("event_name", name);
                                    eventDetails.putExtra("event_start", start);
                                    eventDetails.putExtra("event_image", image);
                                    eventDetails.putExtra("event_end", end);
                                    eventDetails.putExtra("event_address", address);
                                    eventDetails.putExtra("event_date", date);
                                    eventDetails.putExtra("event_description", description);
                                    eventDetails.putExtra("event_price", price);
                                    startActivity(eventDetails);


                                }
                            });
                        }
                    };
            recyclerView.setAdapter(adapter);

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView eventNameTV;
        public ImageView eventImageIV;
        public CardView cardView;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventNameTV = (TextView) itemView.findViewById(R.id.eventNameTV);
            eventImageIV = (ImageView) itemView.findViewById(R.id.eventImageIV);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}


























