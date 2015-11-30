package com.example.renske.hangman_basic;

import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        // get list with highscores from preferences
        SharedPreferences savedhighscores = this.getSharedPreferences("highscores", MODE_PRIVATE);
        Set<String> set = savedhighscores.getStringSet("theSavedList", null);

        // if there are preferences, use those
        if (set != null) {
            List<String> savedlist = new ArrayList<String>(set);
            highscoreList = new ArrayList<String>();
            highscoreList.addAll(savedlist);
        }

        // else start with an empty list
        else {
            String[] initialtodos = new String[]{"No highscores yet."};
            highscoreList = new ArrayList<String>();
            highscoreList.addAll(Arrays.asList(initialtodos));
        }

        // create and set arrayadapter
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, highscoreList);
        highscoreListView.setAdapter(listAdapter);


    }

    // put user input in the array
    public void putHighscoreFromIntentInArray(View view) {

        String gametype, guessesleft, word;
        Bundle extras = getIntent().getExtras();
        gametype = extras.getString("GAMETYPE");
        guessesleft = extras.getString("GUESSESLEFT");
        word = extras.getString("WORD");

        // build string to put in actual highscore table
        StringBuilder highscorebuilder = new StringBuilder();

            if (gametype == "1")
                highscorebuilder.append("Type: Evil ");

            else
                highscorebuilder.append("Type: Good ");

        highscorebuilder.append("Guesses left:" + guessesleft + " ");
        highscorebuilder.append("Word" + word);

        String highscore = highscorebuilder.toString();

        listAdapter.add(highscore);

        // add the current state of highscore list to shared preferences
        SharedPreferences prefs = this.getSharedPreferences("highscores", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(highscoreList);
        edit.putStringSet("theSavedList", set);
        edit.commit();


    }
}
