package com.jacky.airline;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jacky.airline.utils.AndroidUtils;
import com.jacky.niceplayer.NicePlayer;
import com.jacky.niceplayer.ParamsCreator;
import com.jacky.niceplayer.aidl.OnPlayerEventListener;

public class MainActivity extends AppCompatActivity implements Runnable {
    final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NicePlayer.get().addPlayerListener(playerEventListener);
        AndroidUtils.requestPermission(this, "android.permission.INTERNET", 1);
        MAIN_HANDLER.postDelayed(this, 1000);
    }

    @Override
    public void run() {
        try {
            NicePlayer.get()
                    .start(new ParamsCreator
                            .Builder()
                            .load("http://filebag-1252817547.cosgz.myqcloud.com/201710/4bc3fbdd-9732-469d-afba-0a7ea651be61.mp3")
                            .build()
                    );
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MAIN_HANDLER.removeCallbacks(this);
        NicePlayer.get().removePlayerListener(playerEventListener);
    }

    private OnPlayerEventListener playerEventListener = new OnPlayerEventListener() {
        @Override
        public void onMusicSwitch(Uri uri, int index) {
            Log.d("MainActivity", "switch->uri:" + uri.toString() + ",index:" + index);
        }

        @Override
        public void onStart() {
            Log.d("MainActivity", "start");
        }

        @Override
        public void onPause() {
            Log.d("MainActivity", "pause");
        }

        @Override
        public void onBuffering(float percent, boolean finished) {
            Log.d("MainActivity", "buffering:" + percent + ",finished:" + finished);
        }

        @Override
        public void onCompletion() {
            Log.d("MainActivity", "complete");
        }

        @Override
        public void onError(int errorCode, String msg) {
            Log.d("MainActivity", "error->errorCode:" + errorCode + ",msg:" + msg);
        }
    };

}
