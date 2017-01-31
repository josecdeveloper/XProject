package com.android.xproject;

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

    private TextView categoryNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        rootRef = FirebaseDatabase.getInstance().getReference();

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        categoryName = getIntent().getStringExtra(CATEGORY_NAME);
        Log.d(TAG, "initRecyclerView: CATEGORY_NAME: " + categoryName);

        categoryNameTextView = (TextView) findViewById(R.id.categoryNameTextView);
        categoryNameTextView.setText(categoryName);
        categoryRef = rootRef.child(categoryName); // Music
    }

//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView eventImage;
//        public TextView eventName;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            eventName = (TextView) itemView.findViewById(R.id.eventName);
//            eventImage = (ImageView) itemView.findViewById(R.id.eventImage);
//        }
//    }
}
