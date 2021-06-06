package com.YaroslavGorbach.delusionalgenerator.screen.training;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.ItemTrainingExBinding;
import com.bumptech.glide.Glide;


public class TrainingListAdapter extends ListAdapter<Exercise, TrainingListAdapter.Vh> {
    private final Listener mListener;
    public interface Listener{
        void onClick(Exercise exercise);
    }

    public TrainingListAdapter(Listener listener) {
        super(new DiffCallback());
        mListener = listener;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(ItemTrainingExBinding.bind(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_training_ex, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.bind(getItem(position));
    }

    public class Vh extends RecyclerView.ViewHolder {
        final ItemTrainingExBinding mBinding;

        public Vh(ItemTrainingExBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            binding.item.setOnClickListener(v -> mListener.onClick(getItem(getBindingAdapterPosition())));
        }

        @SuppressLint("SetTextI18n")
        void bind(Exercise ex){
            mBinding.name.setText(itemView.getContext().getString(ex.getName().getNameId()));
            Glide.with(itemView.getContext()).load(ex.getImageId()).into(mBinding.image);
            mBinding.aim.setText(ex.done + "/" + ex.aim);
            mBinding.category.setText(ex.getCategory().getNameId());
            if (ex.getCategory() == Exercise.Category.VOCABULARY) mBinding.aim.setVisibility(View.GONE);
            if (ex.done == ex.aim) mBinding.icComplete.setImageResource(R.drawable.ic_done_round);
        }
    }

    public static class DiffCallback extends DiffUtil.ItemCallback<Exercise>{

        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return true;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.done == newItem.done;
        }
    }
}
