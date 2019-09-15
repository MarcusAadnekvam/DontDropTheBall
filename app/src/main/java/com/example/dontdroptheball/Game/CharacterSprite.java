package com.example.dontdroptheball.Game;



import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by rushd on 7/5/2017.
 */

public class CharacterSprite {


    protected Bitmap image;
    protected int x, y;
    protected int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    protected int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public CharacterSprite(Bitmap bmp) {
        image = bmp;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);


    }

    public void update() {}

    public int getScreenWidth(){
        return screenWidth;
    }
    public int getScreenHeight(){
        return screenHeight;
    }



}
