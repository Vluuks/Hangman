package com.example.renske.hangman_basic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * The startscreen contains three buttons leading to respectively a new game, the options and
 * the list containing user's highscores.
 */
public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    // Obtain settings and redirect to game activity.
    public void startNewGame(View view) {
        SharedPreferences useroptions = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        int chosenWordlength = useroptions.getInt("WORDLENGTH", 5);
        int chosenGuesses = useroptions.getInt("GUESSES", 7);
        int chosenGametype = useroptions.getInt("GAMETYPE", 2);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("SOURCE", "start");
        StartScreenActivity.this.startActivity(intent);
    }

    // Redirect to highscore activity.
    public void goToHighscores(View view) {
        Intent intent = new Intent(this, HistoryViewActivity.class);
        intent.putExtra("SOURCE", "start");
        StartScreenActivity.this.startActivity(intent);
    }

    // Redirect to option activity.
    public void goToOptions(View view) {
        Intent intent = new Intent(this, OptionsActivity.class);
        StartScreenActivity.this.startActivity(intent);
    }
}