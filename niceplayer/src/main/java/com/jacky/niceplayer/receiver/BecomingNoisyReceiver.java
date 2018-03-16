package com.jacky.niceplayer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;

import com.jacky.niceplayer.Utils.Util;

import java.lang.ref.WeakReference;

/**
 * 2018/3/14.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class BecomingNoisyReceiver extends BroadcastReceiver {
    private WeakReference<OnNoisyCallBack> weakReference;

    public interface OnNoisyCallBack {
        void onNoisy();
    }

    public BecomingNoisyReceiver(OnNoisyCallBack callBack) {
        this.weakReference = new WeakReference<OnNoisyCallBack>(callBack);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (weakReference != null && weakReference.get() != null) {
            weakReference.get().onNoisy();
        }
    }

    public void register(Context context) {
        Util.checkNull(context, "Context can not be null");

        IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        context.getApplicationContext().registerReceiver(this, intentFilter);
    }

    public void unRegister(Context context){
        Util.checkNull(context, "Context can not be null");

        context.getApplicationContext().unregisterReceiver(this);
    }
}
