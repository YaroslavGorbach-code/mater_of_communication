package com.YaroslavGorbach.delusionalgenerator.screen.records;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentRecordsBinding;
import com.google.android.material.transition.MaterialFade;

import java.util.List;

public class RecordsView {
    public interface Callback{
        void onRecord(Record record);
        void onSkipNext();
        void onSkipPrevious();
        void onPauseResume();
        void onSeekTo(int progress);
        void onSwipe(Record record);
    }

    public interface ItemSwipeCallback{
        void onSwipe(RecyclerView.ViewHolder viewHolder);
    }

    private final RecordsAdapter mAdapter;
    private final FragmentRecordsBinding mBinding;

    public RecordsView(FragmentRecordsBinding binding, Callback callback){
        mBinding = binding;
        binding.player.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { callback.onPauseResume(); }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                callback.onSeekTo(seekBar.getProgress());
                callback.onPauseResume();
            }
        });
        mBinding.player.startPause.setOnClickListener(v-> callback.onPauseResume());
        mBinding.player.skipNext.setOnClickListener(v-> callback.onSkipNext());
        mBinding.player.skipPrevious.setOnClickListener(v-> callback.onSkipPrevious());

        mAdapter = new RecordsAdapter(record ->{
            callback.onRecord(record);
            binding.player.recordName.setText(record.getName());
            showPlayerView(binding.player.getRoot(), binding.getRoot());
        });

        SwipeDeleteDecor swipeDeleteDecor = new SwipeDeleteDecor(viewHolder ->
                callback.onSwipe(mAdapter.getData().get(viewHolder.getBindingAdapterPosition())),
                ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.delete_record_hint_bg));
        binding.recordsList.addItemDecoration(swipeDeleteDecor);
        swipeDeleteDecor.attachToRecyclerView(binding.recordsList);
        binding.recordsList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.recordsList.setAdapter(mAdapter);

    }
    public void setRecords(List<Record> records){
        mAdapter.setData(records);
    }

    public void setIsPlaying(boolean isPlaying){
        if (isPlaying){
            mBinding.player.startPause.setImageResource(R.drawable.ic_pause_round);
        }else {
            mBinding.player.startPause.setImageResource(R.drawable.ic_play_round);
        }
    }

    public void setDuration(int duration){
        mBinding.player.seekBar.setMax(duration);
    }

    public void setProgress(int progress){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding.player.seekBar.setProgress(progress, true);
        }else {
            mBinding.player.seekBar.setProgress(progress);
        }
    }

    private void showPlayerView(View view, ViewGroup viewGroup){
        Transition transition = new MaterialFade();
        transition.setDuration(400);
        transition.addTarget(R.id.player);
        TransitionManager.beginDelayedTransition(viewGroup, transition);
        view.setVisibility(View.VISIBLE);
    }
}
