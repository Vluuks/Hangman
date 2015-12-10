package com.example.renske.hangman_basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView wordtoguess_textview, wrongletterlist_textview, wrongtriesleft_textview;
    public GamePreparation startgame;
    public EvilGamePlay evilgame;
    public GoodGamePlay goodgame;
    public int gametype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize views in the layout
        wordtoguess_textview = (TextView) findViewById(R.id.main_textview);
        wrongletterlist_textview = (TextView) findViewById(R.id.wrongletterlist_textview);
        wrongtriesleft_textview = (TextView) findViewById(R.id.guessesleft_textview);

        // start the game's initialization
        startgame = new GamePreparation();
        startgame.loadDictionary(MainActivity.this);
        startgame.initializeGame(MainActivity.this);
        startgame.pickInitialWord(wordtoguess_textview);

        // obtain game type from initializiation
        gametype = startgame.getGameType();

        // determine which abstract implementation should be used from now on
        if (gametype == 1) {
            evilgame = new EvilGamePlay();
            evilgame.setContext(MainActivity.this);
            evilgame.setGuesses(startgame.wrongtriesallowed, wrongtriesleft_textview);
            evilgame.setWrongletters(" ", wrongletterlist_textview);
        }
        else {
            goodgame = new GoodGamePlay();
            goodgame.setContext(MainActivity.this);
            goodgame.setGuesses(startgame.wrongtriesallowed, wrongtriesleft_textview);
            goodgame.setWrongletters(" ", wrongletterlist_textview);
        }
    }

    // redirect user's keyboard input to the correct gametype
    public void redirectInput(char letter) {

        // if the chosen game type is evil
        if (gametype == 1) {
            evilgame.checkInWord(letter, wordtoguess_textview, wrongletterlist_textview, wrongtriesleft_textview);

            // if the word is guessed, save score and continue to highscores
            if (wordtoguess_textview.getText().toString().indexOf('_') == -1) {
                goodgame.onWin(MainActivity.this, MainActivity.this, wordtoguess_textview);
            }

            // if the user runs out of guesses, continue to highscores
            if (evilgame.getGuesses() == 0) {
                evilgame.onLose(MainActivity.this, MainActivity.this);
            }
        }

        // if the chosen game type is good
        else {
            goodgame.checkInWord(letter, wordtoguess_textview, wrongletterlist_textview, wrongtriesleft_textview);

            // if the word is guessed, save score and continue to highscores
            if (wordtoguess_textview.getText().toString().indexOf('_') == -1) {
                goodgame.onWin(MainActivity.this, MainActivity.this, wordtoguess_textview);
            }

            // if the user runs out of guesses, continue to highscores
            if (goodgame.getGuesses() == 0) {
                goodgame.onLose(MainActivity.this, MainActivity.this);
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




















