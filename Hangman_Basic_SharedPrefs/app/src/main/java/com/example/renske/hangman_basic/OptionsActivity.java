package com.example.renske.hangman_basic;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

public SeekBar wordlength, guessesamount;
    public Switch gamemode;
    public TextView wordlength_textview, guessesamount_textview;
    public int chosen_gamemode, chosen_guesses, chosen_maxwordlength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // initialize lay out components
        wordlength = (SeekBar) findViewById(R.id.wordlength);
        guessesamount = (SeekBar) findViewById(R.id.guessesamount);
        gamemode = (Switch) findViewById(R.id.gamemode);

        wordlength_textview = (TextView) findViewById(R.id.wordlength_textview);
        guessesamount_textview = (TextView) findViewById(R.id.guessesamount_textview);

        // get list with highscores from preferences
        SharedPreferences useroptions = this.getSharedPreferences("settings", MODE_PRIVATE);

        chosen_guesses = useroptions.getInt("GUESSES", 5);
        chosen_maxwordlength = useroptions.getInt("WORDLENGTH", 6);
        chosen_gamemode = useroptions.getInt("GAMETYPE", 1);

        // set the options accordingly
        if(chosen_gamemode == 1)
            gamemode.setChecked(false);
        else
            gamemode.setChecked(true);

        wordlength.setProgress(chosen_maxwordlength);
        guessesamount.setProgress(chosen_guesses);

    }


    // get the options the user specified and put them in shared preferences
    // add an onchange listener for every option bar/thing so that the options
    // are changed when the user changes something
    public void saveOptions(){

        int chosen_wordlength = wordlength.getProgress();
        int chosen_guesses = guessesamount.getProgress();

        // store the user's settings in the shared preferences
        SharedPreferences useroptions = this.getSharedPreferences("settings",
                this.MODE_PRIVATE);
        SharedPreferences.Editor editor = useroptions.edit();
        editor.putInt("WORDLENGTH", chosen_wordlength);
        editor.putInt("GUESSES", chosen_guesses);
        editor.putInt("GAMETYPE", chosen_gamemode);
        editor.commit();

    }


    public void trackOptions(){

        trackSeekBarProgress(wordlength);
        trackSeekBarProgress(guessesamount);

        gamemode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    chosen_gamemode = 1; //evil
                } else {
                    chosen_gamemode = 2; //gdod
                }

                saveOptions();
            }
        });


    }








    // credit: http://stackoverflow.com/questions/8956218/android-seekbar-setonseekbarchangelistener
    public void trackSeekBarProgress(SeekBar theSeekbar){

        theSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar theSeekbar) {
                saveOptions();
            }

            @Override
            public void onStartTrackingTouch(SeekBar theSeekbar) {
                // not needed? not sure keep for now
            }

            @Override
            public void onProgressChanged(SeekBar theSeekbar, int progress, boolean fromUser) {
                wordlength_textview.setText(String.valueOf(progress));

            }
        });


    }

}
