package com.example.easygolive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button create_btn;
    TextInputEditText LiveIdInput, nameIdInput;
    String liveId, nameId, userId;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("name_pref",MODE_PRIVATE);

        LiveIdInput = findViewById(R.id.LiveId);
        nameIdInput = findViewById(R.id.nameId);
        create_btn = findViewById(R.id.createBtn);

        nameIdInput.setText(sharedPreferences.getString("name",""));


        LiveIdInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                liveId = LiveIdInput.getText().toString().trim();
                if (liveId.isEmpty()){
                    create_btn.setText("Create New Live");
                }
                else {
                    create_btn.setText("Join a Live");
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        create_btn.setOnClickListener(view -> {
            nameId = nameIdInput.getText().toString().trim();
            if (nameId.isEmpty()){
                nameIdInput.setError("Name is Required");
                nameIdInput.requestFocus();
                return;
            }

            liveId = LiveIdInput.getText().toString().trim();
            if ( !(liveId.isEmpty()) &&  liveId.length() != 5 ){
                LiveIdInput.setError("LiveID is Required");
                LiveIdInput.requestFocus();
                return;
            }
            startMeeting();
        });
    }
    private void startMeeting(){
        sharedPreferences.edit().putString("name",nameId).apply();
        boolean isHost = true;
        if (liveId.length()==5){
            isHost = false;
        }else {
            liveId = generateLiveId();
        }
        userId = UUID.randomUUID().toString();

        Intent intent = new Intent(MainActivity.this, LiveActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("name",nameId);
        intent.putExtra("liveId",liveId);
        intent.putExtra("host",isHost);
        startActivity(intent);
    }
    public String generateLiveId(){
        StringBuilder s = new StringBuilder();
        while (s.length() != 5){
            int num = new Random().nextInt(9);
            s.append(num);
        }
        return s.toString();
    }
}