package com.example.renske.hangman_basic;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public TextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        word = (TextView) findViewById(R.id.maintextview);

        // start the game's initialization
        StartGame startgame = new StartGame();

        // load dictionary and pick a word
        startgame.initializeGame();
        startgame.loadDictionary();
        startgame.pickInitialWord(word);


        if (startgame.gametype == 1){

            // tell program which code to use to handle key presses and guessed letters
            // but has to keep doing it, not call only once? how??
            GoodGamePlay theevilgame = new GoodGamePlay();

            while (GamePlay.currentguesses != 0 && (String.valueOf(GamePlay.word).indexOf('_') != -1))
                theevilgame.manageKeys();

            // if(String.valueOf(GamePlay.word).indexOf('_') == -1){
            //    theevilgame.onWin(MainActivity.this); // TODO CHECK IF THIS WORKS CORRECTLY
            //}

            // if the user is out of guesses, stop the game
            //if(GamePlay.currentguesses == 0){
                //theevilgame.onLose(MainActivity.this);
            }
        }

        else {
            GoodGamePlay thegoodgame = new GoodGamePlay();

            while (GamePlay.currentguesses != 0 && (String.valueOf(GamePlay.word).indexOf('_') != -1))
                thegoodgame.manageKeys();

            // if the user guesses the word, stop the game
            if(String.valueOf(GamePlay.word).indexOf('_') == -1){
                thegoodgame.onWin(MainActivity.this); // TODO CHECK IF THIS WORKS CORRECTLY
            }

            // if the user is out of guesses, stop the game
            if(GamePlay.currentguesses == 0){
                thegoodgame.onLose(MainActivity.this);
            }

        }





    }

    // if no game is in progress, direct to start screen?


}

















