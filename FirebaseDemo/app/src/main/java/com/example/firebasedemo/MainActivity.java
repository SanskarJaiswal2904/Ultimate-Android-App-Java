package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Animation and Piechart not working

public class MainActivity extends AppCompatActivity {

    Button register, login, upload, animpie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        upload = findViewById(R.id.uploadImage);
        animpie = findViewById(R.id.animation_piechart);

        login.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LoginIn.class));
            finish();
        });
        register.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Register.class));
            finish();
        });
        upload.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, UploadImageActivity.class));
            finish();
        });
        animpie.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AnimateAndChartActivity.class));
            finish();
        });

    }
}