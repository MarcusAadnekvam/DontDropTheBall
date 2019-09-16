package com.example.dontdroptheball.Game;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.dontdroptheball.LoseActivity;

public class GameActivity extends Activity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = this.getSharedPreferences("scorePrefs", MODE_PRIVATE);
        this.sharedPreferences = sharedPreferences;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));

    }

    @Override
    protected void onStop(){
        super.onStop();
        Intent intent = new Intent(this, LoseActivity.class);
        this.startActivity(intent);
    }


}
