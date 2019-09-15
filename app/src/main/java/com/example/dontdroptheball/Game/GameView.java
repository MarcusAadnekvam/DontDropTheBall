package com.example.dontdroptheball.Game;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;


import com.example.dontdroptheball.R;

/**
 * Created by rushd on 7/5/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private CharacterSprite ball;
    private Slider slider;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);

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

    }

    @Override
    public void draw(Canvas canvas)
    {

        super.draw(canvas);
        if(canvas!=null) {
            ball.draw(canvas);
            slider.draw(canvas);
        }
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