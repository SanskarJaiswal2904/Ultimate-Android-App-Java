package com.example.recyclerviewdemo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView titleView,descriptionView,authorView,publishedAtView;
    ImageView urlToImageView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.titleView);
        descriptionView = itemView.findViewById(R.id.descriptionView);
        authorView = itemView.findViewById(R.id.authorView);
        publishedAtView = itemView.findViewById(R.id.publishedAtView);
        urlToImageView = itemView.findViewById(R.id.urlToImageView);




    }
}
