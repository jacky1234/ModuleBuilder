package com.jacky.niceplayer.function;

import android.os.RemoteException;

/**
 * 2018/3/16.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public interface Callback<T> {
    void call(T t) throws RemoteException;
}
