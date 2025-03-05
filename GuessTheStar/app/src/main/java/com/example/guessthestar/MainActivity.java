package com.example.guessthestar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

//This app is created with web page -->
//https://fameregistry.com/top-100-pornstars/
//if this website crashes the app also crashes
//No error in the app

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();
    ImageView imageView;
    Button zero, one, two, three;
    ArrayList<String> celebURLs = new ArrayList<>();
    ArrayList<String> celebNamesTemp = new ArrayList<>();
    ArrayList<String> celebNames = new ArrayList<>();
    ArrayList<String> celebNamesUnique = new ArrayList<>();
    ArrayList<String> answers = new ArrayList<>();
    int chosenCelebIndex, indexOfCorrectAnswer;

    public class DownloadHTML extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String HTMLDoc = "";
            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    HTMLDoc += current;
                    data = reader.read();
                }
                return HTMLDoc;
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        }
    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap myBitmap = null;
            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(in); // in place of inputStreamReader to download html, we will use this to download image
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return myBitmap;
            }
        }
    }

    private void generateQuestion() {
        answers.clear();
        chosenCelebIndex = rand.nextInt(celebURLs.size()); //100
        DownloadImage task = new DownloadImage();
        Bitmap myImage;
        try {
            // copy image address, it shall end with jpeg,jpg,png,etc
            myImage = task.execute(celebURLs.get(chosenCelebIndex)).get();
            imageView.setImageBitmap(myImage);

            String correctName = celebNamesUnique.get(chosenCelebIndex); //name

            indexOfCorrectAnswer = rand.nextInt(4);


            for (int i = 0; i < 4; i++) {
                if (i == indexOfCorrectAnswer){
                    answers.add(i, correctName);
                }
                int wrongIndex = rand.nextInt(celebURLs.size());
                String wrongName = celebNamesUnique.get(wrongIndex);
                while (wrongName.equals(correctName)){
                    wrongName = celebNamesUnique.get(rand.nextInt(celebURLs.size()));
                }
                answers.add(wrongName);
            }
            zero.setText(answers.get(0));
            one.setText(answers.get(1));
            two.setText(answers.get(2));
            three.setText(answers.get(3));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clicked(View view) {
        String selectedButton = String.valueOf(view.getTag());

        if (selectedButton.equals(Integer.toString(indexOfCorrectAnswer))){
            Toast.makeText(this, "Correct!!",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Wrong!! Correct guess was "+ celebNamesUnique.get(chosenCelebIndex),Toast.LENGTH_LONG).show();
        }
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zero = findViewById(R.id.button0);
        one = findViewById(R.id.button1);
        two = findViewById(R.id.button2);
        three = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView);

        DownloadHTML taskHTML = new DownloadHTML();
        String resultedHTML = "";
        try {
            resultedHTML = taskHTML.execute("https://fameregistry.com/top-100-pornstars/").get();
//            DownloadImage taskImg = new DownloadImage();
//            Bitmap myImage;
//            // copy image address, it shall end with jpeg,jpg,png,etc
//            myImage = taskImg.execute("https://fameregistry.com/wp-content/uploads/2010/11/autumnfalls-215x300.jpg").get();
//            imageView.setImageBitmap(myImage);
            Log.i("MESSAGE -------->","HTML SUCCESS");

            //fetching url
            Pattern p = Pattern.compile("src=\"(.*?)\" ");
            Matcher m = p.matcher(resultedHTML);
            while (m.find()){
                //Log.i("URL",m.group(1));
                celebURLs.add(m.group(1));
            }
//            Log.i("SIZE OF CELEB URL",Integer.toString(celebURLs.size()));

            celebURLs.subList(0, 9).clear();    // removing the first 9 url because its fetching the url that we don't need

//            Log.i("SIZE OF CELEB URL 2",Integer.toString(celebURLs.size()));

//            for (int i = 0; i < 100 ; i++) {
//                Log.i("CelebURL", celebURLs.get(i));
//            }




            //fetching name
            p = Pattern.compile("<a href=\"https://fameregistry.com/(.*?)/\">");
            m = p.matcher(resultedHTML);
            while (m.find()){
                //Log.i("Name",m.group(1));
                celebNamesTemp.add(m.group(1));
            }

            //first -> 21
            //last -> 8

            celebNamesTemp.subList(celebNamesTemp.size() - 8, celebNamesTemp.size()).clear(); // removing the last 8 names because its fetching non name obj
            //Log.i("SIZE OF CELEB NAME 2",Integer.toString(celebNamesTemp.size()));

            celebNamesTemp.subList(0, 21).clear(); // removing the first 21 names because its fetching non name obj
            //Log.i("SIZE OF CELEB NAME 3",Integer.toString(celebNamesTemp.size()));

//            for (int i = 0; i < 200 ; i++) {
//                Log.i("CelebNAME", celebNamesTemp.get(i));
//            }


            Log.i("ArrayList","Modified");

            ArrayList<String> celebNames = new ArrayList<>();
            for (String name : celebNamesTemp) {
                String[] parts = name.split("-");
                StringBuilder formattedName = new StringBuilder();
                for (String part : parts) {
                    formattedName.append(part.substring(0, 1).toUpperCase())
                            .append(part.substring(1))
                            .append(" ");
                }
                celebNames.add(formattedName.toString().trim());
            }
            Log.i("Name Initialise","PASSED");

            for (String str : celebNames) {
                // Add to celebNamesUnique only if not already present
                if (!celebNamesUnique.contains(str)) {
                    celebNamesUnique.add(str);
                }
            }

            Log.i("NAME DUPLICATES","REMOVED");

//            for (int i = 0; i < 100 ; i++) {
//                Log.i("CelebNAME", celebNamesUnique.get(i));
//            }

            Log.i("Size of Name",Integer.toString(celebNamesUnique.size()));
            Log.i("Size of URL",Integer.toString(celebURLs.size()));

            generateQuestion();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}