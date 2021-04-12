package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.ExModel;
import com.bumptech.glide.Glide;

public class ExsAdapter extends ListAdapter<ExModel, ExsAdapter.ExsVh> {
    private final Listener mListener;

    protected ExsAdapter(Listener listener) {
        super(new DiffCallback());
        mListener = listener;
    }

    interface Listener{
        void onClick(ExModel exModel);
    }

    @NonNull
    @Override
    public ExsVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ExsVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.excersice_item,
               parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExsVh holder, int position) {
        holder.ex_name.setText(getItem(position).name.getName());
        Glide.with(holder.itemView.getContext()).load(getItem(position).pic).into(holder.ex_image);
    }


    public class ExsVh extends RecyclerView.ViewHolder {
        private final TextView ex_name;
        private final ImageView ex_image;

        public ExsVh(@NonNull View itemView) {
            super(itemView);
            ex_name = itemView.findViewById(R.id.item_ex_name);
            ex_image = itemView.findViewById(R.id.ex_item_image);
            itemView.setOnClickListener(c -> mListener.onClick(getItem(getBindingAdapterPosition())));
        }
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<ExModel>{

        @Override
        public boolean areItemsTheSame(@NonNull ExModel oldItem, @NonNull ExModel newItem) {
            return false; // TODO: 4/12/2021 implement equals
        }

        @Override
        public boolean areContentsTheSame(@NonNull ExModel oldItem, @NonNull ExModel newItem) {
            return false;
        }
    }
}
