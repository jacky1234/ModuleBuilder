package com.jacky.niceplayer.constant;

/**
 * Created by xian on 2018/1/28.
 */

public enum PlayMode {
    PLAY_IN_SINGLE_LOOP(1), PLAY_IN_RANDOM(2), PLAY_IN_LIST_LOOP(3);

    int index;

    PlayMode(int index) {
        this.index = index;
    }

    public static PlayMode convert(int index) {
        if (PLAY_IN_LIST_LOOP.index == index) {
            return PLAY_IN_LIST_LOOP;
        }

        if (PLAY_IN_RANDOM.index == index) {
            return PLAY_IN_RANDOM;
        }

        return PLAY_IN_LIST_LOOP;
    }
}
