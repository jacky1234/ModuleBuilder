package com.jacky.niceplayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;

import com.jacky.niceplayer.aidl.OnPlayerEventListener;
import com.jacky.niceplayer.aidl.RequestHandler;
import com.jacky.niceplayer.handler.MediaPlayerHandler;

import java.util.ArrayList;
import java.util.List;

import static com.jacky.niceplayer.Utils.Util.checkNull;

/**
 * 2018/3/13.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class NicePlayer {
    private static NicePlayer instance;
    public Context context;
    private ParamsCreator paramsCreator;
    private IPlayControl playControl;
    private PlayerEventNative playerEventListener;
    private Intent serviceIntent;

    private boolean disableMediaPlayer;
    private boolean enableNotification;

    private Cache cache = new Cache();

    class Cache {
        List<RequestHandler> addHandlers = new ArrayList<>();
        List<OnPlayerEventListener> playerEventListeners = new ArrayList<>();

        void clear() {
            addHandlers.clear();
            playerEventListeners.clear();
        }
    }

    public static NicePlayer get() {
        if (instance == null) {
            synchronized (NicePlayer.class) {
                if (instance == null) {
                    instance = new NicePlayer();
                }
            }
        }

        return instance;
    }

    public NicePlayer addPlayerListener(OnPlayerEventListener listener) {
        checkNull(listener, "Listener can not be null");
        if (playControl == null) {
            cache.playerEventListeners.add(listener);
            return this;
        }

        if (playerEventListener != null) {
            playerEventListener.addPlayerEventListener(listener);
        }
        return this;
    }

    public NicePlayer removePlayerListener(OnPlayerEventListener listener) {
        checkNull(listener, "Listener can not be null");
        if (playControl == null) {
            cache.playerEventListeners.remove(listener);
            return this;
        }

        if (playerEventListener != null) {
            playerEventListener.removePlayerEventListener(listener);
        }
        return this;
    }

    public void addRequestHandler(final RequestHandler handler) throws RemoteException {
        checkNull(handler, "RequestHandler can not be null");

        if (playControl == null) {
            cache.addHandlers.add(handler);
            return;
        }

        playControl.addRequestHandler(new IRequestHandler.Stub() {
            @Override
            public boolean canHandlerRequest() throws RemoteException {
                return handler.canHandlerRequest();
            }

            @Override
            public boolean start(Uri uri, int position) throws RemoteException {
                return handler.start(uri, position);
            }

            @Override
            public boolean pause() throws RemoteException {
                return handler.pause();
            }

            @Override
            public void resume() throws RemoteException {
                handler.resume();
            }

            @Override
            public void stop() throws RemoteException {
                handler.stop();
            }

            @Override
            public int getStatus() throws RemoteException {
                return handler.getStatus();
            }

            @Override
            public long getProgress() throws RemoteException {
                return handler.getProgress();
            }

            @Override
            public void reset() throws RemoteException {
                handler.reset();
            }
        });
    }

    public NicePlayer init(Context context) {
        checkNull(context, "Context can not be null");

        this.context = context.getApplicationContext();
        serviceIntent = new Intent(context, MusicService.class);
        context.bindService(serviceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        return this;
    }

    public NicePlayer setDisableMediaPlayer(boolean disableMediaPlayer) {
        this.disableMediaPlayer = disableMediaPlayer;
        return this;
    }

    public NicePlayer enableNotification(boolean notification) {
        this.enableNotification = notification;
        return this;
    }

    private NicePlayer() {
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            playControl = IPlayControl.Stub.asInterface(iBinder);
            try {
                if (!disableMediaPlayer) {
                    playControl.addRequestHandler(new MediaPlayerHandler());
                }

                playerEventListener = new PlayerEventNative(cache.playerEventListeners);
                playControl.registerPlayingEventListener(playerEventListener);
                if (!cache.addHandlers.isEmpty()) {
                    for (RequestHandler handler : cache.addHandlers) {
                        addRequestHandler(handler);
                    }
                }

                cache.clear();

                if (!playControl.hasHandlers()) {
                    throw new IllegalArgumentException("None of handles to perform");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                playControl.unRegisterPlayingEventListener(playerEventListener);
            } catch (Exception e) {
                e.printStackTrace();
            }
            clearOnDisConnect();
            playControl = null;
            context.bindService(serviceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }
    };

    private void clearOnDisConnect() {

    }

    public void start(ParamsCreator paramsCreator) throws RemoteException {
        checkNull(paramsCreator, "ParamsCreator can not be null");

        if (playControl != null) {
            playControl.playMusic(paramsCreator.uris, paramsCreator.index);
        }
    }

}
