package com.example.renske.hangman_basic;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Renske on 28-11-2015.
 */
////////////////////////////////////////////////////////////////////////////////////////////////////
// GOOD GAME PLAY
////////////////////////////////////////////////////////////////////////////////////////////////////

public class GoodGamePlay extends GamePlay {

    @Override
    public boolean checkInWord(char letter, TextView textview) {

        //check if this letter was already pressed
        if (wrongletters.indexOf(letter) != -1 || PrepareForGame.underscoredword.indexOf(letter) > 0) {
            Toast toast = Toast.makeText(getContext(), "You already guessed " + letter, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return false;
        }

        // check whether letter is present in word
        else {
            if (PrepareForGame.pickedword.indexOf(letter) >= 0) {

                // start building the new string do be displayed
                StringBuilder theWord = new StringBuilder(PrepareForGame.underscoredword);

                for (int i = 0; i < PrepareForGame.pickedword.length(); i++) {

                    if (PrepareForGame.pickedword.charAt(i) == letter)
                        theWord.setCharAt(i, letter);
                }

                PrepareForGame.underscoredword = theWord.toString();
                textview.setText(PrepareForGame.underscoredword);
                return true;
            }

            // if the letter is not in the word
            else {
                // if the user has guesses left
                if (currentguesses > 0) {
                    currentguesses--;
                    addWrongLetter(letter);
                }

                return false;
            }
        }


    }

}