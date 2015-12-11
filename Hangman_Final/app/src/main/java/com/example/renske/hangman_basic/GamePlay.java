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
 * The GamePlay class handles the basic functionalities of the game such as managing the wrong
 * guesses and redirecting the player on win/lose.
 */

abstract class GamePlay extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

    public String wrongLetterString = " ";
    public String pickedWord;
    public int currentGuesses, gametype;
    private Context context;

    /**
     * Checks whether the chosen word contains the letter a user chose and handles
     * subsequent events. This method is to be implemented by Good/EvilGamePlay.
     */
    public abstract boolean checkInWord(char letter, TextView wordToGuess_textView,
                                        TextView wrongLetterList_textView, TextView wrongTriesLeft_textView);

    /**
     * Adds wrong letter to list displayed to user.
     */
    public void addWrongLetter(char letter){
        StringBuilder wrongLettersList = new StringBuilder(wrongLetterString);
        wrongLettersList.append(letter + " ");
        wrongLetterString =  wrongLettersList.toString();
    }

    /**
     * Redirects user to highscore activity adds their score on win.
     */
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

    /**
     * Redirects user to highscore activity on lose, without adding score.
     */
    public void onLose(Activity currentActivity, Context context) {
        Toast toast = Toast.makeText(getContext(), "Aww,  you lost.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

        Intent intent = new Intent(context, HistoryViewActivity.class);
        currentActivity.startActivity(intent);
    }

    /**
     * Small methods used to obtain or set variable's values.
     */
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
}