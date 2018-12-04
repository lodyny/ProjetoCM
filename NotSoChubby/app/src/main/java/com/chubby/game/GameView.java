package com.chubby.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import com.chubby.notsochubby.R;

public class GameView extends View {
    private int canvasWidth, canvasHeight;
    private boolean touch = false;
    private boolean jumping = false;

    private int distance;
    private Paint score = new Paint();

    private Bitmap dog[] = new Bitmap[2];
    private int dogX = 10;
    private int dogY;
    private int dogJump;
    private int birdX, birdY, birdSpeed = 30;
    private Bitmap bird;

    private Bitmap bottomBackground;
    private Bitmap topBackground;
    private Bitmap distanceIcon;

    private Handler animationHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            jumping = false;
        }
    };

    public GameView(Context context) {
        super(context);

        bottomBackground = BitmapFactory.decodeResource(getResources(), R.drawable.bgbot);
        topBackground = BitmapFactory.decodeResource(getResources(), R.drawable.bgtop);
        distanceIcon = BitmapFactory.decodeResource(getResources(), R.drawable.distance);

        dog[0] = BitmapFactory.decodeResource(getResources(), R.drawable.doggo);
        dog[1] = BitmapFactory.decodeResource(getResources(), R.drawable.doggo_jump);

        bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);

        score.setColor(Color.BLACK);
        score.setTextSize(70);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setAntiAlias(true);

        dogY = 550;
        distance = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        canvas.drawBitmap(topBackground, 0, 0, null);
        canvas.drawBitmap(bottomBackground, 0, (canvasHeight-bottomBackground.getHeight()), null);

        int minDogY = dog[0].getHeight() - 50;
        int maxDogY = canvasHeight - dog[0].getHeight() - 50;

        dogY = dogY + dogJump;
        if (dogY < minDogY)
            dogY = minDogY;
        if (dogY > maxDogY)
            dogY = maxDogY;

        dogJump = dogJump + 2;

        if (touch && jumping){
            DogJumping(canvas);
            touch = false;
        }
        else if (touch && !jumping){
            DogJumping(canvas);
            touch = false;
            dogJump =  -50;
            jumping = true;
            animationHandler.sendEmptyMessageDelayed(0, 1500);
        }
        else if (!touch && jumping){
            DogJumping(canvas);
        }
        else if (!touch && !jumping){
            DogIdle(canvas);
        }

        birdX = birdX - birdSpeed;

        if (hitObjectChecker(birdX, birdY)){
            distance = 0;
            birdX -= 500;
        }

        if (birdX < 0){
            birdX = canvasWidth + bird.getWidth();
            birdY = (int) Math.floor(Math.random() * (maxDogY - minDogY)) + minDogY;
        }
        Bird(canvas);

        canvas.drawBitmap(distanceIcon, canvasWidth, 0, null);
        distance++;
        canvas.drawText("Bones: " + distance, canvasWidth - distanceIcon.getWidth(), 0, score);
    }

    private void DogIdle(Canvas canvas){
        canvas.drawBitmap(dog[0], dogX, dogY, null);
    }

    private void DogJumping(Canvas canvas){
        canvas.drawBitmap(dog[1], dogX, dogY, null);
    }

    private void Bird(Canvas canvas){
        canvas.drawBitmap(bird, birdX, birdY, null);
    }

    public boolean hitObjectChecker(int x, int y){
        if (dogX < x && x < (dogX + dog[0].getWidth()) && dogY < y && y < (dogY + dog[0].getHeight())){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if(!touch)
                touch = true;
        }
        return true;
    }
}
