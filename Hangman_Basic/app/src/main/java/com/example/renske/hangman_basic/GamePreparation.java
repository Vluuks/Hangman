package com.example.renske.hangman_basic;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GamePreparation extends AppCompatActivity {

    // declare variables
    public static ArrayList<String> dictionary;
    public static String underscoredWord, pickedWord;
    public int currentWordlength, longestWord, maxWordLength, wrongTriesAllowed, gametype;
    
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
            wrongTriesAllowed = useroptions.getInt("GUESSES", 5);
            maxWordLength = useroptions.getInt("WORDLENGTH", 6);
            gametype = useroptions.getInt("GAMETYPE", 1);
    }

    // pick the word to start the game with
    public void pickInitialWord(TextView textview) {
        longestWord = 15;
        currentWordlength = 1;

        // if user has specified less than max word length
        if (maxWordLength < longestWord) {
            Random randomizer = new Random();
            pickedWord = dictionary.get(randomizer.nextInt(dictionary.size()));
            currentWordlength = pickedWord.length();

            // keep picking a random word until it has the right length
            while (currentWordlength > maxWordLength) {
                Random randomizers = new Random();
                pickedWord = dictionary.get(randomizers.nextInt(dictionary.size()));
                currentWordlength = pickedWord.length();
            }
        }

        // if the user has not specified a maximum value, just pick a random word
        else {
            Random randomizer = new Random();
            pickedWord = dictionary.get(randomizer.nextInt(dictionary.size()));
            currentWordlength = pickedWord.length();
        }

        // set the underscores to the right length
        underscoredWord = new String(new char[currentWordlength]).replace("\0", "_");
        textview.setText(underscoredWord);
    }

    public int getGameType(){
        return gametype;
    }

    public static ArrayList getDictionary(){
        return dictionary;
    }
}

