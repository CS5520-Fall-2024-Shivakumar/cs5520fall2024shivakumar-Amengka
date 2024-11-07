package com.example.numad24fa_zitingwang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList<String> contactIDs, contactNames, contactNumbers;

    Animation translateAnim;

    CustomAdapter(Activity activity, Context context, ArrayList<String> contactIDs, ArrayList<String> contactNames, ArrayList<String> contactNumbers){
        this.activity = activity;
        this.context = context;
        this.contactIDs = contactIDs;
        this.contactNames = contactNames;
        this.contactNumbers = contactNumbers;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.contactIDText.setText(String.valueOf(contactIDs.get(position)));
        holder.contactNameText.setText(String.valueOf(contactNames.get(position)));
        holder.contactNumberText.setText(String.valueOf(contactNumbers.get(position)));
        holder.mainLayout.setOnClickListener((view) -> {
            Intent intent = new Intent(context, ContactUpdateAndDial.class);
            intent.putExtra("ID", String.valueOf(contactIDs.get(position)));
            intent.putExtra("Name", String.valueOf(contactNames.get(position)));
            intent.putExtra("Number", String.valueOf(contactNumbers.get(position)));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return contactIDs.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView contactIDText, contactNameText, contactNumberText;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contactIDText = itemView.findViewById(R.id.contactIDText);
            contactNameText = itemView.findViewById(R.id.contactNameText);
            contactNumberText = itemView.findViewById(R.id.contactNumberText);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            // animate recyclerView
            translateAnim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translateAnim);
        }
    }
}
