package com.rameda.zupflix.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rameda.zupflix.R;

public class Splash extends AppCompatActivity {
    //Tempo do splash
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // Chama MainActivity
                Intent chamaMain = new Intent(Splash.this, MainActivity.class);
                startActivity(chamaMain);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
