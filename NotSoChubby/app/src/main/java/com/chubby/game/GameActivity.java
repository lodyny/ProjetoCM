package com.chubby.game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chubby.notsochubby.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    // Views
    private GameView gameView;
    private int gameBoost;
    private final static long FPS = 15;
    private Handler handler = new Handler();

    // Elements
    private Button startGameButton;
    private ImageButton returnButton;
    private ImageButton shopButton;
    private TextView textFinalScore;
    private TextView textBoost;

    // Shop Codes
    private static final int BUY_ITEMS_SHOP = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game);

        SetupButtons();
        textFinalScore.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == BUY_ITEMS_SHOP) {
            switch(resultCode){
                case 1: gameBoost = 1; textBoost.setText("Current bonus: Extra Life"); break;
                case 2: gameBoost = 2; textBoost.setText("Current bonus: Jumpy Dog"); break;
                default: gameBoost = 0; textBoost.setText("Nenhum bónus ativo");
            }
        }
    }


    private void StartGame(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        gameView = new GameView(this);
        gameView.SetupBoost(gameBoost);
        setContentView(gameView);

        // GAME UI
        DisplayUI();
        // FIM TESTES

        MediaPlayer dogBark = MediaPlayer.create(this, R.raw.dogbark);
        dogBark.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, FPS);
    }

    private void DisplayUI(){
        LayoutInflater lf = getLayoutInflater();
        View headerView = View.inflate(this, R.layout.activity_game_ui, null);
        final ImageButton btn = (ImageButton) headerView.findViewById(R.id.imgButtonExitGame);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndGame(gameView.GetDistance());
            }
        });

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addContentView(headerView,params);
    }

    private void ExitGame(){
        finish();
    }

    public void EndGame(int finishedMeters){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game);
        SetupButtons();
        textFinalScore.setText("Distancia Percorrida: " + finishedMeters);
        textFinalScore.setVisibility(View.VISIBLE);
        gameBoost = 0;
    }

    private void SetupButtons(){
        startGameButton = findViewById(R.id.buttonStartGame);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartGame();
            }
        });

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        animation.setStartOffset(20);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        startGameButton.setAnimation(animation);

        returnButton = findViewById(R.id.exitShopButton);
        returnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ExitGame();
            }
        });

        shopButton = findViewById(R.id.shopButton);
        shopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent shop = new Intent(GameActivity.this, GameShop.class);
                startActivityForResult(shop, BUY_ITEMS_SHOP);
            }
        });

        textFinalScore = findViewById(R.id.textFinalScore);
        textBoost = findViewById(R.id.textBoost);
    }

}
