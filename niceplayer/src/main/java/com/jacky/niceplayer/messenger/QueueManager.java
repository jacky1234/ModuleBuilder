package com.jacky.niceplayer.messenger;

import android.net.Uri;
import android.support.v4.media.session.MediaSessionCompat;

import com.jacky.niceplayer.constant.PlayMode;

import java.util.List;

/**
 * 2018/3/15.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class QueueManager {
    private int mCurrentIndex;
    private List<Uri> mPlayingQueue;
    private PlayMode mPlayMode;

    public void setCurrentQueue(List<Uri> uris, int currentIndex) {
        int index = 0;
        if (currentIndex != -1) {
            index = currentIndex;
        }
        mCurrentIndex = Math.max(index, 0);
        mPlayingQueue.clear();

        mPlayingQueue.addAll(uris);

        //通知播放列表更新了
//        List<MediaSessionCompat.QueueItem> queueItems = QueueHelper.getQueueItems(mPlayingQueue);
//        if (mListener != null) {
//            mListener.onQueueUpdated(queueItems, mPlayingQueue);
//        }
    }

    public void setCurrentQueue(List<Uri> uris) {
        setCurrentQueue(uris, -1);
    }

    public interface MetadataUpdateListener {
        void onMetadataChanged(Uri uri);

        void onMetadataRetrieveError();

        void onCurrentQueueIndexUpdated(int queueIndex, boolean isJustPlay, boolean isSwitchMusic);

        void onQueueUpdated(List<MediaSessionCompat.QueueItem> newQueue, List<Uri> uris);
    }

}
