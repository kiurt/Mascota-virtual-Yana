package com.mascota.yanamascotavirtual;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView petImageView;
    private TextView statusTextView;
    private Button feedButton;
    private Button playButton;
    private int hungerLevel = 5;
    private int happinessLevel = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        petImageView = findViewById(R.id.petImageView);
        statusTextView = findViewById(R.id.statusTextView);
        feedButton = findViewById(R.id.feedButton);
        playButton = findViewById(R.id.playButton);

        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedPet();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playWithPet();
            }
        });

        updateStatus();
    }

    private void feedPet() {
        hungerLevel = Math.min(hungerLevel + 1, 10);
        updateStatus();
    }

    private void playWithPet() {
        happinessLevel = Math.min(happinessLevel + 1, 10);
        updateStatus();
    }

    private void updateStatus() {
        String status = "Hunger: " + hungerLevel + " | Happiness: " + happinessLevel;
        statusTextView.setText(status);
        if (hungerLevel < 3 || happinessLevel < 3) {
            statusTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            statusTextView.setTextColor(getResources().getColor(android.R.color.black));
        }
    }
}