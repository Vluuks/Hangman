package com.example.renske.hangman_basic;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class GoodGamePlay extends GamePlay {

    @Override
    public boolean checkInWord(char letter, TextView wordToGuess_textView,
        TextView wrongLetterList_textView, TextView wrongTriesLeft_textView) {

        //check if this letter was already pressed
        if (wrongLetterString.indexOf(letter) != -1 || GamePreparation.underscoredWord.indexOf(letter) > 0) {
            Toast toast = Toast.makeText(getContext(), "You already guessed " + letter, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return false;
        }

        // check whether letter is present in word
        else {
            if (GamePreparation.pickedWord.indexOf(letter) >= 0) {

                // start building the new string do be displayed
                StringBuilder theWord = new StringBuilder(GamePreparation.underscoredWord);

                for (int i = 0; i < GamePreparation.pickedWord.length(); i++) {

                    if (GamePreparation.pickedWord.charAt(i) == letter)
                        theWord.setCharAt(i, letter);
                }

                GamePreparation.underscoredWord = theWord.toString();
                wordToGuess_textView.setText(GamePreparation.underscoredWord);
                return true;
            }

            // if the letter is not in the word
            else {
                // if the user has guesses left
                if (currentGuesses > 0) {
                    currentGuesses--;
                    addWrongLetter(letter);
                    wrongLetterList_textView.setText(getWrongLetters());
                    wrongTriesLeft_textView.setText(getGuessesString());
                }
                return false;
            }
        }
    }
}