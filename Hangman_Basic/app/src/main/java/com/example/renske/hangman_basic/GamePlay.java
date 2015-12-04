package com.example.renske.hangman_basic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

    // declare variables
    public static String wrongletters = " ";
    public static String underscoredword, pickedword;
    public static TextView word, wronglettersview;
    public int currentguesses, gametype;
    private static Context context;


    // puts current status of game in sharedpreferences
    public void saveGameStatus(Context context) {

        SharedPreferences useroptions = context.getSharedPreferences("settings",
                this.MODE_PRIVATE);
        SharedPreferences.Editor editor = useroptions.edit();
        editor.putString("WORDTOGUESS", pickedword);
        editor.putString("WORDSTATUS", underscoredword);
        editor.putInt("GUESSESSTATUS", currentguesses);
        editor.putString("WRONGLETTERS", wrongletters);
        editor.commit();
    }





//  er uit halen?? werken niet

    // if the user guesses the word
    public void onWin(Activity currentactivity, Context context, TextView textview) {

        //display some kind of winning message
        // if highscore, let user type in name?
        // pass this info on to highscore activity

        Intent intent = new Intent(context, HighscoresActivity.class);
        intent.putExtra("GUESSESLEFT", currentguesses);
        intent.putExtra("GAMETYPE", gametype);
        intent.putExtra("WORD", String.valueOf(textview));
        currentactivity.startActivity(intent);
    }


    // if the user runs out of guesses
    public void onLose(Activity currentactivity, Context context) {
        // do something
        Intent intent = new Intent(context, HighscoresActivity.class);
        currentactivity.startActivity(intent);

    }

    // adds letter to list of wrong letters
    public void addWrongLetter(char letter){
        StringBuilder wrongLettersList = new StringBuilder(wrongletters);
        wrongLettersList.append(letter + " ");
        wrongletters =  wrongLettersList.toString();


    }

    public String getGuessesString(){
        return String.valueOf(currentguesses);
    }

    public int getGuesses(){
        return currentguesses;
    }

    public String getWrongLetters(){
        return wrongletters;
    }

    public void setInitialGuesses(int value, TextView textview){
        currentguesses = value;
        textview.setText(String.valueOf(currentguesses));
    }

    public Context setContext(Context context){
        this.context = context;
        return this.context;
    }

    public Context getContext(){
        return context;
    }


    // the abstract methods to be implemented by the sublcasses evil and good
    public abstract boolean checkInWord(char letter, TextView textview);


}

