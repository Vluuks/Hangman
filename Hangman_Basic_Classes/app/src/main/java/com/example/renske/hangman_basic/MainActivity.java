package com.example.renske.hangman_basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


}



////////////////////////////////////////////////////////////////////////////////////////////////////
// ABSTRACT GAMEPLAY CLASS
////////////////////////////////////////////////////////////////////////////////////////////////////

    abstract class GamePlay extends AppCompatActivity {

        private ArrayList<String> wordList;
        public static String underscoredword;
        private static String pickedword;
        public TextView word;
        public static int guesses, score, wordlength;


        public void loadDictionary() {
            // String[] wordstoguess = new String[]{"pompoen", "appel", "mandarijn", "pastinaak", "peer"};
            String[] wordstoguess = getResources().getStringArray(R.array.words_small);
            wordList = new ArrayList<String>();
            wordList.addAll(Arrays.asList(wordstoguess));
        }

        public void initializeGame() {

            guesses = 5; // get these values from settings activity later
            score = 0;
            wordlength = 15;

        }


        public void saveGameStatus() {

            // put things in the bundle/sharedpreferences
        }

        public void onWin() {
            // do something

        }


        public void onLose() {
            // do something

        }




        public void pickInitialWord(int maxwordlength) {
            // if user has specified less than max word length
            int longestword = 15; // longest word in dictionary, idk if its 15 for sure though
            if (maxwordlength != longestword) {

                // keep picking a random word until it has the right length
                while (wordlength > maxwordlength) {
                    Random randomizer = new Random();
                    pickedword = wordList.get(randomizer.nextInt(wordList.size()));
                    wordlength = pickedword.length();
                }
            }

            // if the user has not specified a maximum value
            else {
                // pick a random word to start
                Random randomizer = new Random();
                pickedword = wordList.get(randomizer.nextInt(wordList.size()));
                wordlength = pickedword.length();
            }

            // set the underscores to the right length
            underscoredword = new String(new char[wordlength]).replace("\0", "_");
            word = (TextView) findViewById(R.id.maintextview);
            word.setText(underscoredword);
        }



        // methods to change and access variables outside of the gameplay class

        public static String getCurrentWord() {
            return pickedword;
        }

        public static String getUnderScoredWord() {
            return underscoredword;
        }

        public static void setUnderScoredWord(String newunderscoredword) {
            underscoredword = newunderscoredword;
        }

        public static int getGuesses() {
            return guesses;
        }

        public static void setGuesses(int a_value) {
            guesses = a_value;
        }

        // the abstract methods to be implemented by the sublcasses evil and good
        abstract boolean checkInWord(char letter);
        abstract boolean onKeyUp(int keyCode, KeyEvent event);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////
// GOOD GAME PLAY
////////////////////////////////////////////////////////////////////////////////////////////////////

        public class GoodGamePlay extends GamePlay {


            public boolean checkInWord(char letter) {

                // get current word
                String pickedword = GamePlay.getCurrentWord();

                // check whether letter is present in word
                if (pickedword.indexOf(letter) >= 0) {

                    // start building the new string do be displayed
                    StringBuilder theWord = new StringBuilder(GamePlay.getUnderScoredWord());

                    for (int i = 0; i < pickedword.length(); i++) {

                        if (pickedword.charAt(i) == letter)
                            theWord.setCharAt(i, letter);
                    }

                    word.setText(theWord);
                    setUnderScoredWord(theWord.toString());
                    return true;
                } else
                    return false;
                // decrease amount of guesses left

            }


            // handling user keyboard input
            @Override
            public boolean onKeyUp(int keyCode, KeyEvent event) {
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
                        return super.onKeyUp(keyCode, event);
                    // DOE NIKS ALS HET GEEN LETTER IS?
                }


            }

        }

        public class EvilGamePlay extends GamePlay {


            public boolean checkInWord(char letter) {

                // get current word
                String pickedword = GamePlay.getCurrentWord();

                // check whether letter is present in word
                if (pickedword.indexOf(letter) >= 0) {


                    //implement evil logic to avoid user guessing the word
                    // TODO









                    // start building the new string do be displayed
                    StringBuilder theWord = new StringBuilder(GamePlay.getUnderScoredWord());

                    for (int i = 0; i < pickedword.length(); i++) {

                        if (pickedword.charAt(i) == letter)
                            theWord.setCharAt(i, letter);
                    }

                    word.setText(theWord);
                    underscoredword = theWord.toString();
                    return true;
                } else
                    return false;
                // decrease amount of guesses left

            }


            // handling user keyboard input
            @Override
            public boolean onKeyUp(int keyCode, KeyEvent event) {
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
                        return super.onKeyUp(keyCode, event);
                    // DOE NIKS ALS HET GEEN LETTER IS?
                }


            }

        }

}


