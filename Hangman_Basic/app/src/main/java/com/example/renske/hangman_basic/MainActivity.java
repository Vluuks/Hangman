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
    public int gametype;

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
        startgame.loadDictionary(MainActivity.this);
        startgame.initializeGame(MainActivity.this);
        startgame.pickInitialWord(wordview);

        // obtain game type from initializiation
        gametype = startgame.getGameType();


        if (gametype == 1) {
            // tell program which code to use to handle key presses and guessed letters
            theevilgame = new EvilGamePlay();
            theevilgame.setContext(MainActivity.this);
            theevilgame.setGuesses(startgame.guessesallowed, guessesleft);
        }

        else {
            thegoodgame = new GoodGamePlay();
            thegoodgame.setContext(MainActivity.this);
            thegoodgame.setGuesses(startgame.guessesallowed, guessesleft);

        }
    }



    // !!!!!!! DEZE MOET IN HIER BLIJVEN STAAN KAN NIET IN GOOD/EVIL CLASS DAN WERKT HET NIET
    // handling user keyboard input
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

    public void redirectInput(char letter) {
        int gametype = startgame.getGameType();

        // if the chosen game type is evil
        if (gametype == 1) {
            if (theevilgame.checkInWord(letter, wordview) == false) {
                wrongletterlist.setText(theevilgame.getWrongLetters());
                guessesleft.setText(theevilgame.getGuessesString());
            }

            // if the user has guessed all the letters, go to highscores
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

        // if the chosen game type is good
        else {
            if (thegoodgame.checkInWord(letter, wordview) == false) {
                wrongletterlist.setText(thegoodgame.getWrongLetters());
                guessesleft.setText(thegoodgame.getGuessesString());
            }

            // if the user has guessed all the letters, go to highscores
            if (wordview.getText().toString().indexOf('_') == -1) {
                wrongletterlist.setText(" ");
                Intent intent = new Intent(this, HighscoresActivity.class);
                intent.putExtra("GUESSESLEFT", thegoodgame.getGuessesString());
                intent.putExtra("GAMETYPE", gametype);
                intent.putExtra("WORD", wordview.getText().toString());
                intent.putExtra("SOURCE", "win");
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




















