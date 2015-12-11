package com.example.renske.hangman_basic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HistoryViewActivity extends AppCompatActivity {

    private ListView highscoreListView;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> highscoreList;
    private String word, guessesleft;
    private int gametype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        // initialize list and adapter
        highscoreList = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                highscoreList);
        highscoreListView = (ListView) findViewById(R.id.listView);
        highscoreListView.setAdapter(listAdapter);

        // get list with highscores from preferences if this is not the first time they are added
        SharedPreferences savedList = this.getSharedPreferences("savedhighscores", MODE_PRIVATE);
        Set<String> highscoreset = savedList.getStringSet("theSavedList", null);
        if (highscoreset != null) {
            List<String> savedlist = new ArrayList<String>(highscoreset);
            highscoreList.addAll(savedlist);
        }

        // if the user won a game, add their score to the table
        Intent intent = getIntent();
        if (intent.getStringExtra("SOURCE") == "win"); {
            putHighscoreFromIntentInArray(intent);
        }

        // if there are no highscores yet, display message accordingly
        if (highscoreset == null && intent.getStringExtra("SOURCE") != "win") {
            String[] nohighscores = new String[]{"No highscores yet."};
            highscoreList.addAll(Arrays.asList(nohighscores));
        }
    }

    // put highscore in list
    public void putHighscoreFromIntentInArray(Intent intent) {

        if (highscoreList.contains("No highscores yet.")){
            listAdapter.remove("No highscores yet.");
        }

        gametype = intent.getIntExtra("GAMETYPE", 1);
        guessesleft  = intent.getStringExtra("GUESSESLEFT");
        word = intent.getStringExtra("WORD");

        // build string to put in actual highscore table
        StringBuilder highscorebuilder = new StringBuilder();

            if (gametype == 1) {
                highscorebuilder.append("Type: Evil |");
            }
            else {
                highscorebuilder.append("Type: Good |");
            }

        highscorebuilder.append(" Guesses left: " + guessesleft + " |");
        highscorebuilder.append(" Word : " + word);
        String highscore = highscorebuilder.toString();
        listAdapter.add(highscore);

        // add the current state of highscore list to shared preferences
        SharedPreferences prefs = this.getSharedPreferences("savedhighscores", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        Set<String> highscoreset = new HashSet<String>();
        highscoreset.addAll(highscoreList);
        edit.putStringSet("theSavedList", highscoreset);
        edit.commit();
    }

    // redirect user to menu if back is pressed
    public void onBackPressed(){
            Intent intent = new Intent(this, StartScreenActivity.class);
            HistoryViewActivity.this.startActivity(intent);
        }
}
