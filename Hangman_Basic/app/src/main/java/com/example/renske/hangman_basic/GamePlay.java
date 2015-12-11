package com.example.renske.hangman_basic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

abstract class GamePlay extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

    // declare variables
    public String wrongLetterString = " ";
    public String pickedWord;
    public int currentGuesses, gametype;
    private Context context;

    // adds letter to list of wrong letters
    public void addWrongLetter(char letter){
        StringBuilder wrongLettersList = new StringBuilder(wrongLetterString);
        wrongLettersList.append(letter + " ");
        wrongLetterString =  wrongLettersList.toString();
    }

    // if the word is guessed, save score and continue to highscores
    public void onWin(Activity currentactivity, Context context, TextView textView) {
        Toast toast = Toast.makeText(getContext(), "GG WP, you won!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

        Intent intent = new Intent(context, HistoryViewActivity.class);
        intent.putExtra("GUESSESLEFT", currentGuesses);
        intent.putExtra("GAMETYPE", gametype);
        intent.putExtra("WORD", String.valueOf(textView));
        currentactivity.startActivity(intent);
    }

    // if the user runs out of guesses, continue to highscores
    public void onLose(Activity currentActivity, Context context) {
        Toast toast = Toast.makeText(getContext(), "Aww,  you lost.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

        Intent intent = new Intent(context, HistoryViewActivity.class);
        currentActivity.startActivity(intent);
    }

    public String getGuessesString(){
        return String.valueOf(currentGuesses);
    }

    public int getGuesses(){
        return currentGuesses;
    }

    public String getWrongLetters(){
        return wrongLetterString;
    }

    public void setWrongletters(String string, TextView textView){
        textView.setText(string);
    }

    public void setGuesses(int value, TextView textView){
        currentGuesses = value;
        textView.setText(String.valueOf(currentGuesses));
    }

    public Context setContext(Context context){
        this.context = context;
        return this.context;
    }

    public Context getContext(){
        return context;
    }

    // the abstract methods to be implemented by the classes evil and good
    public abstract boolean checkInWord(char letter, TextView wordToGuess_textView,
            TextView wrongLetterList_textView, TextView wrongTriesLeft_textView);
}

