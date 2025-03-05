package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class FirebaseFirestoreCloud extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_firestore_cloud);

        db = FirebaseFirestore.getInstance();

        Map<String, Object> city = new HashMap<>();
        city.put("Muncipal", "Garulia");
        city.put("Town", "Shyamnagar");
        city.put("Sheher", "Kolkata");

        db.collection("cities").document("Address SanKam1").set(city).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(FirebaseFirestoreCloud.this, "Value Added 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
        city.put("Muncipal", "Bhatpara");
        city.put("Town", "Jaggdal");
        city.put("Sheher", "Kolkata");
        db.collection("cities").document("Address SanKam 2").set(city).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(FirebaseFirestoreCloud.this, "Value Added 2", Toast.LENGTH_SHORT).show();
                }
            }
        });
        city.put("Muncipal", "Koochbehar");
        city.put("Town", "Ratnapur");
        city.put("Sheher", "Siliguri");
        db.collection("cities").document("Address SanKam 3").set(city).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(FirebaseFirestoreCloud.this, "Value Added 3", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // to merge a map to another collection -> document -> value
        Map<String, Object> dataToBeMerged = new HashMap<>();
        dataToBeMerged.put("country", "India");
        dataToBeMerged.put("capital", "New Delhi");
        db.collection("cities").document("Address SanKam 3").set(dataToBeMerged, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(FirebaseFirestoreCloud.this, "Merge Added to 3", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //random key
//        dataToBeMerged.clear();
//        dataToBeMerged.put("country","India");
//        dataToBeMerged.put("planet","Earth");
//        dataToBeMerged.put("galaxy","MilkyWay");
//        db.collection("cities").add(dataToBeMerged).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentReference> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(FirebaseFirestoreCloud.this, "Generated random key", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        //update existing data
//        DocumentReference ref = FirebaseFirestore.getInstance().collection("cities").document("Address SanKam 2");
//        ref.update("Town","Kankinara"); // update document Address SanKam 2 with tag town, from jaggdal to kankinara


        //Retrive data from CloudFireStore
        DocumentReference retriveDoc = FirebaseFirestore.getInstance().collection("cities").document("Address SanKam 3");
        retriveDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    if (snapshot.exists()) {
                        Log.i("Result is this", snapshot.getData().toString());
                    } else {
                        Log.i("Result is this", "No Data ");
                    }
                }
            }
        });

        //Retrive data from CloudFireStore with Conditions
        FirebaseFirestore.getInstance().collection("cities").whereEqualTo("Sheher", "Kolkata").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                Log.i("THE DETAIL OF PAGE IS", doc.getId() + " -> " + doc.getData()); // get id -> document name, get data -> details of the document where the condition matches
                            }
                        }
                    }
                });

        FirebaseFirestore.getInstance().collection("cities").document("Address SanKam 3")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                //Task is performed
            }
        });
    }

}