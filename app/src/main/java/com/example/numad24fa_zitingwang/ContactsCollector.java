package com.example.numad24fa_zitingwang;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Array;
import java.util.ArrayList;

public class ContactsCollector extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    DatabaseHelper db;
    ArrayList<String> contactIDs, contactNames, contactNumbers;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts_collector);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addContactButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsCollector.this, AddContact.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(ContactsCollector.this);
        contactIDs = new ArrayList<>();
        contactNames = new ArrayList<>();
        contactNumbers = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ContactsCollector.this, contactIDs, contactNames, contactNumbers);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ContactsCollector.this));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void storeDataInArrays(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            Snackbar.make(findViewById(R.id.main), "No data. ", Snackbar.LENGTH_SHORT).show();
        }
        else {
            while(cursor.moveToNext()){
                contactIDs.add(cursor.getString((0)));
                contactNames.add(cursor.getString((1)));
                contactNumbers.add(cursor.getString(2));
            }
        }
    }
}