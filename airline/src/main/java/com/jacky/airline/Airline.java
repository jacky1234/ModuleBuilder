package com.jacky.airline;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 2018/3/9.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class Airline {
    Context context;


    public Airline(Context context) {
        this.context = context;
    }

    public void connect(){

    }

    public void disConnect(){

    }

    public static class Builder {
        Context context;

        public Builder(@NonNull Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        public Airline build() {
            return new Airline(this.context);
        }
    }
}
