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

import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManager;
import com.YaroslavGorbach.delusionalgenerator.feature.ad.AdManagerImp;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentRecordsBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.transition.MaterialFade;

import java.util.List;

public class RecordsView {
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
    private final RecordsAdapter mAdapter;
    private final FragmentRecordsBinding mBinding;
    private boolean mIsPlaying = false;

    public RecordsView(FragmentRecordsBinding binding, Callback callback, AdManager adManager) {

        // show Ad
        adManager.showBanner(binding.getRoot().getContext(), binding.bannerContainer);

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
                mAdapter.notifyItemRemoved(position);
                showNoRecordsIcon(mAdapter.getData().isEmpty());

                if (mBinding.player.recordName.getText().equals(item.getName())){
                    setPlayerVisibility(mBinding.player.getRoot(), mBinding.getRoot(), false);
                    callback.onPause();
                }

                Snackbar.make(binding.getRoot(), R.string.record_deleted, Snackbar.LENGTH_LONG)
                        .setAction(mBinding.getRoot().getContext().getString(R.string.cancel), v -> {
                            mAdapter.getData().add(position, item);
                            mAdapter.notifyItemInserted(position);
                            showNoRecordsIcon(mAdapter.getData().isEmpty());
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
            showNoRecordsIcon(records.isEmpty());
            mAdapter.setData(records);
    }

    private void showNoRecordsIcon(boolean isVisible){
        if (isVisible) {
            mBinding.noRecords.setVisibility(View.VISIBLE);
        } else {
            mBinding.noRecords.setVisibility(View.GONE);
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

}
