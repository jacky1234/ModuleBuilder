package com.jacky.airline;

import android.app.Application;

import com.jacky.niceplayer.NicePlayer;

/**
 * 2018/3/15.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NicePlayer.get().init(this);
    }
}
