package com.example.dontdroptheball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.example.dontdroptheball.Game.GameActivity;

public class LoseActivity extends AppCompatActivity {
    private TextView scoreText;
    private TextView highScoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        //Set score and highscore text
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int score = preferences.getInt("score", 0);
        int highscore = preferences.getInt("highscore", 0);
        scoreText = (TextView) findViewById(R.id.finalScore);
        scoreText.setText("Final Score: " + score);
        highScoreText = (TextView) findViewById(R.id.highScore);
        scoreText.setText("Highscore: " + highscore);

    }
}
