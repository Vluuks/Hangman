package com.example.renske.hangman_basic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
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

    public ArrayList<String> wordList;
    public ArrayList<Character> wrongletterList;
    public static String underscoredword, pickedword, wrongletters;
    public TextView word, wronglettersview;
    public static int guesses, score, wordlength, gametype;


    public void loadDictionary() {
        // String[] wordstoguess = new String[]{"pompoen", "appel", "mandarijn", "pastinaak", "peer"};
        String[] wordstoguess = getResources().getStringArray(R.array.words_small);
        wordList = new ArrayList<String>();
        wordList.addAll(Arrays.asList(wordstoguess));
    }

    public void initializeGame() {

        guesses = 5; // get these values from settings activity later
        score = 0;
        wordlength = 15; // max length of word
        gametype = 1; //or 2, get from user preference
        wrongletterList = new ArrayList<Character>(); // list to store wrong letters

        // if gametype is normal, use normal class, else evil


    }


    public void saveGameStatus() {

        // put things in the bundle/sharedpreferences
    }

    public void onWin(View view) {
        Intent intent = new Intent(this, HighScores.class);


    }


    public void onLose() {
        // do something

    }




    public void pickInitialWord(int maxwordlength) {
        // if user has specified less than max word length
        int longestword = 15; // longest word in dictionary, idk if its 15 for sure though
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
        word = (TextView) findViewById(R.id.maintextview);
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
    abstract boolean checkInWord(char letter);
    abstract boolean onKeyUp(int keyCode, KeyEvent event);
}