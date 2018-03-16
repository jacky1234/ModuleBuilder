// IRequestHandler.aidl
package com.jacky.niceplayer;

// Declare any non-default types here with import statements

interface IRequestHandler {
    boolean canHandlerRequest();

    boolean start(in Uri uri,int position);

    boolean pause();

    void resume();

    void stop();

    int getStatus();

    long getProgress();

    void reset();
}
