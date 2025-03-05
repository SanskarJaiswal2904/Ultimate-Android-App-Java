package com.example.livenews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class topNewsActivity extends AppCompatActivity {
    String apiKey = "09c7e4b53f22489da3c83b866aa8b344";
    String urlNews;

    String jsonString;

    ArrayList<String> titleList;
    ArrayList<String> authorList;
    ArrayList<String> descriptionList;
    public static ArrayList<String> urlToImageList;
    ArrayList<String> publishedAtList;
    public static ArrayList<String> URLList;
    public static RecyclerView recyclerView;

    public static WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);
        Bundle extras = getIntent().getExtras();

        titleList = new ArrayList<>();
        URLList = new ArrayList<>();
        authorList = new ArrayList<>();
        descriptionList = new ArrayList<>();
        urlToImageList = new ArrayList<>();
        publishedAtList = new ArrayList<>();


        myWebView = (WebView) findViewById(R.id.webview);
        recyclerView = findViewById(R.id.recyclerView);

        String value = extras.getString("topic name");
        String encodedTopicName;

        //to handle space in topic name, no use to do it
        try {
             encodedTopicName = URLEncoder.encode(value,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        urlNews = "https://newsapi.org/v2/everything?q=" + encodedTopicName + "&apiKey=" + apiKey;
        Log.i("url",urlNews);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlNews)
                .build();

        client.newCall(request).enqueue(new Callback() {  //to run on a branch not in main thread
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    jsonString = response.body().string();
                    extractTitle_URL_everything(jsonString);
                    topNewsActivity.this.runOnUiThread(new Runnable() { //to run in main activity
                        @Override
                        public void run() {
                            myWebView.setVisibility(View.INVISIBLE);

//                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                                    String urlAtThatIndex = URLList.get(i);
//
//                                    listView.setVisibility(View.INVISIBLE);
//                                    myWebView.setVisibility(View.VISIBLE);
//
//                                    myWebView.getSettings().setJavaScriptEnabled(true); // run html that has javascript ALSO
//                                    myWebView.setWebViewClient(new WebViewClient()); // don't open the default browser, open the web view in my app
//                                    myWebView.loadUrl(urlAtThatIndex);
//
//                                    Log.i("urlAtThatIndex",urlAtThatIndex);
//                                }
//                            });
                            RecyclerView recyclerView = findViewById(R.id.recyclerView);

                            List<Items> items = new ArrayList<>();

                            for (int i = 0; i < authorList.size(); i++) {
                                items.add(new Items(authorList.get(i), titleList.get(i), descriptionList.get(i), publishedAtList.get(i), urlToImageList.get(i)));
                            }

                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
                        }
                    });
                }
            }


            //extract title and url and everything from the json
            private void extractTitle_URL_everything(String jsonString) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);

                    if ("ok".equals(jsonObject.getString("status"))) {
                        JSONArray articlesArray = jsonObject.getJSONArray("articles");

                        for (int i = 0; i < articlesArray.length(); i++) {
                            JSONObject articleObject = articlesArray.getJSONObject(i);

                            String title = articleObject.getString("title");
                            String url = articleObject.getString("url");
                            String author = articleObject.getString("author");
                            String description = articleObject.getString("description");
                            String urlToImage = articleObject.getString("urlToImage");
                            String publishedAt = articleObject.getString("publishedAt");

                            if (urlToImage.equals(null)){
                                urlToImage = "https://st4.depositphotos.com/14953852/22772/v/450/depositphotos_227724992-stock-illustration-image-available-icon-flat-vector.jpg";
                            }

                            titleList.add(title);
                            authorList.add(author);
                            descriptionList.add(description);
                            publishedAtList.add(publishedAt);
                            urlToImageList.add(urlToImage);
                            URLList.add(url);
                        }
                    } else {
                        Log.i("ERROR_LOG","Error: Status is not 'ok'");
                    }

                    for (int i = 0; i < titleList.size(); i++) {
                        Log.i("title",titleList.get(i));
                        Log.i("URL",URLList.get(i));
                        Log.i("author",authorList.get(i));
                        Log.i("description",descriptionList.get(i));
                        Log.i("urlToImage",urlToImageList.get(i));
                        Log.i("publishedAt",publishedAtList.get(i));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}