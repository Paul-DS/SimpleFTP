package com.paulds.simpleftp.presentation.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paulds.simpleftp.R;

/**
 * Activity which displays the splash screen.
 *
 * @author Paul-DS
 */
public class SplashActivity extends AppCompatActivity {
    /**
     * The duration of the splash screen.
     */
    private static int SPLASH_TIME_OUT = 3000;

    /**
     * Method called at activity creation.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, ListFileActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
