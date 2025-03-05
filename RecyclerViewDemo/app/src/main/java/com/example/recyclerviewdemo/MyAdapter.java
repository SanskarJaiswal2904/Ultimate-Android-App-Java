package com.example.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<Items>items;

    public MyAdapter(Context context, List<Items> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleView.setText(items.get(position).getTitle());
        holder.descriptionView.setText(items.get(position).getDescription());
        holder.authorView.setText(items.get(position).getAuthor());
        holder.publishedAtView.setText(items.get(position).getPublishedAt());
        holder.urlToImageView.setImageResource(items.get(position).getUrlToImage());

        holder.urlToImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "clicked on " +position, Toast.LENGTH_SHORT).show();
                Toast.makeText(context.getApplicationContext(), "clicked on " +holder.titleView.getText(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
