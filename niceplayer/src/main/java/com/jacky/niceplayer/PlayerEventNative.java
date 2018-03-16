package com.jacky.niceplayer;

import android.net.Uri;
import android.os.RemoteException;

import com.jacky.niceplayer.aidl.OnPlayerEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/3/15.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class PlayerEventNative extends IOnPlayerEventListener.Stub {
    private List<OnPlayerEventListener> playerEventListeners;

    public boolean addPlayerEventListener(OnPlayerEventListener listener){
        return playerEventListeners.add(listener);
    }

    public boolean removePlayerEventListener(OnPlayerEventListener listener){
        return playerEventListeners.remove(listener);
    }

    public PlayerEventNative(List<OnPlayerEventListener> playerEventListeners) {
        assert playerEventListeners != null;
        this.playerEventListeners = new ArrayList<>(playerEventListeners);
    }

    @Override
    public void onMusicSwitch(Uri uri, int index) throws RemoteException {
        if (!playerEventListeners.isEmpty()) {
            for (OnPlayerEventListener listener : playerEventListeners) {
                listener.onMusicSwitch(uri, index);
            }
        }
    }

    @Override
    public void onStart() throws RemoteException {
        if (!playerEventListeners.isEmpty()) {
            for (OnPlayerEventListener listener : playerEventListeners) {
                listener.onStart();
            }
        }
    }

    @Override
    public void onPause() throws RemoteException {
        if (!playerEventListeners.isEmpty()) {
            for (OnPlayerEventListener listener : playerEventListeners) {
                listener.onPause();
            }
        }
    }

    @Override
    public void onBuffering(int percent, boolean finished) throws RemoteException {
        if (!playerEventListeners.isEmpty()) {
            for (OnPlayerEventListener listener : playerEventListeners) {
                listener.onBuffering(percent, finished);
            }
        }
    }

    @Override
    public void onCompletion() throws RemoteException {
        if (!playerEventListeners.isEmpty()) {
            for (OnPlayerEventListener listener : playerEventListeners) {
                listener.onCompletion();
            }
        }
    }

    @Override
    public void onError(int errorCode, String msg) throws RemoteException {
        if (!playerEventListeners.isEmpty()) {
            for (OnPlayerEventListener listener : playerEventListeners) {
                listener.onError(errorCode, msg);
            }
        }
    }
}
