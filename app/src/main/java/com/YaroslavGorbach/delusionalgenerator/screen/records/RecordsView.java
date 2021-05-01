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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.transition.MaterialFade;

import java.util.List;

public class RecordsView {
    private final RecordsAdapter mAdapter;
    private final FragmentRecordsBinding mBinding;
    private boolean mIsPlaying = false;
    public RecordsView(FragmentRecordsBinding binding, Callback callback) {
        mBinding = binding;
        binding.player.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                callback.onPause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                callback.onSeekTo(seekBar.getProgress());
                callback.onResume();
            }
        });
        mBinding.player.startPause.setOnClickListener(v -> {
            if (mIsPlaying) {
                callback.onPause();
            } else {
                callback.onResume();
            }
        });
        mBinding.player.skipNext.setOnClickListener(v -> callback.onSkipNext());
        mBinding.player.skipPrevious.setOnClickListener(v -> callback.onSkipPrevious());

        mAdapter = new RecordsAdapter(record -> {
            callback.onRecord(record);
            binding.player.recordName.setText(record.getName());
            setPlayerVisibility(binding.player.getRoot(), binding.getRoot(), true);
        });

        SwipeDeleteDecor swipeDeleteDecor = new SwipeDeleteDecor(new ItemSwipeCallback() {
            boolean undo = false;

            @Override
            public void onSwipe(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getBindingAdapterPosition();
                Record item = mAdapter.getData().get(position);
                undo = false;
                mAdapter.getData().remove(item);
                mAdapter.notifyDataSetChanged();

                Snackbar.make(binding.getRoot(), "Запись удалена", Snackbar.LENGTH_LONG)
                        .setAction("ОТМЕНА", v -> {
                            mAdapter.getData().add(position, item);
                            setRecords(mAdapter.getData());
                            undo = true;
                        })
                        .addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                super.onDismissed(transientBottomBar, event);
                                if (!undo) {
                                    callback.onRemove(item);
                                    setRecords(mAdapter.getData());
                                }
                            }
                        })
                        .show();
            }
        }, ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.delete_record_hint_bg));

        binding.recordsList.addItemDecoration(swipeDeleteDecor);
        swipeDeleteDecor.attachToRecyclerView(binding.recordsList);
        binding.recordsList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.recordsList.setAdapter(mAdapter);

    }

    public void setRecords(List<Record> records) {
        if (records.isEmpty()) {
            mBinding.noRecords.setVisibility(View.VISIBLE);
            mBinding.recordsList.setVisibility(View.GONE);
            setPlayerVisibility(mBinding.player.getRoot(), mBinding.getRoot(), false);
        } else {
            mBinding.noRecords.setVisibility(View.GONE);
            mBinding.recordsList.setVisibility(View.VISIBLE);
            mAdapter.setData(records);
        }
    }

    public void setIsPlaying(boolean isPlaying) {
        mIsPlaying = isPlaying;
        if (isPlaying) {
            mBinding.player.startPause.setImageResource(R.drawable.ic_pause_round);
        } else {
            mBinding.player.startPause.setImageResource(R.drawable.ic_play_round);
        }
    }

    public void setDuration(int duration) {
        mBinding.player.seekBar.setMax(duration);
    }

    public void setProgress(int progress) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding.player.seekBar.setProgress(progress, true);
        } else {
            mBinding.player.seekBar.setProgress(progress);
        }
    }

    private void setPlayerVisibility(View view, ViewGroup viewGroup, boolean isVisible) {
        Transition transition = new MaterialFade();
        transition.setDuration(400);
        transition.addTarget(R.id.player);
        TransitionManager.beginDelayedTransition(viewGroup, transition);
        if (isVisible) view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }

    public interface Callback {
        void onRecord(Record record);

        void onSkipNext();

        void onSkipPrevious();

        void onPause();

        void onResume();

        void onSeekTo(int progress);

        void onRemove(Record record);
    }


    public interface ItemSwipeCallback {
        void onSwipe(RecyclerView.ViewHolder viewHolder);
    }

}
