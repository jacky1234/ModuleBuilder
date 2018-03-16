// IOnPlayerEventListener.aidl
package com.jacky.niceplayer;

import android.net.Uri;

// Declare any non-default types here with import statements

interface IOnPlayerEventListener {
    /**
     * 切换歌曲,list 中的index
     */
    void onMusicSwitch(in Uri uri,int index);

    void onStart();

    /**
     * 暂停播放
     */
    void onPause();

    /**
    * percent 0-100
    */
    void onBuffering(int percent,boolean finished);

    /**
     * 播放完成
     */
    void onCompletion();


    void onError(int errorCode, String msg);

}
