package com.example.renske.hangman_basic;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;

public class OptionsActivity extends AppCompatActivity {

    public SeekBar wordlength_seekbar, guessesamount_seekbar;
    public Switch gamemode;
    public TextView wordlength_textview, guessesamount_textview;
    public int chosen_gamemode, chosen_guesses, chosen_maxwordlength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // initialize lay out components
        wordlength_seekbar = (SeekBar) findViewById(R.id.wordlength);
        guessesamount_seekbar = (SeekBar) findViewById(R.id.guessesamount);
        gamemode = (Switch) findViewById(R.id.gamemode);
        wordlength_textview = (TextView) findViewById(R.id.wordlength_textview);
        guessesamount_textview = (TextView) findViewById(R.id.guessesamount_textview);
        wordlength_seekbar.setMax(15);
        guessesamount_seekbar.setMax(26);

        // initialize listeners for components
        trackSeekBarProgress(wordlength_seekbar);
        trackSeekBarProgress(guessesamount_seekbar);
        gamemode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chosen_gamemode = 1;
                    gamemode.setText("Evil");
                }
                else {
                    chosen_gamemode = 2;
                    gamemode.setText("Good");
                }
                saveOptions();
            }
        });

        // check if there are sharedpreferences to load
        File f = new File("/data/data/com.example.renske.hangman_basic/shared_prefs/settings.xml");
        if (f.exists()) {
            SharedPreferences useroptions = this.getSharedPreferences("settings",
                    this.MODE_PRIVATE);
            chosen_guesses = useroptions.getInt("GUESSES", 5);
            chosen_maxwordlength = useroptions.getInt("WORDLENGTH", 6);
            chosen_gamemode = useroptions.getInt("GAMETYPE", 1);
        }

        // set the options accordingly
        if(chosen_gamemode == 1)
            gamemode.setChecked(true);
        else
            gamemode.setChecked(false);

        wordlength_seekbar.setProgress(chosen_maxwordlength);
        guessesamount_seekbar.setProgress(chosen_guesses);
    }

    // get the options the user specified and put them in shared preferences
    public void saveOptions(){
        int chosen_wordlength = wordlength_seekbar.getProgress();
        int chosen_guesses = guessesamount_seekbar.getProgress();

        SharedPreferences useroptions = this.getSharedPreferences("settings",
                this.MODE_PRIVATE);
        SharedPreferences.Editor editor = useroptions.edit();
        editor.putInt("WORDLENGTH", chosen_wordlength);
        editor.putInt("GUESSES", chosen_guesses);
        editor.putInt("GAMETYPE", chosen_gamemode);
        editor.commit();
    }

    // custom listener for seekbars
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
                if (theSeekbar == wordlength_seekbar) {
                    if(progress <= 2) {
                        wordlength_seekbar.setProgress(2);
                    }
                    wordlength_textview.setText(String.valueOf(progress));
                }
                if (theSeekbar == guessesamount_seekbar) {
                    if(progress < 1) {
                        guessesamount_seekbar.setProgress(2);
                    }
                    guessesamount_textview.setText(String.valueOf(progress));
                }
            }
        });
    }
}