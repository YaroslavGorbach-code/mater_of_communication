package com.YaroslavGorbach.delusionalgenerator.feature.voicerecorder;

import android.media.MediaRecorder;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class VoiceRecorderImp implements VoiceRecorder {
    private boolean mIsRecording = false;
    private  MediaRecorder mediaRecorder;
    private final String mRecordPath;

    public VoiceRecorderImp(String recordPath){
        mRecordPath = recordPath;
    }

    @Override
    public void start() {
        mediaRecorder = new MediaRecorder();
        DateFormat dataFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.LONG, Locale.getDefault());
        Date now = new Date();
        String recordFile = "exName" + dataFormat.format(now) + ".3gp";
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(mRecordPath + "/" + recordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
        mIsRecording = true;
    }

    @Override
    public void stop() {
        mIsRecording = false;
        mediaRecorder.stop();
        mediaRecorder = null;
    }

    @Override
    public boolean getState() {
        return mIsRecording;
    }

}
