package com.example.renske.hangman_basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView wordToGuess_textView, wrongLetterList_textView, wrongTriesLeft_textView;
    public GamePreparation startGame;
    public EvilGamePlay evilGame;
    public GoodGamePlay goodGame;
    public int gametype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize views in the layout
        wordToGuess_textView = (TextView) findViewById(R.id.main_textview);
        wrongLetterList_textView = (TextView) findViewById(R.id.wrongletterlist_textview);
        wrongTriesLeft_textView = (TextView) findViewById(R.id.guessesleft_textview);

        // start the game's initialization
        startGame = new GamePreparation();
        startGame.loadDictionary(MainActivity.this);
        startGame.initializeGame(MainActivity.this);
        startGame.pickInitialWord(wordToGuess_textView);

        // obtain game type from initializiation
        gametype = startGame.getGameType();

        // determine which abstract implementation should be used from now on
        if (gametype == 1) {
            evilGame = new EvilGamePlay();
            evilGame.setContext(MainActivity.this);
            evilGame.setGuesses(startGame.wrongTriesAllowed, wrongTriesLeft_textView);
            evilGame.setWrongletters(" ", wrongLetterList_textView);
        }
        else {
            goodGame = new GoodGamePlay();
            goodGame.setContext(MainActivity.this);
            goodGame.setGuesses(startGame.wrongTriesAllowed, wrongTriesLeft_textView);
            goodGame.setWrongletters(" ", wrongLetterList_textView);
        }
    }

    // redirect user's keyboard input to the correct gametype
    public void redirectInput(char letter) {

        // if the chosen game type is evil
        if (gametype == 1) {
            evilGame.checkInWord(letter, wordToGuess_textView, wrongLetterList_textView,
                    wrongTriesLeft_textView);

            // if the word is guessed, save score and continue to highscores
            if (wordToGuess_textView.getText().toString().indexOf('_') == -1) {
                goodGame.onWin(MainActivity.this, MainActivity.this, wordToGuess_textView);
            }

            // if the user runs out of guesses, continue to highscores
            if (evilGame.getGuesses() == 0) {
                evilGame.onLose(MainActivity.this, MainActivity.this);
            }
        }

        // if the chosen game type is good
        else {
            goodGame.checkInWord(letter, wordToGuess_textView, wrongLetterList_textView,
                    wrongTriesLeft_textView);

            // if the word is guessed, save score and continue to highscores
            if (wordToGuess_textView.getText().toString().indexOf('_') == -1) {
                goodGame.onWin(MainActivity.this, MainActivity.this, wordToGuess_textView);
            }

            // if the user runs out of guesses, continue to highscores
            if (goodGame.getGuesses() == 0) {
                goodGame.onLose(MainActivity.this, MainActivity.this);
            }
        }
    }

    // handle user keyboard input
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                redirectInput('A');
                return true;
            case KeyEvent.KEYCODE_B:
                redirectInput('B');
                return true;
            case KeyEvent.KEYCODE_C:
                redirectInput('C');
                return true;
            case KeyEvent.KEYCODE_D:
                redirectInput('D');
                return true;
            case KeyEvent.KEYCODE_E:
                redirectInput('E');
                return true;
            case KeyEvent.KEYCODE_F:
                redirectInput('F');
                return true;
            case KeyEvent.KEYCODE_G:
                redirectInput('G');
                return true;
            case KeyEvent.KEYCODE_H:
                redirectInput('H');
                return true;
            case KeyEvent.KEYCODE_I:
                redirectInput('I');
                return true;
            case KeyEvent.KEYCODE_J:
                redirectInput('J');
                return true;
            case KeyEvent.KEYCODE_K:
                redirectInput('K');
                return true;
            case KeyEvent.KEYCODE_L:
                redirectInput('L');
                return true;
            case KeyEvent.KEYCODE_M:
                redirectInput('M');
                return true;
            case KeyEvent.KEYCODE_N:
                redirectInput('N');
                return true;
            case KeyEvent.KEYCODE_O:
                redirectInput('O');
                return true;
            case KeyEvent.KEYCODE_P:
                redirectInput('P');
                return true;
            case KeyEvent.KEYCODE_Q:
                redirectInput('Q');
                return true;
            case KeyEvent.KEYCODE_R:
                redirectInput('R');
                return true;
            case KeyEvent.KEYCODE_S:
                redirectInput('S');
                return true;
            case KeyEvent.KEYCODE_T:
                redirectInput('T');
                return true;
            case KeyEvent.KEYCODE_U:
                redirectInput('U');
                return true;
            case KeyEvent.KEYCODE_V:
                redirectInput('V');
                return true;
            case KeyEvent.KEYCODE_W:
                redirectInput('W');
                return true;
            case KeyEvent.KEYCODE_X:
                redirectInput('X');
                return true;
            case KeyEvent.KEYCODE_Y:
                redirectInput('Y');
                return true;
            case KeyEvent.KEYCODE_Z:
                redirectInput('Z');
                return true;
            case KeyEvent.KEYCODE_BACK:
                Intent intent = new Intent(this, StartScreenActivity.class);
                MainActivity.this.startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}