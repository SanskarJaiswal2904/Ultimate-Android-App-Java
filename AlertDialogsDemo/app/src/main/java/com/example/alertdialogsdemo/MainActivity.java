package com.example.alertdialogsdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

//the shared preference is not saving text
/////////////////////////////////////////////////ERROR///////////////////////////////////////////////////

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String fromText,result;
    SharedPreferences sharedPreferences;


    public boolean alert(String lang) {
        final int[] choice = new int[1];
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Language")
                .setMessage("Are you Sure to change Language?")

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice[0] = 0;
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice[0] = 1;
                        Toast.makeText(MainActivity.this, "Language selected " + lang, Toast.LENGTH_SHORT).show();
                        //textView.setText(lang);
                        fromText = lang;
                        sharedPreferences.edit().putString("language",fromText).apply();
                        result = sharedPreferences.getString("language","Error");
                        textView.setText(result);
                    }
                })
                .show();
        return choice[0] == 1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.hindi) {
            alert("Hindi");
            return true;
        }
        else if (item.getItemId() == R.id.bengali) {
            alert("Bengali");
            return true;
        }
        else if (item.getItemId() == R.id.english) {
            alert("English");
            return true;
        }
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            return false;
        }

//        Toast.makeText(this, ":-)", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.langTextView);
        sharedPreferences = this.getSharedPreferences("com.example.alertdialogsdemo", Context.MODE_PRIVATE);

    }
}