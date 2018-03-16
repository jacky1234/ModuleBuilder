package com.jacky.airline;

import android.os.Handler;
import android.os.HandlerThread;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;
import static com.jacky.airline.Utils.DISPATCHER_THREAD_NAME;

/**
 * 2018/3/9.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class Dispatcher {

    static class DispatcherThread extends HandlerThread {
        DispatcherThread() {
            super(Utils.THREAD_PREFIX + DISPATCHER_THREAD_NAME, THREAD_PRIORITY_BACKGROUND);
        }
    }

    private static class DispatchHandler extends Handler{

    }
}
