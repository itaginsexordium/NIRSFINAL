package com.example.nirsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    MyItem item=new MyItem();
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public static final String  EXTRA_USERNAME="username";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database= FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);



        Intent intent = getIntent();
        String username =intent.getStringExtra(EXTRA_USERNAME);



        myRef = database.getReference("users").child(username).child("items").child("another");
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText price = (EditText) findViewById(R.id.price);
        final Button add = (Button) findViewById(R.id.add);
        final ListView items = (ListView) findViewById(R.id.items);
        final ItemsAdapter adapter = new ItemsAdapter();
        items.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyItem item = new MyItem(name.getText().toString(), Integer.valueOf(price.getText().toString()));
                adapter.add(item);
                myRef.push().setValue(item);
            }
        });
        Query myQuery = myRef.orderByChild("price");
        myQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MyItem item = dataSnapshot.getValue(MyItem.class);
                adapter.add(item);
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
        });
    }

    public void onclick(View view) {
        Intent intent= new Intent(this,RegisterActyvity.class);
    }

    private class ItemsAdapter extends ArrayAdapter<MyItem> {
        ItemsAdapter() {
            super(MainActivity.this, R.layout.item);
    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final View view = getLayoutInflater().inflate(R.layout.item, null);
            final MyItem item = getItem(position);
            ((TextView) view.findViewById(R.id.name)).setText(item.name);
            ((TextView) view.findViewById(R.id.price)).setText(String.valueOf(item.price));
            return view;
        }
    }
}
