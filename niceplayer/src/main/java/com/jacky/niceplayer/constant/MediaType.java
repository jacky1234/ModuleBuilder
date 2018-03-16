package com.jacky.niceplayer.constant;

/**
 * 2018/3/14.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public enum MediaType {
    AUDIO(1), MOVIE(2);

    int index;

    MediaType(int index) {
        this.index = index;
    }

    public static MediaType convert(int index) {
        if (MOVIE.index == index) {
            return MOVIE;
        }

        return AUDIO;
    }
}
