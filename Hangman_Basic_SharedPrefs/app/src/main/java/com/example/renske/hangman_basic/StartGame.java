package com.example.renske.hangman_basic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Renske on 1-12-2015.
 */
public class StartGame extends AppCompatActivity {

    // declare variables
    public static ArrayList<String> wordList;
    public static ArrayList<Character> wrongletterList;
    public static String underscoredword, pickedword;
    public static int wordlength, maxwordlength, guessesallowed, gametype;



    // initialize the basic settings before starting the game
    public void initializeGame() {

        wrongletterList = new ArrayList<Character>(); // list to store wrong letters

        // check if its the first time the game is loaded
        File f = new File("/data/data/com.example.renske.hangman_basic/shared_prefs/settings.xml");
        if (f.exists()) {
            SharedPreferences useroptions = this.getSharedPreferences("settings",
                    this.MODE_PRIVATE);
            guessesallowed = useroptions.getInt("GUESSES", 5);
            maxwordlength = useroptions.getInt("WORDLENGTH", 6);
            gametype = useroptions.getInt("GAMETYPE", 1);

        }
        else{
            guessesallowed = 5;
            maxwordlength = 15;
            gametype = 1;
        }
    }

    // load the dictiomary from XML file
    public void loadDictionary() {
        String[] wordstoguess = new String[]{"pompoen", "appel", "mandarijn", "pastinaak", "peer"};
        // String[] wordstoguess = getResources().getStringArray(R.array.words_small);
        wordList = new ArrayList<String>();
        wordList.addAll(Arrays.asList(wordstoguess));
    }




    // pick the word to start the game with
    public void pickInitialWord(TextView textview) {
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

        textview.setText(underscoredword);
    }


}
