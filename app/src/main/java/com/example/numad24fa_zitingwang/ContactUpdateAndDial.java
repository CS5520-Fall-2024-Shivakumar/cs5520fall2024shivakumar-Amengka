package com.example.numad24fa_zitingwang;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class ContactUpdateAndDial extends AppCompatActivity {

    EditText contactNameInput, contactNumberInput;
    Button updateButton, deleteButton;

    String contactID, contactName, contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_update_and_dial);

        View mainView = findViewById(R.id.main);
        contactNameInput = findViewById(R.id.contactNameInputUpdate);
        contactNumberInput = findViewById(R.id.contactNumberInputUpdate);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        getAndSetIntentData(mainView);

        ActionBar ab = getSupportActionBar();
        if (ab != null) ab.setTitle(contactName);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(ContactUpdateAndDial.this);
                contactName = contactNameInput.getText().toString().trim();
                contactNumber = contactNumberInput.getText().toString().trim();
                db.updateData(contactID, contactName, contactNumber, view);

                hideKeyboard();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog(view);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void getAndSetIntentData(View view){
        if (getIntent().hasExtra("ID") && getIntent().hasExtra("Name") && getIntent().hasExtra("Number")){
            // getting data from intent
            contactID = getIntent().getStringExtra("ID");
            contactName = getIntent().getStringExtra("Name");
            contactNumber = getIntent().getStringExtra("Number");

            // setting data
            contactNameInput.setText(contactName);
            contactNumberInput.setText(contactNumber);
        }
        else{
            Snackbar.make(view, "No data", Snackbar.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + contactName + " ?");
        builder.setMessage("Are you sure you want to delete " + contactName + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper db = new DatabaseHelper(ContactUpdateAndDial.this);
                db.deleteOneRow(contactID, view);
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    private void hideKeyboard() {
        // Get the currently focused view, which will be the input field or other view
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}