package com.jacky.niceplayer.handler;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.jacky.niceplayer.IOnPlayerEventListener;
import com.jacky.niceplayer.IRequestHandler;
import com.jacky.niceplayer.PlayControlNative;
import com.jacky.niceplayer.function.Callback;

import java.io.IOException;

/**
 * 2018/3/14.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class MediaPlayerHandler extends IRequestHandler.Stub implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener
        , MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnInfoListener {

    private MediaPlayer mediaPlayer;

    private RemoteCallbackList<IOnPlayerEventListener> getRemoteCallbacks() {
        return PlayControlNative.playerEventListenerRemoteCallbackList;
    }

    @Override
    public boolean canHandlerRequest() {
        return true;
    }

    @Override
    public boolean start(Uri uri, int position) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.reset();

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(uri.toString());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            mediaPlayer.release();
            mediaPlayer = null;
            return false;
        }

        return true;
    }

    @Override
    public boolean pause() throws RemoteException {
        return false;
    }

    @Override
    public void resume() throws RemoteException {

    }

    @Override
    public void stop() throws RemoteException {

    }

    @Override
    public int getStatus() throws RemoteException {
        return 0;
    }

    @Override
    public long getProgress() throws RemoteException {
        return 0;
    }

    @Override
    public void reset() throws RemoteException {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        notifyStatusChanged(new Callback<IOnPlayerEventListener>() {
            @Override
            public void call(IOnPlayerEventListener iOnPlayerEventListener) throws RemoteException {
                iOnPlayerEventListener.onCompletion();
            }
        });;
    }

    @Override
    public boolean onError(MediaPlayer mp, final int what, final int extra) {
        return notifyStatusChanged(new Callback<IOnPlayerEventListener>() {
            @Override
            public void call(IOnPlayerEventListener iOnPlayerEventListener) throws RemoteException {
                iOnPlayerEventListener.onError(what, "error");
            }
        });
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        final RemoteCallbackList<IOnPlayerEventListener> remoteCallbacks = getRemoteCallbacks();
        final int N = remoteCallbacks.beginBroadcast();
        for (int i = 0; i < N; i++) {
            final IOnPlayerEventListener playerEventListener = remoteCallbacks.getBroadcastItem(i);
            try {
                playerEventListener.onBuffering(percent, percent == 100);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        remoteCallbacks.finishBroadcast();
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }


    private boolean notifyStatusChanged(Callback<IOnPlayerEventListener> callback) {
        if (callback != null) {
            final RemoteCallbackList<IOnPlayerEventListener> remoteCallbacks = getRemoteCallbacks();
            final int N = remoteCallbacks.beginBroadcast();
            for (int i = 0; i < N; i++) {
                try {
                    callback.call(remoteCallbacks.getBroadcastItem(i));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            remoteCallbacks.finishBroadcast();
        }

        return true;
    }

}
