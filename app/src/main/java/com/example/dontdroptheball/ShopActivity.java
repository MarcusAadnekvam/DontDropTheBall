package com.example.dontdroptheball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dontdroptheball.Game.BallColor;

public class ShopActivity extends AppCompatActivity {
    private ImageButton redBallButton;
    private ImageButton blueBallButton;
    private ImageButton greenBallButton;
    private TextView currentBallText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = prefs.edit();
        String ballColor = prefs.getString("BallColor", "red");
        currentBallText = (TextView) findViewById(R.id.currentBallText);
        currentBallText.setText("Current ball: " + ballColor);



        redBallButton = (ImageButton) findViewById(R.id.redBallButton);
        redBallButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //setBallColor(BallColor.RED);
                editor.putString("BallColor", "red");
                editor.apply();
                currentBallText.setText("Current ball: Red");
            }
        });

        blueBallButton = (ImageButton) findViewById(R.id.blueBallButton);
        blueBallButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //setBallColor(BallColor.BLUE);
                editor.putString("BallColor", "blue");
                editor.apply();
                currentBallText.setText("Current ball: Blue");
            }
        });

        greenBallButton = (ImageButton) findViewById(R.id.greenBallButton);
        greenBallButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //setBallColor(BallColor.GREEN);
                editor.putString("BallColor", "green");
                editor.apply();
                currentBallText.setText("Current ball: Green");
            }
        });
    }

    private void setBallColor(BallColor color){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        if(color == BallColor.RED){
            editor.putString("BallColor", "red");
        }
        else if(color == BallColor.BLUE){
            editor.putString("BallColor", "blue");
        }
        else if(color == BallColor.GREEN){
            editor.putString("BallColor", "green");
        } else {
            throw new IllegalArgumentException();
        }
    }
}
