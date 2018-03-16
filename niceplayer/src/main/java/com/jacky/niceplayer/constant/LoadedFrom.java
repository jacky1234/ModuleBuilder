package com.jacky.niceplayer.constant;

/**
 * 2018/3/14.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public enum LoadedFrom {
    MEMORY(0),
    DISK(1),
    NETWORK(1);

    int index;

    LoadedFrom(int index) {
        this.index = index;
    }

    public static LoadedFrom convert(int index){
        if (DISK.index == index){
            return DISK;
        }

        if (NETWORK.index == index){
            return NETWORK;
        }

        return MEMORY;
    }
}
