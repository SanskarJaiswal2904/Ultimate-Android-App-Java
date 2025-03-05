package com.example.signinwithgoogledemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class InfoActivity extends AppCompatActivity {
    TextView name, email;
    Button SignOut;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        SignOut = findViewById(R.id.signout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!= null){
            String namePerson = account.getDisplayName();
            String emailPerson = account.getEmail();
            name.setText(namePerson);
            email.setText(emailPerson);
        }
       SignOut.setOnClickListener(view -> {
           gsc.signOut().addOnCompleteListener(task -> {
               if (task.isSuccessful()){
                   Toast.makeText(this, "SignOut", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(InfoActivity.this, MainActivity.class));
                   finish();
               }
           });
       });

    }
}
//