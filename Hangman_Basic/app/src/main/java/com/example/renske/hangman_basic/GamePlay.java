package com.example.renske.hangman_basic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;


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
    public String wrongletters = " ";
    public String pickedword;
    public int currentguesses, gametype;
    private Context context;


    // adds letter to list of wrong letters
    public void addWrongLetter(char letter){
        StringBuilder wrongLettersList = new StringBuilder(wrongletters);
        wrongLettersList.append(letter + " ");
        wrongletters =  wrongLettersList.toString();
    }


    // if the word is guessed, save score and continue to highscores
    public void onWin(Activity currentactivity, Context context, TextView textview) {
        Toast toast = Toast.makeText(getContext(), "Congratulations, you won!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

        Intent intent = new Intent(context, HistoryViewActivity.class);
        intent.putExtra("GUESSESLEFT", currentguesses);
        intent.putExtra("GAMETYPE", gametype);
        intent.putExtra("WORD", String.valueOf(textview));
        currentactivity.startActivity(intent);
    }


    // if the user runs out of guesses, continue to highscores
    public void onLose(Activity currentactivity, Context context) {
        Toast toast = Toast.makeText(getContext(), "Unfortunately,  you lost...", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

        Intent intent = new Intent(context, HistoryViewActivity.class);
        currentactivity.startActivity(intent);

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

    public void setWrongletters(String string, TextView textview){
        textview.setText(string);
    }

    public void setGuesses(int value, TextView textview){
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
    public abstract boolean checkInWord(char letter, TextView wordtoguess_textview, TextView wrongletterlist_textview, TextView wrongtriesleft_textview);


}

