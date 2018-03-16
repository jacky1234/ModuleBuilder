package com.jacky.niceplayer.aidl;

/**
 * 2018/3/15.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public interface OnPlayerEventListener {
    /**
     * 切换歌曲,list 中的index
     */
    void onMusicSwitch(android.net.Uri uri, int index);

    void onStart();

    /**
     * 暂停播放
     */
    void onPause();

    void onBuffering(float percent, boolean finished);

    /**
     * 播放完成
     */
    void onCompletion();

    void onError(int errorCode, java.lang.String msg);
}
