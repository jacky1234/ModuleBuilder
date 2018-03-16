package com.jacky.niceplayer.aidl;

import android.net.Uri;

/**
 * 2018/3/16.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public interface RequestHandler {
    boolean canHandlerRequest();

    boolean start(Uri uri, int position);

    boolean pause();

    void resume();

    void stop();

    int getStatus();

    long getProgress();

    void reset();
}
