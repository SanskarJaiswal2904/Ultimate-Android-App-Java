package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int seekBarValue;
    SeekBar seekBar;

    boolean timerIsRunning = true;
    MediaPlayer mediaPlayer;
    Button button;
    TextView textView;
    public void startTimer(View view) {
        seekBar.setEnabled(false);

        String func = String.valueOf(button.getText());

        if (func.equals("START")) {
            button.setText("STOP");
            new CountDownTimer(seekBarValue, 1000) {
                public void onTick(long milliseconds) {
                    int minutes = (int) (milliseconds / 1000) / 60; // converting millisecond to second then dividing by 60
                    int seconds = (int) (milliseconds / 1000) % 60; // converting millisecond to second then modulo by 60
                    if (!timerIsRunning){
                        cancel();
                    }
                    if (minutes < 10) {
                        textView.setText("\0"+minutes + ":" + seconds);
                    }
                    if (seconds < 10) {
                        textView.setText(minutes + ":" + "0" + seconds);
                    } else {
                        textView.setText(minutes + ":" + seconds);
                    }

                    timerIsRunning = true;
                }
                @Override
                public void onFinish() {
                    textView.setText("00:00");
                    button.setText("START");
                    mediaPlayer.start();
                    seekBar.setEnabled(true);
                    Toast.makeText(MainActivity.this, "Egg is Ready", Toast.LENGTH_SHORT).show();

                }
            }.start();
        }
        else{
            timerIsRunning = false;
            seekBar.setEnabled(true);
//            seekBar.setProgress(30000);
            button.setText("START");
//            seekBarValue = 30000;
            Toast.makeText(this, "STOPPED", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.timerTextView);
        button = (Button) findViewById(R.id.startButton);
        seekBar = (SeekBar) findViewById(R.id.timeSeekBar);
        mediaPlayer = MediaPlayer.create(this, R.raw.timerfinished);

        int maximumOnSeekBar = 1200000;
        int startingValue = 30000;
        seekBarValue = startingValue;
        seekBar.setMax(maximumOnSeekBar);
        seekBar.setProgress(startingValue);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarValue = i;
                int minutes = (i/1000)/60; // converting millisecond to second then dividing by 60
                int seconds = (i/1000)%60; // converting millisecond to second then modulo 60
                if (minutes < 10){
                    textView.setText("\0"+minutes +":"+ seconds);
                }
                if (seconds < 10){
                    textView.setText(minutes +":"+ "0"+seconds);
                }
                else {
                    textView.setText(minutes +":"+ seconds);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}