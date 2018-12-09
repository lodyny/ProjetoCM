package com.chubby.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import com.chubby.notsochubby.R;

public class GameView extends View {
    private Context context;
    private int canvasWidth, canvasHeight;
    private boolean touch = false;
    private boolean jumping = false;
    private int boost;

    private int distance;
    private Paint score = new Paint();

    private Bitmap dog[] = new Bitmap[2];
    private int dogX = 10, dogY, dogJump, minDogY, maxDogY;
    private MediaPlayer dogJumpSound;

    private Bitmap bird;
    private int birdX, birdY, birdSpeed = 20;

    private Bitmap rock;
    private int rockX, rockY, rockSpeed = 10;

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
        this.context = context;

        bottomBackground = BitmapFactory.decodeResource(getResources(), R.drawable.bgbot);
        topBackground = BitmapFactory.decodeResource(getResources(), R.drawable.bgtop);
        distanceIcon = BitmapFactory.decodeResource(getResources(), R.drawable.distance);

        dog[0] = BitmapFactory.decodeResource(getResources(), R.drawable.doggo);
        dog[1] = BitmapFactory.decodeResource(getResources(), R.drawable.doggo_jump);
        dogJumpSound = MediaPlayer.create(context, R.raw.dogjump);

        bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        rock = BitmapFactory.decodeResource(getResources(), R.drawable.rock);

        score.setColor(Color.BLACK);
        score.setTextSize(50);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setAntiAlias(true);
        score.setTextAlign(Paint.Align.RIGHT);
    }

    public void SetupBoost(int code){
        boost = code;
    }

    private void SetupNewGame(Canvas canvas){
        if(canvas.getHeight() > canvas.getWidth())
            return;

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        topBackground = Bitmap.createScaledBitmap(topBackground, canvasWidth, topBackground.getHeight(), true);
        bottomBackground = Bitmap.createScaledBitmap(bottomBackground, canvasWidth, bottomBackground.getHeight(), true);

        minDogY = dog[0].getHeight() - 50;
        dogY = maxDogY = canvasHeight - dog[0].getHeight() - 50;
        distance = 0;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (canvasHeight == 0 || canvasWidth == 0){
            SetupNewGame(canvas);
        }

        dogY = dogY + dogJump;
        if (dogY < minDogY)
            dogY = minDogY;
        if (dogY > maxDogY)
            dogY = maxDogY;
        dogJump = dogJump + 2;

        if (touch && jumping){
            touch = false;
        }
        else if (touch && !jumping){
            touch = false;
            if(boost == 2)
                dogJump = -100;
            else
                dogJump =  -50;
            dogJumpSound.start();
            jumping = true;
            animationHandler.sendEmptyMessageDelayed(0, 800);
        }

        birdX = birdX - birdSpeed;
        rockX = rockX - rockSpeed;

        if (hitObjectChecker(birdX, birdY)){
            ((GameActivity) context).EndGame(distance);
            distance = 0;
            birdX -= 500;
        }

        if (hitObjectChecker(rockX, rockY)){
            ((GameActivity) context).EndGame(distance);
            distance = 0;
            rockX -= 500;
        }

        if (birdX < -bird.getWidth()){
            birdX = canvasWidth + bird.getWidth()*2;
            birdX = random((canvasWidth + bird.getWidth()*2), (canvasWidth + bird.getWidth()*2) + 10000);
            //birdY = (int) Math.floor(Math.random() * (maxDogY - minDogY*2)) + minDogY;
            birdY = random(minDogY, maxDogY - minDogY*2);
            birdSpeed = 20 + (distance/180);
        }

        if (rockX < -rock.getWidth()){
            rockX = random((canvasWidth + rock.getWidth()*2), (canvasWidth + rock.getWidth()*2) + 1000);
            rockY = canvasHeight - rock.getHeight() -(rock.getHeight()/2);
            rockSpeed = 10 + (distance/280);
        }

        RenderObjects(canvas);
    }

    private int random(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
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

    // Render objects in line order
    private void RenderObjects(Canvas canvas){
        canvas.drawBitmap(topBackground, 0, 0, null);
        canvas.drawBitmap(bottomBackground, 0, (canvasHeight-bottomBackground.getHeight()), null);
        canvas.drawBitmap(dog[jumping ? 1 : 0], dogX, dogY, null);
        canvas.drawBitmap(bird, birdX, birdY, null);
        canvas.drawBitmap(rock, rockX, rockY, null);
        canvas.drawBitmap(distanceIcon, (canvasWidth-distanceIcon.getWidth()-10), 10, null);
        canvas.drawText(++distance + " m", (canvasWidth-distanceIcon.getWidth()+15), distanceIcon.getHeight()-5, score);
    }

    public int GetDistance(){
        return distance;
    }
}
