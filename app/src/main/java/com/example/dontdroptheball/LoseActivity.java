package com.example.dontdroptheball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dontdroptheball.Game.GameActivity;

public class LoseActivity extends AppCompatActivity {
    private TextView scoreText;
    private TextView highScoreText;
    private Button playAgainButton;
    private Button mainMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        //Buttons
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        mainMenuButton = (Button) findViewById(R.id.mainMenuButton);

        playAgainButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
                launchGame();
            }
        });
        mainMenuButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        //Set score and highscore text
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int score = preferences.getInt("score", 0);
        int highscore = preferences.getInt("highscore", 0);
        scoreText = (TextView) findViewById(R.id.finalScore);
        scoreText.setText("Final Score: " + score);
        highScoreText = (TextView) findViewById(R.id.highScore);
        highScoreText.setText("Highscore: " + highscore);

    }

    private void launchGame(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
