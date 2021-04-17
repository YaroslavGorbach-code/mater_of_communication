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
    interface Listener{
        void onClick(ExModel exModel);
    }

    private final Listener mListener;

    protected ExsAdapter(Listener listener) {
        super(new DiffCallback());
        mListener = listener;
    }


    @NonNull
    @Override
    public ExsVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ExsVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.excersice_i,
               parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExsVh holder, int position) {
        holder.bind(getItem(position));
    }


    public class ExsVh extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView category;
        private final ImageView image;

        public ExsVh(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ex_name);
            image = itemView.findViewById(R.id.ex_item_image);
            category = itemView.findViewById(R.id.ex_category);
            itemView.setOnClickListener(c -> mListener.onClick(getItem(getBindingAdapterPosition())));
        }

        public void bind(ExModel item) {
            name.setText(item.name.getName());
            category.setText(item.category.getName());
            Glide.with(itemView).load(item.pic).into(image);
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
