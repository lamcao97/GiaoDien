package com.example.testsgwd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        getSupportActionBar().hide();
        Thread time = new Thread(){
            public void run()
            {
                try {
                    sleep(1000);
                } catch (Exception e) {

                }
                finally
                {
                    Intent activityNew = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(activityNew);
                }
            }
        };
        time.start();
    }

    protected void onPause(){
        super.onPause();
        finish();
    }
}
