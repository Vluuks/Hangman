package com.example.renske.hangman_basic;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by Renske on 28-11-2015.
 */
////////////////////////////////////////////////////////////////////////////////////////////////////
// GOOD GAME PLAY
////////////////////////////////////////////////////////////////////////////////////////////////////

public class EvilGamePlay extends GamePlay {



    @Override
    public boolean checkInWord(char letter, TextView wordtoguess_textview, TextView wrongletterlist_textview, TextView wrongtriesleft_textview) {

        //check if this letter was already pressed
        if (wrongletters.indexOf(letter) != -1 || GamePreparation.underscoredword.indexOf(letter) > 0) {
            Toast toast = Toast.makeText(getContext(), "You already guessed " + letter, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return false;
        }

        else {
            ArrayList<String> evil_dictionary;
            evil_dictionary = GamePreparation.getDictionary();
            int wordlength = pickedword.length();

            // iterate over all words in dictionary and add them to evil dictionary if length matches
            ArrayList<String> evil_dictionary_fixedlength = new ArrayList<String>();
            for (String word : evil_dictionary) {
                if (word.length() == wordlength) {
                    evil_dictionary_fixedlength.add(word);
                }
            }

            Hashtable hashtable = new Hashtable();

            // when user presses a letter
            for (String word : evil_dictionary_fixedlength) {

                // create basic string of underscores to function as base key
                String thekeyword = new String(new char[wordlength]).replace("\0", "_");
                StringBuilder thekeywordbuilder = new StringBuilder(thekeyword);

                // iterate over characters in word
                for (int i = 0; i < word.length(); i++) {
                    if (i == letter) {
                        thekeywordbuilder.setCharAt(i, letter);
                    }
                }

                String finalkey = thekeywordbuilder.toString();
                ArrayList<String> tempwordarray = new ArrayList<String>();
                tempwordarray.add(word);

                // if final key is already in hash table - append word
                if (hashtable.containsKey("finalkey")) {
                    // get array from key
                    tempwordarray = (ArrayList<String>) hashtable.get("finalkey");
                    tempwordarray.add(word);

                    // put the array back into the hashtable
                    hashtable.put("finalkey", tempwordarray);

                }

                // else create new entry for this key, append word
                else {

                    hashtable.put(finalkey, tempwordarray);
                }

                // clear the temporary array
                tempwordarray.clear();

            }


            // compare size of lists in hashtable and find out which one is the largest
            Set<String> keys = hashtable.keySet();

            // get the set of possible words that belongs to the current state of the word
            ArrayList<String> currentwordlist = (ArrayList<String>) hashtable.get(GamePreparation.underscoredword);
            String currentkey = GamePreparation.underscoredword;

            // iterate over all the lists of words in the hashtable
            for (String key : keys) {
                ArrayList<String> tempwordlist_2 = (ArrayList<String>) hashtable.get(key);

                // if the found list is bigger than the one before, use it
                if (tempwordlist_2.size() > currentwordlist.size()) {
                    currentwordlist = tempwordlist_2;
                    currentkey = key;
                }

            }

            // if there is no change in optimal key/list combination
            if (currentkey == GamePreparation.underscoredword) {
                currentguesses--;
                addWrongLetter(letter);
                wrongletterlist_textview.setText(getWrongLetters());
                wrongtriesleft_textview.setText(getGuessesString());
                return false;
            }

            else {
                // start building the new string do be displayed
                StringBuilder thewordbuilder = new StringBuilder(GamePreparation.underscoredword);

                for (int i = 0; i < GamePreparation.pickedword.length(); i++) {

                    if (GamePreparation.pickedword.charAt(i) == letter)
                        thewordbuilder.setCharAt(i, letter);
                }

                GamePreparation.underscoredword = thewordbuilder.toString();
                wordtoguess_textview.setText(GamePreparation.underscoredword);
                return true;
            }

        }

    }
}