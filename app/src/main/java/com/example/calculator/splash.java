package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {
    ImageView splash_img;
    TextView txt_cal;
    Animation LR, RL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to get full screen animation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        splash_img = findViewById(R.id.imageview);
        txt_cal = findViewById(R.id.textView);

        //loads the animation file from "anim" directory
        LR = AnimationUtils.loadAnimation(this, R.anim.leftright);
        RL = AnimationUtils.loadAnimation(this, R.anim.rightleft);

        splash_img.setAnimation(LR);
        txt_cal.setAnimation(RL);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },2200);
    }
}