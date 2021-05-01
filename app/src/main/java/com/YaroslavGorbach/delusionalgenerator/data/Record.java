package com.YaroslavGorbach.delusionalgenerator.data;

import com.YaroslavGorbach.delusionalgenerator.util.TimeUtil;

import java.io.File;

public class Record {
    private final File file;
    private final long lastModified;
    private final String name;
    private final String duration;
    public boolean isPlaying = false;
    public boolean isPause = false;

    public Record(File file) {
        this.file = file;
        lastModified = file.lastModified();
        name = file.getName();
        duration = TimeUtil.getFileDuration(file);
    }

    public File getFile() {
        return file;
    }

    public long getLastModified() {
        return lastModified;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }


}
