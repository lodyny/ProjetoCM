package com.chubby.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.chubby.notsochubby.R;


public class GameShop extends AppCompatActivity {

    private ImageButton returnButton;

    private ImageButton lifeButton;
    private ImageButton potionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_shop);
        setUpButtons();
    }

    private void setUpButtons(){
        returnButton = findViewById(R.id.exitShopButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });

        lifeButton = findViewById(R.id.lifeButton);
        lifeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });

        potionButton = findViewById(R.id.potionButton);
        potionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(2);
                finish();
            }
        });
    }
}
