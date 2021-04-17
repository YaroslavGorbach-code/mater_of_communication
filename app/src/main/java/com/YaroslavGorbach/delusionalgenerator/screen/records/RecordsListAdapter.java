package com.YaroslavGorbach.delusionalgenerator.screen.records;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.ItemRecordBinding;
import com.YaroslavGorbach.delusionalgenerator.util.TimeUtil;

import java.io.File;

public class RecordsListAdapter extends ListAdapter<File, RecordsListAdapter.AudioViewHolder> {

    public interface Listener { void onPlay(File file);}

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
        holder.bind(position);
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder {
        ItemRecordBinding binding;

        public AudioViewHolder(ItemRecordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(c ->
                    Listener.onPlay(getItem(getBindingAdapterPosition())));
        }

        private void bind(int position) {
            binding.title.setText(getItem(position).getName());
            binding.date.setText(TimeUtil.getTimeAgo(getItem(position).lastModified()));
            binding.duration.setText(TimeUtil.getFileDuration(getItem(position)));
        }
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<File>{

        @Override
        public boolean areItemsTheSame(@NonNull File oldItem, @NonNull File newItem) {
            return false; // TODO: 4/12/2021 implement equals
        }

        @Override
        public boolean areContentsTheSame(@NonNull File oldItem, @NonNull File newItem) {
            return false;
        }
    }
}
