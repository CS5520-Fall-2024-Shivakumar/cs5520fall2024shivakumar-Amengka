package com.example.numad24fa_zitingwang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> contactIDs, contactNames, contactNumbers;

    CustomAdapter(Context context, ArrayList<String> contactIDs, ArrayList<String> contactNames, ArrayList<String> contactNumbers){
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.contactIDText.setText(String.valueOf(contactIDs.get(position)));
        holder.contactNameText.setText(String.valueOf(contactNames.get(position)));
        holder.contactNumberText.setText(String.valueOf(contactNumbers.get(position)));
    }

    @Override
    public int getItemCount() {
        return contactIDs.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView contactIDText, contactNameText, contactNumberText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contactIDText = itemView.findViewById(R.id.contactIDText);
            contactNameText = itemView.findViewById(R.id.contactNameText);
            contactNumberText = itemView.findViewById(R.id.contactNumberText);
        }
    }
}
