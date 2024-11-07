package com.example.numad24fa_zitingwang;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
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
    ImageView emptyImageView;
    TextView noDataText;

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
        emptyImageView = findViewById(R.id.emptyImageView);
        noDataText = findViewById(R.id.noDataText);

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

        customAdapter = new CustomAdapter(ContactsCollector.this, this, contactIDs, contactNames, contactNumbers);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ContactsCollector.this));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            emptyImageView.setVisibility(View.VISIBLE);
            noDataText.setVisibility(View.VISIBLE);
        }
        else {
            while(cursor.moveToNext()){
                contactIDs.add(cursor.getString((0)));
                contactNames.add(cursor.getString((1)));
                contactNumbers.add(cursor.getString(2));
            }
            emptyImageView.setVisibility(View.GONE);
            noDataText.setVisibility(View.GONE);
        }
    }
}