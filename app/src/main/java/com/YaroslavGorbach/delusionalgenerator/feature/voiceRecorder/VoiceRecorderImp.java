package com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class VoiceRecorderImp implements VoiceRecorder {
    private boolean mIsRecording = false;
    private MediaRecorder mMediaRecorder;

    @Override
    public void start(Context context, String name) {
        mMediaRecorder = new MediaRecorder();
        String recordFile = name + ".3gp";
        recordFile = recordFile.replace(" ", "_");
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mMediaRecorder.setOutputFile(context.getExternalFilesDir("/").getAbsolutePath() + "/" + recordFile);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mMediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaRecorder.start();
        mIsRecording = true;
    }

    @Override
    public void stop() {
        if (mMediaRecorder !=null){
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
            mIsRecording = false;
        }
    }

    @Override
    public boolean getState() {
        return mIsRecording;
    }

}
