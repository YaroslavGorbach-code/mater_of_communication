package com.YaroslavGorbach.delusionalgenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;

public class AudioListActivity extends AppCompatActivity {

    private BottomSheetBehavior mBottomSheetBehavior;
    private RecyclerView mAudioList;
    private File[] mAllFiles;
    private AudioListAdapter mAudioListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_list);
        initializeComponents();

        String path = this.getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(path);
        mAllFiles = directory.listFiles();

        mAudioListAdapter = new AudioListAdapter(mAllFiles, new AudioListAdapter.onItemListClick() {
            @Override
            public void onClickListener(File file, int position) {

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
    }

    private void initializeComponents() {
        ConstraintLayout mPlayerSheet = findViewById(R.id.player_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(mPlayerSheet);
        mAudioList = findViewById(R.id.audio_list_view);
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

}