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
    public boolean checkInWord(char letter, TextView wordtoguess_textview, TextView wrongletterlist_textview, TextView wrongtriesleft_textview) {

        //check if this letter was already pressed
        if (wrongletters.indexOf(letter) != -1 || GamePreparation.underscoredword.indexOf(letter) > 0) {
            Toast toast = Toast.makeText(getContext(), "You already guessed " + letter, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return false;
        }

        // check whether letter is present in word
        else {
            if (GamePreparation.pickedword.indexOf(letter) >= 0) {

                // start building the new string do be displayed
                StringBuilder theWord = new StringBuilder(GamePreparation.underscoredword);

                for (int i = 0; i < GamePreparation.pickedword.length(); i++) {

                    if (GamePreparation.pickedword.charAt(i) == letter)
                        theWord.setCharAt(i, letter);
                }

                GamePreparation.underscoredword = theWord.toString();
                wordtoguess_textview.setText(GamePreparation.underscoredword);
                return true;
            }

            // if the letter is not in the word
            else {
                // if the user has guesses left
                if (currentguesses > 0) {
                    currentguesses--;
                    addWrongLetter(letter);
                    wrongletterlist_textview.setText(getWrongLetters());
                    wrongtriesleft_textview.setText(getGuessesString());

                }

                return false;
            }
        }


    }

}