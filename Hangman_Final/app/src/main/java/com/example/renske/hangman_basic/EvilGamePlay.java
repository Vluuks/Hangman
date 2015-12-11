package com.example.renske.hangman_basic;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * EvilGamePlay inherits all functionalities of GamePlay but implements a different take on hangman
 * through checkInWord, in which the computer tries to avoid that the user guesses the word by
 * changing the set of words it has in mind if needed every time the user takes a guess.
 */
public class EvilGamePlay extends GamePlay {

    @Override
    public boolean checkInWord(char letter, TextView wordToGuess_textView,
        TextView wrongLetterList_textView, TextView wrongTriesLeft_textView) {

        // Check if this letter was already pressed.
        if (wrongLetterString.indexOf(letter) != -1 ||
                GamePreparation.underscoredWord.indexOf(letter) > 0) {
            Toast toast = Toast.makeText(getContext(), "You already guessed " + letter,
                Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return false;
        }
        else {
            ArrayList<String> evilDictionary;
            evilDictionary = GamePreparation.getDictionary();
            Hashtable<String, ArrayList<String>> hashtable = new Hashtable<String, ArrayList<String>>();
            int wordLength = pickedWord.length();

            // Add words of matching length to new dictionary.
            ArrayList<String> evilDictionary_sameLength = new ArrayList<String>();
            for (String word : evilDictionary) {
                if (word.length() == wordLength) {
                    evilDictionary_sameLength.add(word);
                }
            }

            // When user tries a letter, search for matching strings.
            for (String word : evilDictionary_sameLength) {
                String theKeyWord = new String(new char[wordLength]).replace("\0", "_");
                StringBuilder keyWordBuilder = new StringBuilder(theKeyWord);

                for (int i = 0; i < word.length(); i++) {
                    if (i == letter) {
                        keyWordBuilder.setCharAt(i, letter);
                    }
                }

                String theFinalKey = keyWordBuilder.toString();
                ArrayList<String> tempWordList = new ArrayList<String>();
                tempWordList.add(word);

                // Add to entry or create hash table entry for word.
                if (hashtable.containsKey(theFinalKey)) {
                    tempWordList = (ArrayList<String>) hashtable.get(theFinalKey);
                    tempWordList.add(word);
                    hashtable.put(theFinalKey, tempWordList);
                }
                else {
                    hashtable.put(theFinalKey, tempWordList);
                }

                tempWordList.clear();
            }

            // Obtain largest list from hashtable.
            Set<String> keys = hashtable.keySet();
            ArrayList<String> currentWordList = (ArrayList<String>)
                    hashtable.get(GamePreparation.underscoredWord);
            String currentKey = GamePreparation.underscoredWord;

            for (String key : keys) {
                ArrayList<String> tempWordList_2 = (ArrayList<String>) hashtable.get(key);

                if (tempWordList_2.size() > currentWordList.size()) {
                    currentWordList = tempWordList_2;
                    currentKey = key;
                }
            }

            // If there is no change in optimal key/list combination, count as wrong guess.
            if (currentKey == GamePreparation.underscoredWord) {
                currentGuesses--;
                addWrongLetter(letter);
                wrongLetterList_textView.setText(getWrongLetters());
                wrongTriesLeft_textView.setText(getGuessesString());
                return false;
            }
            // If there is a change in key, set this key as new wordstatus and count as correct.
            else {
                StringBuilder thewordbuilder = new StringBuilder(GamePreparation.underscoredWord);

                for (int i = 0; i < GamePreparation.pickedWord.length(); i++) {
                    if (GamePreparation.pickedWord.charAt(i) == letter)
                        thewordbuilder.setCharAt(i, letter);
                }

                GamePreparation.underscoredWord = thewordbuilder.toString();
                wordToGuess_textView.setText(GamePreparation.underscoredWord);
                return true;
            }
        }
    }
}