package com.example.renske.hangman_basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize game settings
        GamePlay.initializeGame();

        // load dictionary
        GamePlay.loadDictionary();

        // pick first word
        GamePlay.pickInitialWord();



        if (GamePlay.gametype == 1){

            // tell program which code to use to handle key presses and guessed letters
            // but has to keep doing it, not call only once? how??
            GoodGamePlay.manageKeys(); // has to be static but cant? TODO
        }

        else {
            // TODO USE GOOD CODE CLASS
        }


        // if the word is guessed announce user wins
        if(String.valueOf(GamePlay.word).indexOf('_') == -1){
            GamePlay.onWin(MainActivity.this); // TODO CHECK IF THIS WORKS CORRECTLY
        }


        // if the user is out of guesses, stop the game
        if(GamePlay.currentguesses == 0){
            GamePlay.onLose(MainActivity.this);
        }

    }

    // if no game is in progress, direct to start screen?


}

















