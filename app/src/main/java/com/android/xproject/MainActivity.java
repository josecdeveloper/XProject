package com.android.xproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private DatabaseReference categoriesRef;
    private ArrayAdapter<String> categoriesAdapter;
    private ArrayList<String> listOfCategories = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        categoriesRef = databaseReference.child("Categories");

        categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfCategories);

        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(categoriesAdapter);

        categoriesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Get list of all children in the Categories reference
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()) {
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                listOfCategories.clear();
                listOfCategories.addAll(set);

                categoriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent event = new Intent(getApplicationContext(), EventListActivity.class);
                event.putExtra(EventListActivity.CATEGORY_KEY, position);
                event.putExtra(EventListActivity.CATEGORY_NAME, ((TextView)view).getText().toString());
                startActivity(event);
            }
        });


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

    }

}
