package com.example.dollartorupees;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clicked1(View view){
        EditText dollar = (EditText) findViewById(R.id.dollarEditText);
        String valuefromApp = dollar.getText().toString();
        double value = Double.parseDouble(valuefromApp);
        double ans = value * 82.63;
        Toast.makeText(this, "$"+valuefromApp+" is", Toast.LENGTH_SHORT ).show();
        Toast.makeText(this, "₹"+ans,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}