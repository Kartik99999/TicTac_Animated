package com.kartik.tictac;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;     //0 is green, 1 is red
    boolean gameIsActive=true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        ImageView counterWhenImageChanged = (ImageView) view;
        int tappedCounter = Integer.parseInt(counterWhenImageChanged.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counterWhenImageChanged.setImageResource(R.drawable.green);
                counterWhenImageChanged.setTranslationY(-1000f);
                counterWhenImageChanged.animate().translationYBy(1000f).setDuration(500);
                counterWhenImageChanged.animate().rotationYBy(1440f).setDuration(500);
                activePlayer = 1;

            } else {
                counterWhenImageChanged.setImageResource(R.drawable.red);
                counterWhenImageChanged.setTranslationX(-1000f);
                counterWhenImageChanged.animate().translationXBy(1000f).rotationXBy(1080f).setDuration(500);
                activePlayer = 0;
            }
        }
        for (int[] winningPosition : winningPositions) {
            TextView showResult = (TextView) findViewById(R.id.resultText);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gameStats);

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
//                Toast.makeText(this, "Someone has won", Toast.LENGTH_SHORT).show();
                gameIsActive = false;
                String winner = "Player RED";
                int COLOR = 0xFFFF0000;

                if (gameState[winningPosition[0]] == 0) {
                    winner = "Player GREEN";
                    COLOR = 0xFF00FF00;
                }
                showResult.setTextColor(COLOR);
                showResult.setText(winner + " has won!");
                linearLayout.setVisibility(View.VISIBLE);
            } else {
                boolean gameIsOver=true;
                for (int state: gameState) {
                    if(state==2)
                        gameIsOver=false;
                }
                if (gameIsOver) {
                    showResult.setTextColor(Color.WHITE);
                    showResult.setText("Game is Draw");
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

        }
    }

    public void playAgain(View view) {
        gameIsActive=true;
        LinearLayout linearLayout = findViewById(R.id.gameStats);
        linearLayout.setVisibility(View.INVISIBLE);

        activePlayer=0;
        for (int i = 0; i <gameState.length; i++) {
            gameState[i]=2;
        }
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
