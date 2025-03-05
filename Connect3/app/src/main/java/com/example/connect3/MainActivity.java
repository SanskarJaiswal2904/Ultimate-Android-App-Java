package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0: yellow, 1: red, 2: empty
    int playerSpaces[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPoss = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}; //first,second,third rows and columns, and 2 diagonals
    int activePlayer = 0;
    int gameIsRunning = 1;

    public void clicked(View view) {
        ImageView imgView = (ImageView) view; // i want to see which image was clicked in the grid
        TextView playAgainText = (TextView) findViewById(R.id.playAgainTextView);
        TextView whoWon = (TextView) findViewById(R.id.whoWonTextView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        int occupiedPos = Integer.parseInt(imgView.getTag().toString());

        if (playerSpaces[occupiedPos] == 2 && gameIsRunning == 1) {   // if position is blank && no one has won
            if (activePlayer == 0) {
                imgView.setImageResource(R.drawable.yelloww);
                playerSpaces[occupiedPos] = activePlayer;
                activePlayer = 1;
            } else {
                imgView.setImageResource(R.drawable.redd);
                playerSpaces[occupiedPos] = activePlayer;
                activePlayer = 0;
            }
        }
        imgView.animate().rotation(3600).setDuration(1000);

        for (int[] winningPosition : winningPoss) {
            if (playerSpaces[winningPosition[0]] == playerSpaces[winningPosition[1]] && playerSpaces[winningPosition[1]] == playerSpaces[winningPosition[2]]
                    && playerSpaces[winningPosition[0]] != 2) {
                playAgainText.setVisibility(View.VISIBLE);
                playAgainText.setText("Wanna Play Again?");
                playAgainButton.setVisibility(View.VISIBLE);
                whoWon.setVisibility(View.VISIBLE);
                String winner = "";
                if (activePlayer == 1) {
                    winner = "Yellow";
                } else {
                    winner = "Red";
                }
                whoWon.setText(winner + " " + "Won the match");
                gameIsRunning = 0;
            }
        }
    }

    public void gameRestart(View view) {
        TextView playAgainText = (TextView) findViewById(R.id.playAgainTextView);
        TextView whoWon = (TextView) findViewById(R.id.whoWonTextView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        androidx.gridlayout.widget.GridLayout myGridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        // there is some issue in line 64 only, due to which playAgain button is crashing the app

        playAgainText.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        whoWon.setVisibility(View.INVISIBLE);

        for(int i = 0; i < myGridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) myGridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }

        for (int i : playerSpaces) {
            playerSpaces[i] = 2;
        }
        activePlayer = 0;
        gameIsRunning = 1;
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}