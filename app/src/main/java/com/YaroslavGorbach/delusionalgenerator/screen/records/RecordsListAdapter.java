package com.YaroslavGorbach.delusionalgenerator.screen.records;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.databinding.ItemRecordBinding;
import com.YaroslavGorbach.delusionalgenerator.util.TimeUtil;

import java.io.File;

public class RecordsListAdapter extends ListAdapter<Record, RecordsListAdapter.AudioViewHolder> {

    public interface Listener { void onPlay(Record record);}
    private final Listener Listener;

    public RecordsListAdapter(Listener Listener) {
        super(new DiffCallback());
        this.Listener = Listener;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AudioViewHolder(ItemRecordBinding.bind(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_record, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder {
        ItemRecordBinding binding;

        public AudioViewHolder(ItemRecordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(c ->
                    Listener.onPlay(getItem(getBindingAdapterPosition())));
        }

        private void bind(Record record) {
            if (record.isPlaying){
                binding.iconPlay.setImageDrawable(
                        ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_pause));
            }else {
                binding.iconPlay.setImageDrawable(
                        ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_play));
            }
            binding.title.setText(record.getName());
            binding.date.setText(TimeUtil.getTimeAgo(record.getLastModified()));
            binding.duration.setText(record.getDuration());
        }
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<Record>{

        @Override
        public boolean areItemsTheSame(@NonNull Record oldItem, @NonNull Record newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Record oldItem, @NonNull Record newItem) {
            return false;
        }
    }
}
