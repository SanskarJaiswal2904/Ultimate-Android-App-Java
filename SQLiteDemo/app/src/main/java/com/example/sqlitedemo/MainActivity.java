package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        try {
//            SQLiteDatabase vidya = this.openOrCreateDatabase("id", MODE_PRIVATE,null);
////            vidya.execSQL("CREATE TABLE IF NOT EXISTS schoolid (name VARCHAR, age INT(3), roll INT(5))");
////            vidya.execSQL("INSERT INTO schoolid (name, age, roll) VALUES ('Jaiswal',32,1235)");
////            vidya.execSQL("INSERT INTO schoolid (name, age, roll) VALUES ('Lala',33,1234)");
////            vidya.execSQL("INSERT INTO schoolid (name, age, roll) VALUES ('Chotu',20,1225)");
////            vidya.execSQL("INSERT INTO schoolid (name, age, roll) VALUES ('Shaw',45,3254)");
////            vidya.execSQL("INSERT INTO schoolid (name, age, roll) VALUES ('Modi',35,6589)");
//
////            Cursor cu = vidya.rawQuery("SELECT * FROM schoolid WHERE age > 25 AND name = 'Lala'", null);
////            Cursor cu = vidya.rawQuery("SELECT * FROM schoolid WHERE age > 25 AND name LIKE '%a%'", null); //a% = starting value a, %a% = middle value a, %a = last value a
//            Cursor cu = vidya.rawQuery("SELECT * FROM schoolid WHERE age > 25 AND name LIKE '%a%' LIMIT 1", null);
//
//            int nameIndex2 = cu.getColumnIndex("name");
//            int ageIndex2 = cu.getColumnIndex("age");
//            int rollIndex2 = cu.getColumnIndex("roll");
//
//            cu.moveToFirst();
//
//            while (cu != null) {
//                Log.i("Name is ", cu.getString(nameIndex2));
//                Log.i("Age is ", cu.getString(ageIndex2));
//                Log.i("Roll is is ", cu.getString(rollIndex2));
//
//                cu.moveToNext();
//            }
//        }
        try {
            SQLiteDatabase vidya = this.openOrCreateDatabase("id", MODE_PRIVATE,null);
            vidya.execSQL("CREATE TABLE IF NOT EXISTS newSchoolID (name VARCHAR, age INT(3), roll INT(5), id INTEGER PRIMARY KEY)"); // PRIMARY KEY CAN BE HANDLE BY SQL ALONE
            vidya.execSQL("INSERT INTO newSchoolID (name, age, roll) VALUES ('Jaiswal',32,1235)");
            vidya.execSQL("INSERT INTO newSchoolID (name, age, roll) VALUES ('Lala',33,1234)");
            vidya.execSQL("INSERT INTO newSchoolID (name, age, roll) VALUES ('Chotu',20,1225)");
            vidya.execSQL("INSERT INTO newSchoolID (name, age, roll) VALUES ('Shaw',45,3254)");
            vidya.execSQL("INSERT INTO newSchoolID (name, age, roll) VALUES ('Modi',35,6589)");

            vidya.execSQL("DELETE FROM newSchoolID WHERE name = 'Chotu' ");

            Cursor cu = vidya.rawQuery("SELECT * FROM newSchoolID", null);
//            Cursor cu = vidya.rawQuery("SELECT * FROM schoolid WHERE age > 25 AND name LIKE '%a%'", null); //a% = starting value a, %a% = middle value a, %a = last value a
//            Cursor cu = vidya.rawQuery("SELECT * FROM schoolid WHERE age > 25 AND name LIKE '%a%' LIMIT 1", null);

            int nameIndex2 = cu.getColumnIndex("name");
            int ageIndex2 = cu.getColumnIndex("age");
            int rollIndex2 = cu.getColumnIndex("roll");
            int idIndex2 = cu.getColumnIndex("id");


            cu.moveToFirst();

            while (cu != null) {
                Log.i("Name is ", cu.getString(nameIndex2));
                Log.i("Age is ", cu.getString(ageIndex2));
                Log.i("Roll is is ", cu.getString(rollIndex2));
                Log.i("ID is is ", cu.getString(idIndex2));

                cu.moveToNext();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}