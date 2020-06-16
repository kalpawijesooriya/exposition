package com.Exposition.basigarcia.navigationdrawervideotutorial;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import com.Exposition.basigarcia.navigationdrawervideotutorial.R;

public class SplashScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor( R.color.black));
        }

        setContentView(R.layout.activity_splash_screen);
        Thread mythred =new Thread() {

            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };
        mythred.start();

    }
}

