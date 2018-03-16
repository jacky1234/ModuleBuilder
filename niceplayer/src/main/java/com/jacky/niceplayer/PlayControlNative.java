package com.jacky.niceplayer;

import android.net.Uri;
import android.os.Build;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import java.util.List;

/**
 * 2018/3/14.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class PlayControlNative extends IPlayControl.Stub {
    private RemoteCallbackList<IRequestHandler> requestHandlerRemoteCallbackList = new RemoteCallbackList<>();
    public static RemoteCallbackList<IOnPlayerEventListener> playerEventListenerRemoteCallbackList = new RemoteCallbackList<>();
    private IRequestHandler done;

    @Override
    public boolean hasHandlers() throws RemoteException {
        if (Build.VERSION.SDK_INT >= 17) {
            return requestHandlerRemoteCallbackList.getRegisteredCallbackCount() != 0;
        }

        final int N = requestHandlerRemoteCallbackList.beginBroadcast();
        requestHandlerRemoteCallbackList.finishBroadcast();
        return N != 0;
    }

    @Override
    public boolean addRequestHandler(IRequestHandler handler) throws RemoteException {
        return requestHandlerRemoteCallbackList.register(handler);
    }

    @Override
    public boolean removeRequestHandler(IRequestHandler handler) throws RemoteException {
        return requestHandlerRemoteCallbackList.unregister(handler);
    }

    @Override
    public boolean registerPlayingEventListener(IOnPlayerEventListener listener) throws RemoteException {
        return playerEventListenerRemoteCallbackList.register(listener);
    }

    @Override
    public boolean unRegisterPlayingEventListener(IOnPlayerEventListener listener) throws RemoteException {
        return playerEventListenerRemoteCallbackList.unregister(listener);
    }

    @Override
    public boolean playMusic(List<Uri> list, int index) throws RemoteException {
        final int N = requestHandlerRemoteCallbackList.beginBroadcast();
        done = null;
        for (int i = 0; i < N; i++) {
            final IRequestHandler iRequestHandler = requestHandlerRemoteCallbackList.getBroadcastItem(i);
            if (iRequestHandler.canHandlerRequest() && iRequestHandler.start(list.get(index), 0)) {
                done = iRequestHandler;
                break;
            }
        }
        requestHandlerRemoteCallbackList.finishBroadcast();

        return done != null;
    }

    @Override
    public void pause() throws RemoteException {
//        if (done != null) {
//            done.
//        }
    }

    @Override
    public void resumeMusic() throws RemoteException {

    }

    @Override
    public void stop() throws RemoteException {

    }

    @Override
    public boolean pausePlayInMillis(long time) throws RemoteException {
        return false;
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
    public boolean playNext() throws RemoteException {


        return false;
    }

    @Override
    public boolean playPre() throws RemoteException {
        return false;

    }

    @Override
    public boolean hasPre() throws RemoteException {
        return false;
    }

    @Override
    public boolean hasNext() throws RemoteException {
        return false;
    }

    @Override
    public void setPlayMode(int mode) throws RemoteException {

    }

    @Override
    public void seekToPosition(int position) throws RemoteException {

    }

    @Override
    public void reset() throws RemoteException {

    }
}
