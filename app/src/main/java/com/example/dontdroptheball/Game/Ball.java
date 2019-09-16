package com.example.dontdroptheball.Game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.dontdroptheball.R;

public class Ball extends CharacterSprite {
    private int xVelocity = 18;
    private int yVelocity = 18;
    private boolean belowSlider = false;

    public Ball(Bitmap bmp) {
        super(bmp);
        this.x = 100;
        this.y = 100;

    }

    public int getX(){
        return this.x;
    }

    public void bounce(){
        //this.yVelocity *= -1;
        this.yVelocity *= -1.1;
        this.xVelocity *= 1.1;
    }

    public boolean isBelowSlider(){
        return belowSlider;
    }


    @Override
    public void update() {
        belowSlider = false;

        x += xVelocity;
        y += yVelocity;
        if ((x > screenWidth - image.getWidth()) || (x < 0)) {
            xVelocity = xVelocity * -1;
        }
        if (y < 0){
            yVelocity = yVelocity * -1;
        }

        if (y > 1574){
            belowSlider = true;
        }

    }
}
