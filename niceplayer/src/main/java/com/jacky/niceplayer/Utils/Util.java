package com.jacky.niceplayer.Utils;

import java.util.List;

/**
 * 2018/3/13.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class Util {
//    public static int SOURCE_PATH = 1;
//    public static int SOURCE_FILE = 1 << 1;
//    public static int SOURCE_URI = 1 << 2;
//    private int source_flag;

    public static void checkNull(Object o, String msg) {
        if (o == null) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void checkNull(Object[] os, String msg) {
        for (Object o : os) {
            if (o == null) {
                throw new IllegalArgumentException(msg);
            }
        }
    }

    public static void checkEmptyOrNull(List list,String msg) {
        if (list == null || (list != null && list.isEmpty())) {
            throw new IllegalArgumentException(msg);
        }
    }
}
