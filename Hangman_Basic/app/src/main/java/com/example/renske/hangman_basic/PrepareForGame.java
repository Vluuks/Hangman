package com.example.renske.hangman_basic;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Renske on 1-12-2015.
 */
public class PrepareForGame extends AppCompatActivity {

    // declare variables
    public static ArrayList<String> wordList;
    public static String underscoredword, pickedword, wrongletters, res_underscoredword, res_pickedword, res_wrongletters;
    public static int wordlength, maxwordlength, guessesallowed, gametype, currentguesses, res_currentguesses, res_gametype;


    // load the dictiomary from XML file
    public void loadDictionary(Context context) {
        String[] wordstoguess = context.getResources().getStringArray(R.array.words_large);
        wordList = new ArrayList<String>();
        wordList.addAll(Arrays.asList(wordstoguess));
    }

    // initialize the basic settings before starting the game
    public void initializeGame(Context context) {

            SharedPreferences useroptions = context.getSharedPreferences("settings",
                    this.MODE_PRIVATE);
            guessesallowed = useroptions.getInt("GUESSES", 5);
            maxwordlength = useroptions.getInt("WORDLENGTH", 6);
            gametype = useroptions.getInt("GAMETYPE", 1);


    }


    public boolean resumeGame(Context context) {

        SharedPreferences savedgamestatus = context.getSharedPreferences("status",
                this.MODE_PRIVATE);
        res_pickedword = savedgamestatus.getString("WORDTOGUESS", null);
        res_underscoredword = savedgamestatus.getString("WORDSTATUS", null);
        res_currentguesses = savedgamestatus.getInt("GUESSESSTATUS", 5);
        res_wrongletters = savedgamestatus.getString("WRONGLETTERS", null);
        res_gametype = savedgamestatus.getInt("GAMETYPE", 1);

        // return if loading preferences was succesfull
        if(pickedword == null || savedgamestatus.getString("WORDSTATUS", null) == null || wrongletters == null)
            return false;
        else
            return true;
    }


    // obtain gametype from options
    public int getGameType(){
        return gametype;
    }

    public int getMaxwordlength(){
        return maxwordlength;
    }


    public ArrayList getDictionary(){
        return wordList;

    }

    // pick the word to start the game with
    public void pickInitialWord(TextView textview) {

        wordlength = 1;

        // if user has specified less than max word length
        int longestword = 15; // longest word in dictionary, idk if its 15 for sure though TODO
        if (getMaxwordlength() < longestword) {

            Random randomizer = new Random();
            pickedword = wordList.get(randomizer.nextInt(wordList.size()));
            wordlength = pickedword.length();

            // keep picking a random word until it has the right length
            while (wordlength > getMaxwordlength()) {
                Random randomizers = new Random();
                pickedword = wordList.get(randomizers.nextInt(wordList.size()));
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

