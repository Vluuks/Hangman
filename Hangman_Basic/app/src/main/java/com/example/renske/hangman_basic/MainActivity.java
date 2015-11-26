package com.example.renske.hangman_basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> wordList;
    private int wordlength;
    private String underscoredword;
    private String pickedword;
    public TextView word;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] wordstoguess = new String[]{"pompoen", "appel", "mandarijn", "pastinaak", "peer"};
        wordList = new ArrayList<String>();
        wordList.addAll(Arrays.asList(wordstoguess));

        Random randomizer = new Random();
        pickedword = wordList.get(randomizer.nextInt(wordList.size()));

        wordlength = pickedword.length();
        underscoredword = new String(new char[wordlength]).replace("\0", "_");

        word = (TextView) findViewById(R.id.maintextview);
        word.setText(underscoredword);


    }


    public boolean checkInWord(char letter) {

        // check whether letter is present in word
        if (pickedword.indexOf(letter) >= 0) {

            // start building the new string do be displayed
            StringBuilder theWord = new StringBuilder(underscoredword);

            for (int i = 0; i < pickedword.length(); i++){

                if (pickedword.charAt(i) == letter)
                    theWord.setCharAt(i,letter);
            }

            word.setText(theWord);
            underscoredword = theWord.toString();
            return true;
        }

        else
            return false;

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
