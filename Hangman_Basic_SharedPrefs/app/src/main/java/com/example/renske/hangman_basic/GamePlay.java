package com.example.renske.hangman_basic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by Renske on 28-11-2015.
 */
////////////////////////////////////////////////////////////////////////////////////////////////////
// ABSTRACT GAMEPLAY CLASS
////////////////////////////////////////////////////////////////////////////////////////////////////

abstract class GamePlay extends AppCompatActivity {

    // declare variables
    public static ArrayList<Character> wrongletterList;
    public static String underscoredword, pickedword, wrongletters;
    public static TextView word, wronglettersview;
    public static int currentguesses, gametype;


    // puts current status of game in sharedpreferences
    public void saveGameStatus() {

        SharedPreferences useroptions = this.getSharedPreferences("settings",
                this.MODE_PRIVATE);
        SharedPreferences.Editor editor = useroptions.edit();
        editor.putString("WORDTOGUESS", pickedword);
        editor.putString("WORDSTATUS", underscoredword);
        editor.putInt("GUESSESSTATUS", currentguesses);
        editor.putString("WRONGLETTERS", wrongletters);
        editor.commit();
    }


    // if the user guesses the word
    public void onWin(Activity currentactivity) {

        //display some kind of winning message
        // if highscore, let user type in name?
        // pass this info on to highscore activity

        Intent intent = new Intent(this, HighscoresActivity.class);
        intent.putExtra("GUESSESLEFT", currentguesses);
        intent.putExtra("GAMETYPE", gametype);
        intent.putExtra("WORD", String.valueOf(word));
        currentactivity.startActivity(intent);
    }


    // if the user runs out of guesses
    public void onLose(Activity currentactivity) {
        // do something
        Intent intent = new Intent(this, HighscoresActivity.class);
        currentactivity.startActivity(intent);


    }

    // adds letter to list of wrong letters
    public void addWrongLetter(char letter){
        StringBuilder wrongLettersList = new StringBuilder(wrongletters);
        wrongLettersList.append(letter+" ");

        wronglettersview = (TextView) findViewById(R.id.textView3);
        wronglettersview.setText(wrongletters.toString());
    }

    // the abstract methods to be implemented by the sublcasses evil and good
    public abstract boolean checkInWord(char letter);
    public abstract void manageKeys();


}

