package com.example.dontdroptheball.Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.dontdroptheball.R;

public class Ball extends CharacterSprite {
    private int xVelocity = 10;
    private int yVelocity = 5;

    public Ball(Bitmap bmp) {
        super(bmp);
        this.x = 100;
        this.y = 100;

    }


    @Override
    public void update() {


        x += xVelocity;
        y += yVelocity;
        if ((x > screenWidth - image.getWidth()) || (x < 0)) {
            xVelocity = xVelocity * -1;
        }
        if ((y > screenHeight - image.getHeight()) || (y < 0)) {
            yVelocity = yVelocity * -1;
        }

    }
}
