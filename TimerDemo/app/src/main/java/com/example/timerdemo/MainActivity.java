package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //USING A HANDLER AND RUNNABLE
        /*
         * The handler and runnable are used when a particular task is to be performed by regular interval of time
        */

//        Handler handler = new Handler(); // the object which will take care of the timer function
//        Runnable run = new Runnable() { // the code that the handler object shall run
//            @Override
//            public void run() {
//                Log.i("runnable is running","a message has passed");
//                handler.postDelayed(this, 1000); //what the handler object must execute and in the interval of
//                                                                // how many milliseconds, this although sometimes fail in keeping the track
//            }
//        };
//        handler.post(run); //starting off the handler object
/****************************************************************************************************************/
        //USING A COUNTDOWN-TIMER

        /*
        * A countdown timer is used when we need to run a task over and over again at certain time interval until the timer runs-out
        * */

        new CountDownTimer(10000,1000){ //first parameter - total number of seconds the function has (here, 10seconds)
                                                                //second parameter - by hoe much time the total shall be decreased (here, 1second)
            public void onTick(long milliseconds){ // long milliseconds - remaining time left of the total
                Log.i("Seconds Left: ", String.valueOf(milliseconds/1000)); // to convert millisecond to second
            }
            public void onFinish(){ // task to do after the onTick is finished
                Log.i("The CountDownTimer finished","No more execution");
            }
        }.start(); // starting the function
    }
}