package com.YaroslavGorbach.delusionalgenerator.feature.voiceRecorder;

import android.content.Context;
import android.media.MediaRecorder;

import com.YaroslavGorbach.delusionalgenerator.util.TimeAndDataUtil;

import java.io.IOException;
import java.util.Date;

public class VoiceRecorderImp implements VoiceRecorder {
    private boolean mIsRecording = false;
    private MediaRecorder mMediaRecorder;

    @Override
    public void start(Context context, String name) {
        mMediaRecorder = new MediaRecorder();
        long time = new Date().getTime();
        String recordFile = name + TimeAndDataUtil.formatRecord(time) + ".3gp";
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
