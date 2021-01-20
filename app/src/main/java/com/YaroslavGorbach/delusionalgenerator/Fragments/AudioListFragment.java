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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.Adapters.AudioListAdapter;
import com.YaroslavGorbach.delusionalgenerator.Database.ViewModels.AudioListViewModel;
import com.YaroslavGorbach.delusionalgenerator.Helpers.AdMob;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;

public class AudioListFragment extends Fragment  {

    private BottomSheetBehavior mBottomSheetBehavior;
    private RecyclerView mAudioList;
    private AudioListAdapter mAudioListAdapter;
    private AppCompatImageButton mPlayResumeButton;
    private TextView mPlayerHeader;
    private TextView mPlayerFilename;
    private MaterialToolbar mToolbar;
    private AppCompatImageButton mButtonAgo;
    private AppCompatImageButton mButtonForward;
    private AppCompatSeekBar mPlayerSeekbar;
    private CoordinatorLayout mCoordinatorLayout;
    private AppCompatImageView mImageNoData;
    private TextView mTextViewNoData;
    private AudioListViewModel mViewModel;
    private TextView mDurationProgress;
    private TextView mDuration;

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
        mPlayResumeButton = view.findViewById(R.id.player_play_btn);
        mPlayerHeader = view.findViewById(R.id.player_header_title);
        mPlayerFilename = view.findViewById(R.id.player_filename);
        mPlayerSeekbar = view.findViewById(R.id.player_seekbar);
        mButtonAgo = view.findViewById(R.id.buttonAgo);
        mButtonForward = view.findViewById(R.id.buttonForward);
        mCoordinatorLayout = view.findViewById(R.id.coordinatorLayout);
        mImageNoData = view.findViewById(R.id.audio_fragment_image_nothing);
        mTextViewNoData = view.findViewById(R.id.audio_fragment_text_nothing);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mToolbar = getActivity().findViewById(R.id.toolbar_main_a);
        mToolbar.inflateMenu(R.menu.menu_recordings_del);
        mDuration = view.findViewById(R.id.file_duration);
        mDurationProgress = view.findViewById(R.id.file_duration_process);
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
        mPlayResumeButton.setOnClickListener(v -> {
            if (mViewModel.eventPause.getValue() != null && mViewModel.eventPause.getValue()){
                mViewModel.resumeAudio();
            }else {
                mViewModel.pauseAudio();
            }
        });

        /*Перемотка назад*/
        mButtonAgo.setOnClickListener(v -> mViewModel.tenSecondsAgo(mPlayerSeekbar.getProgress()));

        /*Перемотка вперед*/
        mButtonForward.setOnClickListener(v -> mViewModel.tenSecondsForward(mPlayerSeekbar.getProgress()));

        /*Настройка перемотки записи з помощу сик бара*/
        mPlayerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

            showImageNoData(files);

            mAudioListAdapter = new AudioListAdapter(files, (file) -> {
                mPlayerFilename.setText(file.getName());
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
                mPlayResumeButton.setImageDrawable(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.ic_player_pause_btn, null));
                mPlayerHeader.setText("Играет...");
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                mPlayerSeekbar.setMax(mViewModel.getMediaPlayer().getDuration());
            }else{
                mPlayerHeader.setText("Закончено");
                mPlayResumeButton.setImageDrawable(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.ic_player_play_btn, null));
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
            });

        mViewModel.eventPause.observe(getViewLifecycleOwner(), isPause -> {
            if (isPause){
                mPlayerHeader.setText("Пауза");
                mPlayResumeButton.setImageDrawable(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.ic_player_play_btn, null));
            }else{
                mPlayerHeader.setText("Играет...");
                mPlayResumeButton.setImageDrawable(ResourcesCompat.getDrawable(
                        getResources(), R.drawable.ic_player_pause_btn, null));
            }
        });

        mViewModel.seekBarProgress.observe(getViewLifecycleOwner(), progress -> {
            mPlayerSeekbar.setProgress(progress);
        });

    }

    private void showImageNoData(File[] files) {
        if (files != null && files.length > 0){
            mImageNoData.setVisibility(View.GONE);
            mTextViewNoData.setVisibility(View.GONE);
            mCoordinatorLayout.setVisibility(View.VISIBLE);
        }else {
            mImageNoData.setImageResource(R.drawable.ic_no_data);
            mImageNoData.setVisibility(View.VISIBLE);
            mTextViewNoData.setVisibility(View.VISIBLE);
            mCoordinatorLayout.setVisibility(View.GONE);
        }
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