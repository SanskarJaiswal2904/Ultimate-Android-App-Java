package com.example.livenews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in); // in place of inputStreamReader to download html, we will use this to download image
                return myBitmap;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }
    Context context;
    List<Items> items;

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

        //Async to download image
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try {
            // copy image address, it shall end with jpeg,jpg,png,etc
            myImage = task.execute(topNewsActivity.urlToImageList.get(position)).get();
            holder.urlToImageView.setImageBitmap(myImage);
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.descriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "clicked on " +holder.titleView.getText(), Toast.LENGTH_SHORT).show();

                String urlAtThatIndex = topNewsActivity.URLList.get(position);

                topNewsActivity.recyclerView.setVisibility(View.INVISIBLE);
                topNewsActivity.myWebView.setVisibility(View.VISIBLE);

                topNewsActivity.myWebView.getSettings().setJavaScriptEnabled(true); // run html that has javascript ALSO
                topNewsActivity.myWebView.setWebViewClient(new WebViewClient()); // don't open the default browser, open the web view in my app
                topNewsActivity.myWebView.loadUrl(urlAtThatIndex);

                Log.i("urlAtThatIndex",urlAtThatIndex);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
