package com.YaroslavGorbach.delusionalgenerator.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Adapters.AudioListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.AudioListViewModel;
import com.YaroslavGorbach.delusionalgenerator.Helpers.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class AudioListFragment extends Fragment  {

    private BottomSheetBehavior mBottomSheetBehavior;
    private RecyclerView mAudioList;
    private AudioListAdapter mAudioListAdapter;
    private AppCompatImageButton playResumeButton;
    private TextView playerHeader;
    private TextView playerFilename;
    private MaterialToolbar mToolbar;
    private AppCompatImageButton buttonAgo;
    private AppCompatImageButton buttonForward;
    private AppCompatSeekBar playerSeekbar;
    private CoordinatorLayout mCoordinatorLayout;
    private AppCompatImageView mImageNoData;
    private TextView mTextViewNoData;
    private AudioListViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)  {
        View view = inflater.inflate(R.layout.activity_audio_list, container, false);
        ConstraintLayout mPlayerSheet = view.findViewById(R.id.player_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(mPlayerSheet);
        mAudioList = view.findViewById(R.id.audio_list_view);
        playResumeButton = view.findViewById(R.id.player_play_btn);
        playerHeader = view.findViewById(R.id.player_header_title);
        playerFilename = view.findViewById(R.id.player_filename);
        playerSeekbar = view.findViewById(R.id.player_seekbar);
        buttonAgo = view.findViewById(R.id.buttonAgo);
        buttonForward = view.findViewById(R.id.buttonForward);
        mCoordinatorLayout = view.findViewById(R.id.coordinatorLayout);
        mImageNoData = view.findViewById(R.id.audio_fragment_image_nothing);
        mTextViewNoData = view.findViewById(R.id.audio_fragment_text_nothing);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mToolbar = getActivity().findViewById(R.id.toolbar_main_a);
        mToolbar.inflateMenu(R.menu.menu_recordings_del);
        mToolbar.getMenu().getItem(0).setVisible(true);
        mViewModel = new ViewModelProvider(this).get(AudioListViewModel.class);

        /*Показ банера*/
        AdMob.showBanner(view.findViewById(R.id.adViewTabAudioList));

        /*Инициализация адаптера и лисенера который отвечает за нажатие на елемент списка*/
        setAdapterForListOfRecords();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*старт/пауза*/
        playResumeButton.setOnClickListener(v -> {
            if (mViewModel.eventPause.getValue() != null && mViewModel.eventPause.getValue()){
                mViewModel.resumeAudio();
            }else {
                mViewModel.pauseAudio();
            }
        });

        /*Перемотка назад*/
        buttonAgo.setOnClickListener(v -> mViewModel.tenSecondsAgo(playerSeekbar.getProgress()));

        /*Перемотка вперед*/
        buttonForward.setOnClickListener(v -> mViewModel.tenSecondsForward(playerSeekbar.getProgress()));

        /*Настройка перемотки записи з помощу сик бара*/
        playerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mViewModel.pauseAudio();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mViewModel.getMediaPlayer() != null) {
                    int progress = seekBar.getProgress();
                    mViewModel.getMediaPlayer().seekTo(progress);
                    mViewModel.resumeAudio();
                }
            }
        });

        /*Спрятать плеер если не играет*/
        mBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_HIDDEN){
                    mViewModel.stopAudio();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    private void setAdapterForListOfRecords() {
        mViewModel.files.observe(getViewLifecycleOwner(), files -> {

            if (files != null && files.length > 0){
                mImageNoData.setVisibility(View.GONE);
                mTextViewNoData.setVisibility(View.GONE);
                mCoordinatorLayout.setVisibility(View.VISIBLE);
            }else {
                String color = Repo.getInstance(getContext()).getThemeState();
                switch (color) {
                    case "blue":
                        mImageNoData.setImageResource(R.drawable.no_data_blue);
                        break;

                    case "green":
                        mImageNoData.setImageResource(R.drawable.no_data_green);
                        break;

                    case "orange":
                        mImageNoData.setImageResource(R.drawable.no_files);
                        break;

                    case "red":
                        mImageNoData.setImageResource(R.drawable.no_data_red);
                        break;

                    case "purple":
                        mImageNoData.setImageResource(R.drawable.no_data_purpure);
                        break;
                }
                mImageNoData.setVisibility(View.VISIBLE);
                mTextViewNoData.setVisibility(View.VISIBLE);
                mCoordinatorLayout.setVisibility(View.GONE);
            }

            mAudioListAdapter = new AudioListAdapter(files, (file, position) -> {
                playerFilename.setText(file.getName());
                if ((mViewModel.eventPlaying.getValue() == null || mViewModel.eventPlaying.getValue())
                        && mViewModel.getMediaPlayer() != null) {
                    mViewModel.stopAudio();
                }
                    new Handler().postDelayed(() -> mViewModel.playAudio(file), 500);
            });
            /*Настройка списка*/
            mAudioList.setHasFixedSize(true);
            mAudioList.setLayoutManager(new LinearLayoutManager(getContext()));
            mAudioList.setAdapter(mAudioListAdapter);
            mAudioList.addItemDecoration(new DividerItemDecoration(getContext(),
                    DividerItemDecoration.VERTICAL));
        });

        mViewModel.eventPlaying.observe(getViewLifecycleOwner(), isPaying -> {
            if (isPaying){
                playResumeButton.setImageDrawable(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.ic_player_pause_btn, null));
                playerHeader.setText("Играет...");
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                playerSeekbar.setMax(mViewModel.getMediaPlayer().getDuration());
            }else{
                playerHeader.setText("Закончено");
                playResumeButton.setImageDrawable(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.ic_player_play_btn, null));
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
            });

        mViewModel.eventPause.observe(getViewLifecycleOwner(), isPause -> {
            if (isPause){
                playerHeader.setText("Пауза");
                playResumeButton.setImageDrawable(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.ic_player_play_btn, null));
            }else{
                playerHeader.setText("Играет...");
                playResumeButton.setImageDrawable(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.ic_player_pause_btn, null));
            }
        });

        mViewModel.seekBarProgress.observe(getViewLifecycleOwner(), progress -> {
            playerSeekbar.setProgress(progress);
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mToolbar.getMenu().getItem(0).setVisible(true);
    }

    @Override
    public void onStop() {
        super.onStop();
            mViewModel.pauseAudio();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
            mViewModel.stopAudio();
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}