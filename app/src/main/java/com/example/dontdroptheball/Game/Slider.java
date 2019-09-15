package com.example.dontdroptheball.Game;

import android.graphics.Bitmap;
import android.view.View;

//Image width = 347
public class Slider extends CharacterSprite {
    private int width;

    public Slider(Bitmap bmp) {
        super(bmp);
        this.x = 1080/2 - 347 / 2;
        this.y = 1600;
        this.width = image.getWidth();
    }

    @Override
    public void update() {

    }

    public void slide(int x){
        this.x = x - 347 / 2;
    }

}