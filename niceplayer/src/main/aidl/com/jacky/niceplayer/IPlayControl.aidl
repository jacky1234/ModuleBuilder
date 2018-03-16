// IPlayControl.aidl
package com.jacky.niceplayer;

import android.net.Uri;
import com.jacky.niceplayer.IRequestHandler;
import com.jacky.niceplayer.IOnPlayerEventListener;


// Declare any non-default types here with import statements
interface IPlayControl {

    //播放，并设置播放列表
     boolean playMusic(in List<Uri> list, int index );

    //暂停
    void pause();

    //继续
    void resumeMusic();

    //停止音乐
    void stop();

    //设置定时时间,time > 0
    boolean pausePlayInMillis(long time);

    //获取播放状态
    int getStatus();

    //获取当前进度
    long getProgress();

    //播放下一首
    boolean playNext();

    //播放上一首
    boolean playPre();

    //是否有上一首
    boolean hasPre();

    //是否有下一首
    boolean hasNext();

    //设置播放模式
    void setPlayMode(int mode);

    void seekToPosition(int position);

    //初始化
    void reset();

    boolean hasHandlers();

    boolean addRequestHandler(IRequestHandler handler);

    boolean removeRequestHandler(IRequestHandler handler);

    boolean registerPlayingEventListener(IOnPlayerEventListener listener);

    boolean unRegisterPlayingEventListener(IOnPlayerEventListener listener);


}
