package com.example.renske.hangman_basic;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import java.io.File;

/**
 * The OptionsActivity allows a user to set their preferred word length, game mode (good or evil)
 * and the amount of wrong guesses allowed.
 */
public class OptionsActivity extends AppCompatActivity {

    public SeekBar wordLength_seekBar, wrongGuessesAllowed_seekBar;
    public Switch gamemode;
    public TextView wordLength_textView, wrongGuessesAllowed_textView;
    public int chosenGamemode, chosenGuesses, chosenMaxwordlength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // Initialize lay out components.
        wordLength_seekBar = (SeekBar) findViewById(R.id.wordlength);
        wrongGuessesAllowed_seekBar = (SeekBar) findViewById(R.id.guessesamount);
        gamemode = (Switch) findViewById(R.id.gamemode);
        wordLength_textView = (TextView) findViewById(R.id.wordlength_textview);
        wrongGuessesAllowed_textView = (TextView) findViewById(R.id.guessesamount_textview);
        wordLength_seekBar.setMax(15);
        wrongGuessesAllowed_seekBar.setMax(26);

        // Initialize listeners for components.
        trackSeekBarProgress(wordLength_seekBar);
        trackSeekBarProgress(wrongGuessesAllowed_seekBar);
        gamemode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chosenGamemode = 1;
                    gamemode.setText("Evil");
                }
                else {
                    chosenGamemode = 2;
                    gamemode.setText("Good");
                }
                saveOptions();
            }
        });

        // Check if there are sharedpreferences to load and set options accordingly.
        File f = new File("/data/data/com.example.renske.hangman_basic/shared_prefs/settings.xml");
        if (f.exists()) {
            SharedPreferences useroptions = this.getSharedPreferences("settings",
                    this.MODE_PRIVATE);
            chosenGuesses = useroptions.getInt("GUESSES", 5);
            chosenMaxwordlength = useroptions.getInt("WORDLENGTH", 6);
            chosenGamemode = useroptions.getInt("GAMETYPE", 1);
        }

        if(chosenGamemode == 1)
            gamemode.setChecked(true);
        else
            gamemode.setChecked(false);

        wordLength_seekBar.setProgress(chosenMaxwordlength);
        wrongGuessesAllowed_seekBar.setProgress(chosenGuesses);
    }

    /**
     * Saves user options to SharedPreferences.
     */
    public void saveOptions(){
        SharedPreferences settings = this.getSharedPreferences("settings",
                this.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("WORDLENGTH", wordLength_seekBar.getProgress());
        editor.putInt("GUESSES", wrongGuessesAllowed_seekBar.getProgress());
        editor.putInt("GAMETYPE", chosenGamemode);
        editor.commit();
    }

    /**
     * A custom listener for the SeekBars.
     */
    public void trackSeekBarProgress(SeekBar theSeekbar){

        theSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            // save options when user stops sliding seekbars
            @Override
            public void onStopTrackingTouch(SeekBar theSeekbar) {
                saveOptions();
            }

            @Override
            public void onStartTrackingTouch(SeekBar theSeekbar) {
                saveOptions();
            }

            // prevent user from picking values that are too low and adjust text
            @Override
            public void onProgressChanged(SeekBar theSeekbar, int progress, boolean fromUser) {
                if (theSeekbar == wordLength_seekBar) {
                    if(progress <= 2) {
                        wordLength_seekBar.setProgress(2);
                    }
                    wordLength_textView.setText(String.valueOf(progress));
                }
                if (theSeekbar == wrongGuessesAllowed_seekBar) {
                    if(progress < 1) {
                        wrongGuessesAllowed_seekBar.setProgress(2);
                    }
                    wrongGuessesAllowed_textView.setText(String.valueOf(progress));
                }
            }
        });
    }
}