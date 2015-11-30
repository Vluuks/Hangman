package com.example.renske.hangman_basic;

import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by Renske on 28-11-2015.
 */
////////////////////////////////////////////////////////////////////////////////////////////////////
// EVIL GAME PLAY
////////////////////////////////////////////////////////////////////////////////////////////////////

public class EvilGamePlay extends GamePlay {

    @Override
    public boolean checkInWord(char letter) {

        //check if this letter was already pressed
        if (wrongletterList.contains(letter)) {
            Toast.makeText(getApplicationContext(), "You already guessed" + letter,
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        // check whether letter is present in word
        else
        if (pickedword.indexOf(letter) >= 0) {

            // start building the new string do be displayed
            StringBuilder theWord = new StringBuilder(underscoredword);

            for (int i = 0; i < pickedword.length(); i++) {

                if (pickedword.charAt(i) == letter)
                    theWord.setCharAt(i, letter);
            }

            word.setText(theWord);
            underscoredword = theWord.toString();
            return true;
        }

        // if the letter is not in the word
        else {

            // if the user has guesses left
            if (currentguesses > 0) {
                currentguesses--;
                addWrongLetter(letter);
            }

            // if the user has no guesses left
            else {
                onLose();
            }

            return false;
        }



    }


    // handling user keyboard input
    @Override
    public void onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                checkInWord('a');
            case KeyEvent.KEYCODE_B:
                checkInWord('b');
            case KeyEvent.KEYCODE_C:
                checkInWord('c');
            case KeyEvent.KEYCODE_D:
                checkInWord('d');
            case KeyEvent.KEYCODE_E:
                checkInWord('e');
            case KeyEvent.KEYCODE_F:
                checkInWord('f');
            case KeyEvent.KEYCODE_G:
                checkInWord('g');
            case KeyEvent.KEYCODE_H:
                checkInWord('h');
            case KeyEvent.KEYCODE_I:
                checkInWord('i');
            case KeyEvent.KEYCODE_J:
                checkInWord('j');
            case KeyEvent.KEYCODE_K:
                checkInWord('k');
            case KeyEvent.KEYCODE_L:
                checkInWord('l');
            case KeyEvent.KEYCODE_M:
                checkInWord('m');
            case KeyEvent.KEYCODE_N:
                checkInWord('n');
            case KeyEvent.KEYCODE_O:
                checkInWord('o');
            case KeyEvent.KEYCODE_P:
                checkInWord('p');
            case KeyEvent.KEYCODE_Q:
                checkInWord('q');
            case KeyEvent.KEYCODE_R:
                checkInWord('r');
            case KeyEvent.KEYCODE_S:
                checkInWord('s');
            case KeyEvent.KEYCODE_T:
                checkInWord('t');
            case KeyEvent.KEYCODE_U:
                checkInWord('u');
            case KeyEvent.KEYCODE_V:
                checkInWord('v');
            case KeyEvent.KEYCODE_W:
                checkInWord('w');
            case KeyEvent.KEYCODE_X:
                checkInWord('x');
            case KeyEvent.KEYCODE_Y:
                checkInWord('y');
            case KeyEvent.KEYCODE_Z:
                checkInWord('z');
        }



    }

}