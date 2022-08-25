package com.example.applicationmacropay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmacropay.views.DashboardActivity;
import com.example.applicationmacropay.views.LoginActivity;



public class SplashActivity extends AppCompatActivity {
    public Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        setContentView(R.layout.activity_splash);
        Thread initThread = new InitThread();
        initThread.start();
    }
    class InitThread extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            handler.post(new Runnable() {

                public void run() {
                    Intent mainIntent = null;

                    if(ApplicationContextProvider.isLogin()){
                        mainIntent = new Intent(SplashActivity.this, DashboardActivity.class);
                    }else {
                        mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    }
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            });
        }
    }
}