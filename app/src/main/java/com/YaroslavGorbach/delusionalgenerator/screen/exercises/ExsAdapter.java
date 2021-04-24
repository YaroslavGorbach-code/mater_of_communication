package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.ItemExBinding;
import com.bumptech.glide.Glide;

public class ExsAdapter extends ListAdapter<Exercise, ExsAdapter.ExsVh> {
    interface Listener{ void onClick(Exercise exercise);}

    private final Listener mListener;

    protected ExsAdapter(Listener listener) {
        super(new DiffCallback());
        mListener = listener;
    }

    @NonNull
    @Override
    public ExsVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ExsVh(ItemExBinding.bind(LayoutInflater.from(
               parent.getContext()).inflate(R.layout.item_ex, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull ExsVh holder, int position) {
        holder.bind(getItem(position));
    }

    public class ExsVh extends RecyclerView.ViewHolder {
        ItemExBinding binding;
        public ExsVh(ItemExBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(c ->
                    mListener.onClick(getItem(getBindingAdapterPosition())));
        }

        public void bind(Exercise item) {
            binding.itemName.setText(itemView.getContext().getString(item.name.getNameId()));
            binding.itemCategory.setText(item.category.getName());
            Glide.with(itemView.getContext()).load(item.imageId).into(binding.itemImage);
        }
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<Exercise>{

        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return false; // TODO: 4/12/2021 implement equals
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return false;
        }
    }
}
