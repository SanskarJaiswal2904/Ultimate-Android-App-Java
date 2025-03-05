package com.example.animateanychart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dont know where to add this repository
        /*
        * allprojects {
        repositories {
                ...
                maven { url 'https://jitpack.io' }
        }
}
        * */
    }
}