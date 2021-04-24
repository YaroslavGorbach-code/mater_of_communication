package com.YaroslavGorbach.delusionalgenerator.data;

import androidx.annotation.Nullable;

import com.YaroslavGorbach.delusionalgenerator.util.TimeUtil;

import java.io.File;
import java.util.Objects;

public class Record {
    private final File file;
    private final long lastModified;
    public String name; // TODO: 4/24/2021 make private
    private final String duration;
    public boolean isPlaying = false;

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

    public boolean equals(@Nullable Record record) {
        return Objects.equals(lastModified, record.lastModified)
                && Objects.equals(name, record.name)
                && Objects.equals(duration, record.duration)
                && Objects.equals(isPlaying, record.isPlaying);

    }
}
