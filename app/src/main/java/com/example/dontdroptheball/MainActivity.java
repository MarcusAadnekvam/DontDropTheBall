package com.example.dontdroptheball;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dontdroptheball.Game.GameActivity;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Button button;
    public static final String MY_PREFS = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                launchGame();
            }
        });

    }



    private void launchGame(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
