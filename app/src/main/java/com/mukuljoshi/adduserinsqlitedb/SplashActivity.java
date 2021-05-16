package com.mukuljoshi.adduserinsqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    private  static  final  int  Time_SPLASH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.teal_700));


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),ViewEmployee.class);
                startActivity(intent);
                finish();

            }
        },Time_SPLASH);

    }
}