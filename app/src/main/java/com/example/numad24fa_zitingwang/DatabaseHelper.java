package com.example.numad24fa_zitingwang;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ContactCollector.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_contacts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CONTACTNAME = "contact_name";
    private static final String COLUMN_CONTACTNUMBER = "contact_number";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_CONTACTNAME + " TEXT, " +
                        COLUMN_CONTACTNUMBER + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addContact(String contactName, String contactNumber, View view){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CONTACTNAME, contactName);
        cv.put(COLUMN_CONTACTNUMBER, contactNumber);


        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Snackbar.make(view, "Failed", Snackbar.LENGTH_SHORT).show();
        }
        else {
            Snackbar.make(view, "Added Successfully!", Snackbar.LENGTH_SHORT).show();
        }
    }
}
