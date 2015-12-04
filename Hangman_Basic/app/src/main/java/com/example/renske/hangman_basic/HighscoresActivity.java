package com.example.renske.hangman_basic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HighscoresActivity extends AppCompatActivity {


    private ListView highscoreListView;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> highscoreList;
    private String word, guessesleft;
    private int gametype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);


        highscoreList = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, highscoreList);
        Intent intent = getIntent();

        // create and set arrayadapter
        highscoreListView = (ListView) findViewById(R.id.listView);
        highscoreListView.setAdapter(listAdapter);

        // get list with highscores from preferences if this is not the first time they are added
        SharedPreferences savedList = this.getSharedPreferences("savedhighscores", MODE_PRIVATE);
        Set<String> highscoreset = savedList.getStringSet("theSavedList", null);

        // if there are preferences, use those
        if (highscoreset != null) {
            List<String> savedlist = new ArrayList<String>(highscoreset);
            highscoreList.addAll(savedlist);

        }

        if (intent.getStringExtra("GUESSESLEFT") != null); {
            if (highscoreset != null) {
                List<String> savedlist = new ArrayList<String>(highscoreset);
                highscoreList.addAll(savedlist);
            }
            putHighscoreFromIntentInArray(intent);
        }

        // if no intent was found and there are no highscores yet
        if (highscoreset == null && intent.getStringExtra("GUESSESLEFT") == null) {
            String[] initialtodos = new String[]{"No highscores yet."};
            highscoreList.addAll(Arrays.asList(initialtodos));
        }





    }

    // put user input in the array
    public void putHighscoreFromIntentInArray(Intent intent) {

        if (highscoreList.contains("No highscores yet.")){
            listAdapter.remove("No highscores yet.");
        }

        gametype = intent.getIntExtra("GAMETYPE");
        guessesleft  = intent.getStringExtra("GUESSESLEFT");
        word = intent.getStringExtra("WORD");

        // build string to put in actual highscore table
        StringBuilder highscorebuilder = new StringBuilder();

            if (gametype == 1)
                highscorebuilder.append("Type: Evil |");

            else
                highscorebuilder.append("Type: Good |");

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



    public void onBackPressed(){
            Intent intent = new Intent(this, StartScreenActivity.class);
            HighscoresActivity.this.startActivity(intent);

        }

}
