package com.example.numad24fa_zitingwang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
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


    DatabaseHelper(@Nullable Context context) {
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
            Snackbar snackbar = Snackbar.make(view, "Failed to Add", Snackbar.LENGTH_SHORT);
            snackbar.setAction("Close", v->{
                snackbar.dismiss();;
            }).show();
        }
        else {
            Snackbar snackbar = Snackbar.make(view, "Successfully Added!", Snackbar.LENGTH_SHORT);
            snackbar.setAction("Close", v->{
                snackbar.dismiss();;
            }).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id, String contactName, String contactNumber, View view){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CONTACTNAME, contactName);
        cv.put(COLUMN_CONTACTNUMBER, contactNumber);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1){
            Snackbar snackbar = Snackbar.make(view, "Failed to Update", Snackbar.LENGTH_SHORT);
            snackbar.setAction("Close", v->{
                snackbar.dismiss();;
            }).show();
        }
        else{
            Snackbar snackbar = Snackbar.make(view, "Successfully Updated!", Snackbar.LENGTH_SHORT);
            snackbar.setAction("Close", v->{
                snackbar.dismiss();;
            }).show();
        }
    }

    void deleteOneRow(String row_id, View view){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1){
            Snackbar snackbar = Snackbar.make(view, "Failed to Delete", Snackbar.LENGTH_SHORT);
            snackbar.setAction("Close", v->{
                snackbar.dismiss();;
            }).show();
        }
        else {
            Snackbar snackbar = Snackbar.make(view, "Successfully Deleted!", Snackbar.LENGTH_SHORT);
            snackbar.setAction("Close", v->{
                snackbar.dismiss();;
            }).show();
        }
    }
}
