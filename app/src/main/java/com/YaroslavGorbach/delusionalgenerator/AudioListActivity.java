package com.YaroslavGorbach.delusionalgenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class AudioListActivity extends AppCompatActivity {

    private BottomSheetBehavior mBottomSheetBehavior;
    private RecyclerView mAudioList;
    private File[] mAllFiles;
    private AudioListAdapter mAudioListAdapter;
    private MediaPlayer mediaPlayer = null;
    private boolean isPlaying = false;

    private ImageButton playBtn;
    private TextView playerHeader;
    private TextView playerFilename;

    private ImageButton buttonAgo;
    private ImageButton buttonForward;

    private SeekBar playerSeekbar;
    private Handler seekbarHandler;
    private Runnable updateSeekbar;

    private File fileToPlay = null;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_list);
        initializeComponents();

        String path = this.getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(path);
        mAllFiles = directory.listFiles();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (mAllFiles != null){
                Arrays.sort(mAllFiles, Comparator.comparingLong(File::lastModified).reversed());
            }
        }
        mToolbar.setNavigationOnClickListener(v-> finish());
        mToolbar.setOnMenuItemClickListener(c->{
            // TODO: 12.09.2020 показать диалог подтверждения удаления
            for(File f:mAllFiles){
            f.delete();
            }
            recreate();
            return true;
        });

        mAudioListAdapter = new AudioListAdapter(mAllFiles, new AudioListAdapter.onItemListClick() {
            @Override
            public void onClickListener(File file, int position) {
                fileToPlay = file;
                if(isPlaying){
                    stopAudio();
                    playAudio(fileToPlay);
                } else {
                    playAudio(fileToPlay);
                }
            }

        });

        mAudioList.setHasFixedSize(true);
        mAudioList.setLayoutManager(new LinearLayoutManager(this));
        mAudioList.setAdapter(mAudioListAdapter);

        // фикс бага который скрывает плеер если потянуть вниз
        mBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_HIDDEN){
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //We cant do anything here for this app
            }
        });

        playerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pauseAudio();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mediaPlayer.seekTo(progress);
                resumeAudio();
            }
        });

        playBtn.setOnClickListener(v -> {
            if(isPlaying){
                pauseAudio();
            } else {
                if(fileToPlay != null){
                    resumeAudio();
                }
            }
        });

        buttonAgo.setOnClickListener(v -> {
            int progress = playerSeekbar.getProgress();
            progress -=800;
            mediaPlayer.seekTo(progress);
        });

        buttonForward.setOnClickListener(v ->{
            int progress = playerSeekbar.getProgress();
            progress +=800;
            mediaPlayer.seekTo(progress);
        });
        
    }

    private void resumeAudio() {
        mediaPlayer.start();
        playBtn.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_player_pause_btn, null));
        isPlaying = true;

        updateRunnable();
        seekbarHandler.postDelayed(updateSeekbar, 0);
    }

    private void pauseAudio() {
        mediaPlayer.pause();
        playBtn.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_player_play_btn, null));
        isPlaying = false;
        seekbarHandler.removeCallbacks(updateSeekbar);
    }


    private void playAudio(File fileToPlay) {
        mediaPlayer = new MediaPlayer();
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        try {
            mediaPlayer.setDataSource(fileToPlay.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        playBtn.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_player_pause_btn, null));
        playerFilename.setText(fileToPlay.getName());
        playerHeader.setText("Playing");
        isPlaying = true;
        mediaPlayer.setOnCompletionListener(mp -> {
            stopAudio();
            playerHeader.setText("Finished");
            isPlaying = false;
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });

       playerSeekbar.setMax(mediaPlayer.getDuration());

        seekbarHandler = new Handler();
        updateRunnable();
        seekbarHandler.postDelayed(updateSeekbar, 0);
    }

    private void updateRunnable() {
        updateSeekbar = new Runnable() {
            @Override
            public void run() {
                playerSeekbar.setProgress(mediaPlayer.getCurrentPosition());
                seekbarHandler.postDelayed(this, 500);
            }
        };
    }

    private void stopAudio() {
        playBtn.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_player_play_btn, null));
        playerHeader.setText("Stopped");
        isPlaying = false;
        mediaPlayer.stop();
        seekbarHandler.removeCallbacks(updateSeekbar);
    }

    private void initializeComponents() {

        ConstraintLayout mPlayerSheet = findViewById(R.id.player_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(mPlayerSheet);
        mAudioList = findViewById(R.id.audio_list_view);
        playBtn = findViewById(R.id.player_play_btn);
        playerHeader = findViewById(R.id.player_header_title);
        playerFilename = findViewById(R.id.player_filename);
        playerSeekbar = findViewById(R.id.player_seekbar);
        buttonAgo = findViewById(R.id.buttonAgo);
        buttonForward = findViewById(R.id.buttonForward);
        mToolbar = findViewById(R.id.toolbar_records);

    }

    private void setTheme(){
        String color = Repo.getInstance(AudioListActivity.this).getThemeState();
        switch (color){

            case "blue":

                setTheme(R.style.AppTheme_blue);

                break;

            case "green":

                setTheme(R.style.AppTheme_green);
                break;

            case "orange":

                setTheme(R.style.AppTheme_orange);
                break;

            case "red":

                setTheme(R.style.AppTheme_red);
                break;

            case "purple":

                setTheme(R.style.AppTheme_purple);
                break;

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(isPlaying) {
            stopAudio();
        }
    }

}