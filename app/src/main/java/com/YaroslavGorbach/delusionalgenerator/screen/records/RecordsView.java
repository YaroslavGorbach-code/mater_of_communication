package com.YaroslavGorbach.delusionalgenerator.screen.records;

import android.os.Build;
import android.widget.SeekBar;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentRecordsBinding;

import java.util.List;

public class RecordsView {
    public interface Callback{
        void onRecord(Record record);
        void onSkipNext();
        void onSkipPrevious();
        void onPauseResume();
        void onSeekTo(int progress);

    }

    private final RecordsAdapter mAdapter;
    private final FragmentRecordsBinding mBinding;

    public RecordsView(FragmentRecordsBinding binding, Callback callback){
        mBinding = binding;
        binding.include.playerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        mBinding.include.playerStartPause.setOnClickListener(v-> callback.onPauseResume());
        mBinding.include.playerSkipNext.setOnClickListener(v-> callback.onSkipNext());
        mBinding.include.playerSkipPrevious.setOnClickListener(v-> callback.onSkipPrevious());

        mAdapter = new RecordsAdapter(record ->{
            callback.onRecord(record);
            binding.include.playerRecordName.setText(record.getName());
        });

        binding.recordsList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.recordsList.setAdapter(mAdapter);

    }
    public void setRecords(List<Record> records){
        mAdapter.setData(records);
    }

    public void setIsPlaying(boolean isPlaying){
        if (isPlaying){
            mBinding.include.playerStartPause.setImageResource(R.drawable.ic_pause_round);
        }else {
            mBinding.include.playerStartPause.setImageResource(R.drawable.ic_play_round);
        }
    }

    public void setDuration(int duration){
        mBinding.include.playerSeekBar.setMax(duration);
    }

    public void setProgress(int progress){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding.include.playerSeekBar.setProgress(progress, true);
        }else {
            mBinding.include.playerSeekBar.setProgress(progress);
        }
    }
}
