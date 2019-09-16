package com.example.dontdroptheball.Game;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
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
            if (ball.getX() < slider.getLeftX()+25 || ball.getX() > slider.getRightX()+25){
                setScore();
                ((Activity) context).finish();
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
        //Score
        Paint scorePaint = new Paint();
        canvas.drawPaint(scorePaint);
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(100);
        String text = "Score: " + score;
        canvas.drawText(text, 1080/2-150, 200, scorePaint);

        //Difficulty

        Paint diffPaint = new Paint();
        canvas.drawPaint(diffPaint);
        String diff = "";
        if (score < 10){
            diffPaint.setColor(Color.GREEN);
            diffPaint.setTextSize(75);
            diff = "ez mode";
        } else if (score < 20){
            diffPaint.setColor(Color.YELLOW);
            diffPaint.setTextSize(75);
            diff = "getting harder";
        } else {
            diffPaint.setColor(Color.WHITE);
            diffPaint.setTextSize(75);
            diff = "Impossible mode";
        }

        canvas.drawText(diff, 1080/2 - 150, 350, diffPaint);
        canvas.drawText(text, 1080/2 - 150, 200, scorePaint);

    }

    public void setScore(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("score", score);

        if (prefs.contains("highscore")){
            if (score > prefs.getInt("highscore", 0)) {
                editor.putInt("highscore", score);
            }
        } else {
            editor.putInt("highscore", score);
        }


        editor.commit();
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