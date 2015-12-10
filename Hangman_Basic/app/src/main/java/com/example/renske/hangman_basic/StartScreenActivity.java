package com.example.renske.hangman_basic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

    }

    public void startNewGame(View view) {

        // obtain user's settings from sharedpreferences
        SharedPreferences useroptions = this.getSharedPreferences("settings",
                this.MODE_PRIVATE);
        float savedNumber1 = useroptions.getFloat("1", 0);
        int chosen_wordlength = useroptions.getInt("WORDLENGTH", 5);
        int chosen_guesses = useroptions.getInt("GUESSES", 7);
        int chosen_gametype = useroptions.getInt("GAMETYPE", 2);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("SOURCE", "start");
        StartScreenActivity.this.startActivity(intent);

    }

    public void goToHighscores(View view) {
        Intent intent = new Intent(this, HistoryViewActivity.class);
        intent.putExtra("SOURCE", "start");
        StartScreenActivity.this.startActivity(intent);

    }

    public void goToOptions(View view) {
        Intent intent = new Intent(this, OptionsActivity.class);
        StartScreenActivity.this.startActivity(intent);
    }


}
