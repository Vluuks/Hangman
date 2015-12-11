package com.example.renske.hangman_basic;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class EvilGamePlay extends GamePlay {

    @Override
    public boolean checkInWord(char letter, TextView wordToGuess_textView,
        TextView wrongLetterList_textView, TextView wrongTriesLeft_textView) {

        //check if this letter was already pressed
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
            Hashtable hashtable = new Hashtable(); //TODO
            int wordLength = pickedWord.length();

            // iterate over all words in dictionary and add them to new one if length matches
            ArrayList<String> evilDictionary_sameLength = new ArrayList<String>();
            for (String word : evilDictionary) {
                if (word.length() == wordLength) {
                    evilDictionary_sameLength.add(word);
                }
            }

            // when user presses a letter
            for (String word : evilDictionary_sameLength) {

                // create basic string of underscores to function as base key
                String theKeyWord = new String(new char[wordLength]).replace("\0", "_");
                StringBuilder keyWordBuilder = new StringBuilder(theKeyWord);

                // iterate over characters in word
                for (int i = 0; i < word.length(); i++) {
                    if (i == letter) {
                        keyWordBuilder.setCharAt(i, letter);
                    }
                }

                String theFinalKey = keyWordBuilder.toString();
                ArrayList<String> tempWordList = new ArrayList<String>();
                tempWordList.add(word);

                // if final key is already in hash table
                if (hashtable.containsKey(theFinalKey)) {
                    tempWordList = (ArrayList<String>) hashtable.get(theFinalKey);
                    tempWordList.add(word);
                    hashtable.put(theFinalKey, tempWordList);
                }

                // else create new entry for this key
                else {
                    hashtable.put(theFinalKey, tempWordList);
                }

                tempWordList.clear();
            }

            // compare size of lists in hashtable and find out which one is the largest
            Set<String> keys = hashtable.keySet();
            ArrayList<String> currentWordList = (ArrayList<String>)
                    hashtable.get(GamePreparation.underscoredWord);
            String currentKey = GamePreparation.underscoredWord;

            // iterate over all the lists of words in the hashtable
            for (String key : keys) {
                ArrayList<String> tempWordList_2 = (ArrayList<String>) hashtable.get(key);

                // if the found list is bigger than the one before, use it
                if (tempWordList_2.size() > currentWordList.size()) {
                    currentWordList = tempWordList_2;
                    currentKey = key;
                }
            }

            // if there is no change in optimal key/list combination
            if (currentKey == GamePreparation.underscoredWord) {
                currentGuesses--;
                addWrongLetter(letter);
                wrongLetterList_textView.setText(getWrongLetters());
                wrongTriesLeft_textView.setText(getGuessesString());
                return false;
            }

            else {
                // start building the new string do be displayed
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