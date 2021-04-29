package com.YaroslavGorbach.delusionalgenerator.screen.dailyTraining;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.ItemDailyTrainingExBinding;


public class DailyTrainingExsAdapter extends ListAdapter<Exercise, DailyTrainingExsAdapter.Vh> {
    private final Listener mListener;
    public interface Listener{
        void onClick(Exercise exercise);
    }

    public DailyTrainingExsAdapter(Listener listener) {
        super(new DiffCallback());
        mListener = listener;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(ItemDailyTrainingExBinding.bind(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_daily_training_ex, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.bind(getItem(position));
    }

    public class Vh extends RecyclerView.ViewHolder {
        final ItemDailyTrainingExBinding mBinding;

        public Vh(ItemDailyTrainingExBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            binding.item.setOnClickListener(v -> mListener.onClick(getItem(getBindingAdapterPosition())));
        }

        @SuppressLint("SetTextI18n")
        void bind(Exercise ex){
            mBinding.name.setText(itemView.getContext().getString(ex.getName().getNameId()));
            mBinding.image.setImageResource(ex.getImageId());
            mBinding.aim.setText("Пройдено слов " + ex.getDone() + "/" + ex.getAim()); // TODO: 4/28/2021 fix it later
        }
    }

    public static class DiffCallback extends DiffUtil.ItemCallback<Exercise>{

        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return true;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.getDone() == newItem.getDone();
        }
    }
}
