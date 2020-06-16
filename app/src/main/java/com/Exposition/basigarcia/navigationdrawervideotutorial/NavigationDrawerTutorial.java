package com.Exposition.basigarcia.navigationdrawervideotutorial;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Kalpa Wijesooriya on 12/2/2017.
 */

public class NavigationDrawerTutorial extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
