package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Homepage extends AppCompatActivity {
    Button Logout, add;
    EditText name;
    FirebaseDatabase children;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Logout = findViewById(R.id.logout);
        add = findViewById(R.id.add);
        name = findViewById(R.id.name);

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        Logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(Homepage.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Homepage.this, MainActivity.class));
            finish();
        });

        //single value store
//        add.setOnClickListener(view -> {
//            String nametxt = name.getText().toString().trim();
//            children.getInstance().getReference().child("SansuKaamu").child("Languages").push()
//                    .setValue(nametxt);
//            Toast.makeText(Homepage.this, "Added successfully", Toast.LENGTH_SHORT).show();
//        });


        //single value
        /*
        DatabaseReference singleReference = FirebaseDatabase.getInstance().getReference().child("SansuKaamu").child("Languages");
        singleReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { // snapshot has every child of Languages tree in realtime database
                arrayList.clear();
                for (DataSnapshot childSnapShot : snapshot.getChildren()){
                    arrayList.add(childSnapShot.getValue().toString());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */

        //multiple value
        /*
        DatabaseReference multipleReference = FirebaseDatabase.getInstance().getReference().child("SansuKaamu").child("Employee");
        multipleReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { // snapshot has every child of Languages tree in realtime database
                arrayList.clear();
                for (DataSnapshot childSnapShot : snapshot.getChildren()){
                    Information info = childSnapShot.getValue(Information.class);
                    String valueFromDatabase = info.getEmail() +" : "+info.getPhone()+" : "+info.getName();
                    arrayList.add(valueFromDatabase);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */


//        children.getInstance().getReference().child("SansuKaamu").child("Boys").child("NAMES OF CHILDREN").push()
//                .setValue("Tejpratap");
//        children.getInstance().getReference().child("SansuKaamu").child("Boys").child("NAMES OF CHILDREN").push()
//                .setValue("Swastik");
//        children.getInstance().getReference().child("SansuKaamu").child("Boys").child("NAMES OF CHILDREN").push()
//                .setValue("Lata");
//        children.getInstance().getReference().child("SansuKaamu").child("Boys").child("NAMES OF CHILDREN").push()
//                .setValue("Tejasvi");

        //multiple value
//        HashMap <String, Object> map = new HashMap<>();
//        map.put("Name", "KamyaLala");
//        map.put("Email", "Kamya_Sanskar@love.com");
//        map.put("Phone","9875614692");
//        children.getInstance().getReference().child("SansuKaamu").child("Employee").push().updateChildren(map);
//        map.put("Name", "SanskarJaiswal");
//        map.put("Email", "Kamya_Sanskar@lovebirds.com");
//        map.put("Phone","8777450280");
//        children.getInstance().getReference().child("SansuKaamu").child("Employee").push().updateChildren(map);
//        map.put("Name", "KartigyaJaiswal");
//        map.put("Email", "Kamya_Sanskar@daughter.com");
//        map.put("Phone","10020");
//        children.getInstance().getReference().child("SansuKaamu").child("Employee").push().updateChildren(map);
//        map.put("Name", "TejasviJaiswal");
//        map.put("Email", "Kamya_Sanskar@son.com");
//        map.put("Phone","3435");
//        children.getInstance().getReference().child("SansuKaamu").child("Employee").push().updateChildren(map);
//
//        HashMap <String, Object> map2 = new HashMap<>();
//        map2.put("Name", "SansKar");
//        map2.put("Email", "SanskarLoveKamya@love.com");
//        map2.put("Bday", "29APR");
//        map2.put("Married", "Yes");
//        children.getInstance().getReference().child("SansuKaamu").child("Multiple Values").push().updateChildren(map2);
    }
}
//kamya@gmail.com
//123456