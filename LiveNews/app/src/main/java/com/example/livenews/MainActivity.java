package com.example.livenews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

////////////running without any error//////////////////////

public class MainActivity extends AppCompatActivity {
    static TextView loadingScreen;
    EditText topic;
    static String topicName = "";
    Button search;

    public void clicked(View view){
        topicName = String.valueOf(topic.getText());
        search.setVisibility(View.INVISIBLE);
        topic.setVisibility(View.INVISIBLE);
        //to hide keyboard
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(topic.getWindowToken(),0);

        loadingScreen.setText("Loading Please Wait...");
        loadingScreen.setVisibility(View.VISIBLE);
        Intent intent = new Intent(getApplicationContext(),topNewsActivity.class);
        intent.putExtra("topic name",topicName);
        startActivity(intent);

        loadingScreen.setVisibility(View.INVISIBLE);
        search.setVisibility(View.VISIBLE);
        topic.setVisibility(View.VISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingScreen = findViewById(R.id.loading);
        topic = findViewById(R.id.city);
        search = findViewById(R.id.search);
    }
}