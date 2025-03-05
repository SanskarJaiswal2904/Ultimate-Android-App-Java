package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginIn extends AppCompatActivity {

    EditText email, password;
    Button Login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.loginBtn);
        auth = FirebaseAuth.getInstance();

        Login.setOnClickListener(view -> {
            String emailTxt = email.getText().toString().trim();
            String passwordTxt = password.getText().toString();
            loggingIn(emailTxt, passwordTxt);
        });
    }

    private void loggingIn(String emailTxt, String passwordTxt) {
        auth.signInWithEmailAndPassword(emailTxt, passwordTxt).addOnCompleteListener(LoginIn.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginIn.this, Homepage.class));
                    finish();
                }else {
                    Toast.makeText(LoginIn.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            Log.i("USSSSSSSEEEEEEERR",user.toString());
            startActivity(new Intent(LoginIn.this, FirebaseFirestoreCloud.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }
    }
}