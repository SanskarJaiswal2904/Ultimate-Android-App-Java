 package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

 public class Register extends AppCompatActivity {

     EditText email, password;
     Button register;
     FirebaseAuth auth;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_register);

         email = findViewById(R.id.email);
         password = findViewById(R.id.password);
         register = findViewById(R.id.register);
         auth = FirebaseAuth.getInstance();

         register.setOnClickListener(view -> {
             String emailTxt = email.getText().toString().trim();
             String passwordTxt = password.getText().toString();
             authenticate(emailTxt, passwordTxt);
         });
     }

     private void authenticate(String emailTxt, String passwordTxt) {
         auth.createUserWithEmailAndPassword(emailTxt, passwordTxt).addOnCompleteListener(Register.this,new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful()) {
                     Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(Register.this, Homepage.class));
                     finish();
                 }else {
                     Toast.makeText(Register.this, "Register Unsuccessful", Toast.LENGTH_SHORT).show();
                 }
             }
         });
     }
 }