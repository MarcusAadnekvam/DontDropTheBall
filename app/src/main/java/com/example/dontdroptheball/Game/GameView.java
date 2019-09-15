package com.example.dontdroptheball.Game;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;


import com.example.dontdroptheball.LoseActivity;
import com.example.dontdroptheball.R;

import static com.example.dontdroptheball.Game.MainThread.canvas;

/**
 * Created by rushd on 7/5/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Ball ball;
    private Slider slider;
    private int score = 0;
    Context context;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);

        this.context = context;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int eventAction = event.getAction();

        // you may need the x/y location
        int x = (int)event.getX();

        // put your code in here to handle the event
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                slider.slide(x);
        }

        // tell the View to redraw the Canvas
        invalidate();

        // tell the View that we handled the event
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        ball = new Ball(BitmapFactory.decodeResource(getResources(),R.drawable.redball));
        slider = new Slider(BitmapFactory.decodeResource(getResources(),R.drawable.sliderbar));


        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        ball.update();
        slider.update();

        if (ball.isBelowSlider()){
            if (ball.getX() < slider.getLeftX() || ball.getX() > slider.getRightX()){
                ((Activity) context).finish();
                //canvas = null;
                //Intent intent = new Intent(context, LoseActivity.class);
                //context.startActivity(intent);
            } else{
                ball.bounce();
                score ++;
            }
        }

    }

    @Override
    public void draw(Canvas canvas)
    {

        super.draw(canvas);
        if(canvas!=null) {
            displayScore();
            ball.draw(canvas);
            slider.draw(canvas);
        }
    }

    private void displayScore(){
        Paint paint = new Paint();
        canvas.drawPaint(paint);
        paint.setColor(Color.RED);
        paint.setTextSize(100);
        String text = "Score: " + Integer.toString(score);
        canvas.drawText(text, 1080/2-100, 200, paint);
    }

    public void changeBallColor(BallColor color){
        if (color == BallColor.RED){
            ball = new Ball(BitmapFactory.decodeResource(getResources(),R.drawable.redball));
        } else if (color == BallColor.BLUE){
            ball = new Ball(BitmapFactory.decodeResource(getResources(),R.drawable.blueball));
        } else if (color == BallColor.GREEN){
            ball = new Ball(BitmapFactory.decodeResource(getResources(),R.drawable.greenball));
        }

    }


}