package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;

import javax.net.ssl.HttpsURLConnection;

////////////////////////////////////////// The Toast is not working, its giving error so removing toast//////////////////////////////////////////////////////////////////////////
public class MainActivity extends AppCompatActivity {
    EditText cityEditText;
    TextView displayWeather;
    String WeatherToBEDisplayed = "";
    ProgressDialog progressDialog;
    DecimalFormat df = new DecimalFormat("#.##");
    //toast,handle space in url

    public void fetchWeather(View view){
        displayWeather.setText("");
        WeatherToBEDisplayed = "";
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Fetching Data");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Thread(new Runnable() {
            public void run() {
                try {
                    String cityName = String.valueOf(cityEditText.getText());
                    String encodedCityName = URLEncoder.encode(cityName,"UTF-8");   //although the code handles city name with spaces but just in case if it doesn't this line will handle the space
                    DownloadJSON task = new DownloadJSON();
                    task.execute("https://api.openweathermap.org/data/2.5/weather?q="+encodedCityName+"&appid=658b225b8c1d378ba8af9b5147de8a44");
                    //to hide keyboard
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(cityEditText.getWindowToken(),0);

                    Thread.sleep(1000);
                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this, "Could not find City", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }
    public class DownloadJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpsURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            }catch (Exception e){
//                Toast.makeText(MainActivity.this, "Could not find City", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return "Failed";
            }
        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            //Log.i("JSON", result);
//            try {
//                JSONObject jsonObject = new JSONObject(result);
//                String WeatherInfo = jsonObject.getString("weather");

//                String coord = jsonObject.getString("coord");
//                String Main = jsonObject.getString("main");
//                String visibility = jsonObject.getString("visibility");
//                String wind = jsonObject.getString("wind");
//                String sys = jsonObject.getString("sys");
//
//                JSONArray jsonArray = new JSONArray(WeatherInfo);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonSubPart = jsonArray.getJSONObject(i);
//                    String main = jsonSubPart.getString("main") ;
//                    String description = jsonSubPart.getString("description");
//                    WeatherToBEDisplayed = "Main: "+main + "\n "+"Description: "+ description ;
//                }
//                JSONArray jsonArray1 = new JSONArray(coord);
//                for (int i = 0; i < jsonArray1.length(); i++) {
//                    JSONObject jsonSubPart = jsonArray1.getJSONObject(i);
//                    double longitude = jsonSubPart.getDouble("lon") ;
//                    double latitude = jsonSubPart.getDouble("lat");
//                    WeatherToBEDisplayed = "longitude: ";
//                    displayWeather.setText(WeatherToBEDisplayed);
//                }
            // the above comment section is from the video and the below code i have copied from youtube -->
            //https://youtu.be/f2oSRBwN2HY?si=Nk1VYR5cNtm7fMAt
                try {
                    JSONObject jsonResponse = new JSONObject(result);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    float pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    String wind = jsonObjectWind.getString("speed");
                    JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                    String clouds  = jsonObjectClouds.getString("all");
                    JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                    String countryName = jsonObjectSys.getString("country");
                    String cityName = jsonResponse.getString("name");
                    WeatherToBEDisplayed += "Current weather of " + cityName + " (" + countryName + ")"
                            + "\n Temp: " + df.format(temp) + " °C"
                            + "\n Feels Like: " + df.format(feelsLike) + " °C"
                            + "\n Humidity: " + humidity + "%"
                            + "\n Description: " + description
                            + "\n Wind Speed: " + wind + "m/s"
                            + "\n Cloudiness: " + clouds + "%"
                            + "\n Pressure: " + pressure + " hPa";
                    displayWeather.setText(WeatherToBEDisplayed);
                } catch (JSONException e) {
                    //Toast.makeText(MainActivity.this, "Could not find City", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityEditText = findViewById(R.id.editTextText);
        displayWeather = findViewById(R.id.resultTextView);
    }
}