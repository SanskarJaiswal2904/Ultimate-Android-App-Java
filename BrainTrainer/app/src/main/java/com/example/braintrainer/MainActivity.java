package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;



public class MainActivity extends AppCompatActivity {
    TextView timer, go, countQuestions, question, status;
    Button playAgain, one, two, three, four;
    Random rand = new Random(); // Create a Random object
    GridLayout myGridLayout;
    int rightAnswerCount =0, totalQuestionCount= 0;
    int correctAnswerPos;


    ArrayList<Integer> answerList = new ArrayList<Integer>(4);

    public void gameRestart(View view){
        timer.setText("30s");
        countQuestions.setText("0 / 0");
        status.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        rightAnswerCount =0;
        totalQuestionCount= 0;
        gameStarted(view);
    }
    public void clicked(View view) {
        status.setVisibility(View.VISIBLE);
        String tag = view.getTag().toString();
        if (tag.equals(Integer.toString(correctAnswerPos))) {
//        int tag = (int) view.getTag(); // what the fuck is wrong in this code
//        Log.i("Tag  ", Integer.toString(tag));
            status.setText("Correct :)");
            rightAnswerCount++;
            totalQuestionCount++;

        } else {
            status.setText("Wrong :(");
            totalQuestionCount++;
        }
        countQuestions.setText(rightAnswerCount+" / "+totalQuestionCount);
        createNewQuestion();
    }
    public void createNewQuestion(){
        answerList.clear();
        int firstNum, secondNum;
        firstNum = rand.nextInt(1001);
        secondNum = rand.nextInt(1001);
        int correctAnswer = firstNum + secondNum;
        question.setText(firstNum +"+"+secondNum);
        correctAnswerPos = rand.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == correctAnswerPos){
                answerList.add(correctAnswer);
            }
            int wrongAnswer = rand.nextInt(2001);
            while (wrongAnswer == correctAnswer){
                wrongAnswer = rand.nextInt(2001);
            }
            answerList.add(wrongAnswer);
        }
        one.setText(Integer.toString(answerList.get(0)));
        two.setText(Integer.toString(answerList.get(1)));
        three.setText(Integer.toString(answerList.get(2)));
        four.setText(Integer.toString(answerList.get(3)));
    }

    public void gameStarted(View view){
        go.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);
        countQuestions.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        //myGridLayout.setVisibility(View.VISIBLE); //don't know the code for starting gridlayout
        status.setText("");
        new CountDownTimer(30100, 1000){
            public void onTick(long l){
                timer.setText(String.valueOf(l/1000));
            }
            public void onFinish(){
                double actualPer =  ((double) rightAnswerCount /totalQuestionCount)*100;
                String roundPercent = String.format("%.2f",actualPer); // to convert percentage to only 2 digits after decimal
                status.setText(roundPercent+" %");
                timer.setText("00s");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
        createNewQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textview
        timer = findViewById(R.id.TimertextView);
        go = findViewById(R.id.goTextView);
        countQuestions = findViewById(R.id.NumberquestionTextView);
        question = findViewById(R.id.questiontextView);
        status = findViewById(R.id.statusTextView);

        //button
        playAgain = findViewById(R.id.playAgainButton);
        one = findViewById(R.id.button1);
        two = findViewById(R.id.button2);
        three = findViewById(R.id.button3);
        four = findViewById(R.id.button4);


        androidx.gridlayout.widget.GridLayout myGridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.optionGridLayout);


    }
}