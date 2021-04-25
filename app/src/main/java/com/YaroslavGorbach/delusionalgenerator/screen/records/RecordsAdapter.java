package com.YaroslavGorbach.delusionalgenerator.screen.records;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Record;
import com.YaroslavGorbach.delusionalgenerator.databinding.ItemRecordBinding;
import com.YaroslavGorbach.delusionalgenerator.util.TimeUtil;

import java.util.List;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.AudioViewHolder> {

    public interface Listener { void onPlay(Record record);}
    private final Listener Listener;
    private List<Record> mData;

    public RecordsAdapter(Listener Listener) {
        this.Listener = Listener;
        this.setHasStableIds(true);
    }

    public void setData(List<Record> data){
        mData = data;
        notifyDataSetChanged();
    }

    public List<Record> getData(){
        return mData;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AudioViewHolder(ItemRecordBinding.bind(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_record, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getLastModified();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder {
        ItemRecordBinding binding;

        public AudioViewHolder(ItemRecordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(c ->
                    Listener.onPlay(mData.get(getBindingAdapterPosition())));
        }


        private void bind(Record record) {
            if (record.isPlaying){
                binding.iconPlay.setImageDrawable(
                        ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_pause_round));
            }else {
                binding.iconPlay.setImageDrawable(
                        ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_play_round));
            }
            binding.title.setText(record.getName());
            binding.date.setText(TimeUtil.getTimeAgo(record.getLastModified()));
            binding.duration.setText(record.getDuration());
        }
    }

}
