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
public class GamePreparation extends AppCompatActivity {

    // declare variables
    public static ArrayList<String> dictionary;
    public static String underscoredword, pickedword;
    public int current_wordlength, longestword, maxwordlength, wrongtriesallowed, gametype;
    
    // load the dictiomary from XML file
    public void loadDictionary(Context context) {
        String[] wordstoguess = context.getResources().getStringArray(R.array.words_large);
        dictionary = new ArrayList<String>();
        dictionary.addAll(Arrays.asList(wordstoguess));
    }

    // initialize the basic settings before starting the game
    public void initializeGame(Context context) {
            SharedPreferences useroptions = context.getSharedPreferences("settings",
                    this.MODE_PRIVATE);
            wrongtriesallowed = useroptions.getInt("GUESSES", 5);
            maxwordlength = useroptions.getInt("WORDLENGTH", 6);
            gametype = useroptions.getInt("GAMETYPE", 1);
    }

    public int getGameType(){
        return gametype;
    }

    public static ArrayList getDictionary(){
        return dictionary;
    }

    // pick the word to start the game with
    public void pickInitialWord(TextView textview) {
        longestword = 15;
        current_wordlength = 1;

        // if user has specified less than max word length
        if (maxwordlength < longestword) {
            Random randomizer = new Random();
            pickedword = dictionary.get(randomizer.nextInt(dictionary.size()));
            current_wordlength = pickedword.length();

            // keep picking a random word until it has the right length
            while (current_wordlength > maxwordlength) {
                Random randomizers = new Random();
                pickedword = dictionary.get(randomizers.nextInt(dictionary.size()));
                current_wordlength = pickedword.length();
            }
        }

        // if the user has not specified a maximum value, just pick a random word
        else {
            Random randomizer = new Random();
            pickedword = dictionary.get(randomizer.nextInt(dictionary.size()));
            current_wordlength = pickedword.length();
        }

        // set the underscores to the right length
        underscoredword = new String(new char[current_wordlength]).replace("\0", "_");
        textview.setText(underscoredword);
    }
}

