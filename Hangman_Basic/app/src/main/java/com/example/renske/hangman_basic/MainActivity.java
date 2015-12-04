package com.example.renske.hangman_basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView wordview, wrongletterlist, guessesleft;
    public PrepareForGame startgame;
    public EvilGamePlay theevilgame;
    public GoodGamePlay thegoodgame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize views in the layout
        wordview = (TextView) findViewById(R.id.main_textview);
        wrongletterlist = (TextView) findViewById(R.id.wrongletterlist_textview);
        guessesleft = (TextView) findViewById(R.id.guessesleft_textview);

        // start the game's initialization
        startgame = new PrepareForGame();
        startgame.initializeGame(this);
        startgame.loadDictionary();

        startgame.pickInitialWord(wordview);


        // obtain game type from initializiation
        int gametype = startgame.getGameType();

        if (gametype == 1) {
            // tell program which code to use to handle key presses and guessed letters
            theevilgame = new EvilGamePlay();
            theevilgame.setInitialGuesses(startgame.guessesallowed, guessesleft);
            theevilgame.setContext(MainActivity.this);
        }

        else {
            thegoodgame = new GoodGamePlay();
            thegoodgame.setInitialGuesses(startgame.guessesallowed, guessesleft);
            thegoodgame.setContext(MainActivity.this);

            }


        }



    // !!!!!!! DEZE MOET IN HIER BLIJVEN STAAN KAN NIET IN GOOD/EVIL CLASS DAN WERKT HET NIET
    // handling user keyboard input
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                redirectInput('a');
                return true;
            case KeyEvent.KEYCODE_B:
                redirectInput('b');
                return true;
            case KeyEvent.KEYCODE_C:
                redirectInput('c');
                return true;
            case KeyEvent.KEYCODE_D:
                redirectInput('d');
                return true;
            case KeyEvent.KEYCODE_E:
                redirectInput('e');
                return true;
            case KeyEvent.KEYCODE_F:
                redirectInput('f');
                return true;
            case KeyEvent.KEYCODE_G:
                redirectInput('g');
                return true;
            case KeyEvent.KEYCODE_H:
                redirectInput('h');
                return true;
            case KeyEvent.KEYCODE_I:
                redirectInput('i');
                return true;
            case KeyEvent.KEYCODE_J:
                redirectInput('j');
                return true;
            case KeyEvent.KEYCODE_K:
                redirectInput('k');
                return true;
            case KeyEvent.KEYCODE_L:
                redirectInput('l');
                return true;
            case KeyEvent.KEYCODE_M:
                redirectInput('m');
                return true;
            case KeyEvent.KEYCODE_N:
                redirectInput('n');
                return true;
            case KeyEvent.KEYCODE_O:
                redirectInput('o');
                return true;
            case KeyEvent.KEYCODE_P:
                redirectInput('p');
                return true;
            case KeyEvent.KEYCODE_Q:
                redirectInput('q');
                return true;
            case KeyEvent.KEYCODE_R:
                redirectInput('r');
                return true;
            case KeyEvent.KEYCODE_S:
                redirectInput('s');
                return true;
            case KeyEvent.KEYCODE_T:
                redirectInput('t');
                return true;
            case KeyEvent.KEYCODE_U:
                redirectInput('u');
                return true;
            case KeyEvent.KEYCODE_V:
                redirectInput('v');
                return true;
            case KeyEvent.KEYCODE_W:
                redirectInput('w');
                return true;
            case KeyEvent.KEYCODE_X:
                redirectInput('x');
                return true;
            case KeyEvent.KEYCODE_Y:
                redirectInput('y');
                return true;
            case KeyEvent.KEYCODE_Z:
                redirectInput('z');
                return true;
            case KeyEvent.KEYCODE_BACK:
                goToMenu();
                return true;

            default:
                return false;
        }

    }

    public void goToMenu(){
        Intent intent = new Intent(this, StartScreenActivity.class);
        MainActivity.this.startActivity(intent);

    }

    public void redirectInput(char letter) {
        int gametype = startgame.getGameType();

        if (gametype == 1) {
            if (theevilgame.checkInWord(letter, wordview) == false) {
                wrongletterlist.setText(theevilgame.getWrongLetters());
                guessesleft.setText(theevilgame.getGuessesString());
            }

            if (wordview.getText().toString().indexOf('_') == -1) {
                    Intent intent = new Intent(this, HighscoresActivity.class);
                    intent.putExtra("GUESSESLEFT", theevilgame.getGuessesString());
                    intent.putExtra("GAMETYPE", gametype);
                    intent.putExtra("WORD", wordview.getText().toString());
                    MainActivity.this.startActivity(intent);
                }

                // if the user is out of guesses, stop the game
                if (theevilgame.getGuesses() == 0) {
                    Intent intent = new Intent(this, HighscoresActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            }


        else {
            if (thegoodgame.checkInWord(letter, wordview) == false) {
                wrongletterlist.setText(thegoodgame.getWrongLetters());
                guessesleft.setText(thegoodgame.getGuessesString());
            }

            if (wordview.getText().toString().indexOf('_') == -1) {
                Intent intent = new Intent(this, HighscoresActivity.class);
                intent.putExtra("GUESSESLEFT", thegoodgame.getGuessesString());
                intent.putExtra("GAMETYPE", gametype);
                intent.putExtra("WORD", wordview.getText().toString());
                MainActivity.this.startActivity(intent);
            }

            // if the user is out of guesses, stop the game
            if (thegoodgame.getGuesses() == 0) {
                Intent intent = new Intent(this, HighscoresActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }

        }


    }




















