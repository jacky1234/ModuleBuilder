package com.jacky.niceplayer;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.jacky.niceplayer.constant.MediaType;
import com.jacky.niceplayer.constant.PlayMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.jacky.niceplayer.Utils.Util.checkEmptyOrNull;
import static com.jacky.niceplayer.Utils.Util.checkNull;

/**
 * 2018/3/14.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

public class ParamsCreator {
    public MediaType type;
    public List<Uri> uris = new ArrayList<>();
    public PlayMode playMode = PlayMode.PLAY_IN_LIST_LOOP;
    public int index;

    private ParamsCreator(Builder builder) {
        this.type = builder.type;
        this.uris = builder.uris;
        this.playMode = builder.playMode;
    }

    public static class Builder {
        private MediaType type;
        private List<Uri> uris = new ArrayList<>();
        private PlayMode playMode = PlayMode.PLAY_IN_LIST_LOOP;
        private int index;
        private List<IOnPlayerEventListener> events;


        public Builder add(IRequestHandler handler) {
            return this;
        }

        public Builder playMode(PlayMode playMode) {
            this.playMode = playMode;
            return this;
        }

        public Builder type(MediaType type) {
            this.type = type;
            return this;
        }

        public Builder load(@NonNull String... paths) {
            checkNull(paths, "Path must not be null.");
            uris.clear();

            for (String s : paths) {
                append(Uri.parse(s));
            }

            return this;
        }

        public Builder load(@NonNull File... files) {
            checkNull(files, "File must not be null.");

            uris.clear();
            for (File f : files) {
                append(Uri.fromFile(f));
            }
            return this;
        }

        public Builder load(@NonNull Uri... uri) {
            checkNull(uri, "URI may not be null.");


            uris.clear();
            for (Uri u : uri) {
                append(u);
            }
            return this;
        }

        public Builder index(int index) {
            this.index = index;
            return this;
        }

        private void append(Uri uri) {
            if (uris == null) {
                uris = new ArrayList();
            }

            uris.add(uri);
        }


        public ParamsCreator build() {
            checkEmptyOrNull(uris, "Uris can not be empty or null");

            if (uris.size() <= index) {
                throw new IllegalArgumentException("play index wrong");
            }

            return new ParamsCreator(this);
        }
    }
}
