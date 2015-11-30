package com.example.renske.hangman_basic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Renske on 28-11-2015.
 */
////////////////////////////////////////////////////////////////////////////////////////////////////
// ABSTRACT GAMEPLAY CLASS
////////////////////////////////////////////////////////////////////////////////////////////////////

abstract class GamePlay extends AppCompatActivity {

    // declare variables
    public static ArrayList<String> wordList;
    public static ArrayList<Character> wrongletterList;
    public static String underscoredword, pickedword, wrongletters;
    public static TextView word, wronglettersview;
    public static int currentguesses, wordlength, maxwordlength, guessesallowed, gametype;


    // load the dictiomary from XML file
    public void loadDictionary() {
        String[] wordstoguess = new String[]{"pompoen", "appel", "mandarijn", "pastinaak", "peer"};
        // String[] wordstoguess = getResources().getStringArray(R.array.words_small);
        wordList = new ArrayList<String>();
        wordList.addAll(Arrays.asList(wordstoguess));
    }


    // initialize the basic settings before starting the game
    public void initializeGame() {
        SharedPreferences useroptions = this.getSharedPreferences("settings",
                this.MODE_PRIVATE);
        guessesallowed = useroptions.getInt("GUESSES", 5);
        maxwordlength = useroptions.getInt("WORDLENGTH", 6);
        gametype = useroptions.getInt("GAMETYPE", 1);
        wrongletterList = new ArrayList<Character>(); // list to store wrong letters
    }


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

    // pick the word to start the game with
    public static void pickInitialWord() {
        // if user has specified less than max word length
        int longestword = 15; // longest word in dictionary, idk if its 15 for sure though TODO
        if (maxwordlength != longestword) {

            // keep picking a random word until it has the right length
            while (wordlength > maxwordlength) {
                Random randomizer = new Random();
                pickedword = wordList.get(randomizer.nextInt(wordList.size()));
                wordlength = pickedword.length();
            }
        }

        // if the user has not specified a maximum value
        else {
            // pick a random word to start
            Random randomizer = new Random();
            pickedword = wordList.get(randomizer.nextInt(wordList.size()));
            wordlength = pickedword.length();
        }

        // set the underscores to the right length
        underscoredword = new String(new char[wordlength]).replace("\0", "_");

        word.setText(underscoredword);
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