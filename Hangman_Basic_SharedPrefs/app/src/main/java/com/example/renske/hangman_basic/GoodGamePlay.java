package com.example.renske.hangman_basic;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Renske on 28-11-2015.
 */
////////////////////////////////////////////////////////////////////////////////////////////////////
// GOOD GAME PLAY
////////////////////////////////////////////////////////////////////////////////////////////////////

public class GoodGamePlay extends GamePlay {

    @Override
    public boolean checkInWord(char letter) {

        //check if this letter was already pressed
        if (wrongletterList.contains(letter)) {
            Toast.makeText(getApplicationContext(), "You already guessed" + letter,
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        // check whether letter is present in word
        else {
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
                    onLose(this);  // takes current activity as argument, no idea if this is correct TODO
                }

                return false;
            }
        }



    }


    @Override
    public void manageKeys() {

        View.OnKeyListener listener = new View.OnKeyListener() {

            // handling user keyboard input
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_A:
                        checkInWord('a');
                        return true;
                    case KeyEvent.KEYCODE_B:
                        checkInWord('b');
                        return true;
                    case KeyEvent.KEYCODE_C:
                        checkInWord('c');
                        return true;
                    case KeyEvent.KEYCODE_D:
                        checkInWord('d');
                        return true;
                    case KeyEvent.KEYCODE_E:
                        checkInWord('e');
                        return true;
                    case KeyEvent.KEYCODE_F:
                        checkInWord('f');
                        return true;
                    case KeyEvent.KEYCODE_G:
                        checkInWord('g');
                        return true;
                    case KeyEvent.KEYCODE_H:
                        checkInWord('h');
                        return true;
                    case KeyEvent.KEYCODE_I:
                        checkInWord('i');
                        return true;
                    case KeyEvent.KEYCODE_J:
                        checkInWord('j');
                        return true;
                    case KeyEvent.KEYCODE_K:
                        checkInWord('k');
                        return true;
                    case KeyEvent.KEYCODE_L:
                        checkInWord('l');
                        return true;
                    case KeyEvent.KEYCODE_M:
                        checkInWord('m');
                        return true;
                    case KeyEvent.KEYCODE_N:
                        checkInWord('n');
                        return true;
                    case KeyEvent.KEYCODE_O:
                        checkInWord('o');
                        return true;
                    case KeyEvent.KEYCODE_P:
                        checkInWord('p');
                        return true;
                    case KeyEvent.KEYCODE_Q:
                        checkInWord('q');
                        return true;
                    case KeyEvent.KEYCODE_R:
                        checkInWord('r');
                        return true;
                    case KeyEvent.KEYCODE_S:
                        checkInWord('s');
                        return true;
                    case KeyEvent.KEYCODE_T:
                        checkInWord('t');
                        return true;
                    case KeyEvent.KEYCODE_U:
                        checkInWord('u');
                        return true;
                    case KeyEvent.KEYCODE_V:
                        checkInWord('v');
                        return true;
                    case KeyEvent.KEYCODE_W:
                        checkInWord('w');
                        return true;
                    case KeyEvent.KEYCODE_X:
                        checkInWord('x');
                        return true;
                    case KeyEvent.KEYCODE_Y:
                        checkInWord('y');
                        return true;
                    case KeyEvent.KEYCODE_Z:
                        checkInWord('z');
                        return true;

                    default:
                        return false;
                }

            }

        };
    }

}

