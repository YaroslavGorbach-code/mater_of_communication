package com.YaroslavGorbach.delusionalgenerator.screen.dailyTraining;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.DailyTrainingEx;

public class DailyTrainingExsAdapter extends ListAdapter<DailyTrainingEx, DailyTrainingExsAdapter.Vh> {

    public DailyTrainingExsAdapter() {
        super(new DiffCallback());
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Vh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_training_ex, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.bind(getItem(position));
    }

    public static class Vh extends RecyclerView.ViewHolder {

        public Vh(View itemView) {
            super(itemView);
        }

        void bind(DailyTrainingEx ex){

        }
    }

    public static class DiffCallback extends DiffUtil.ItemCallback<DailyTrainingEx>{

        @Override
        public boolean areItemsTheSame(@NonNull DailyTrainingEx oldItem, @NonNull DailyTrainingEx newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DailyTrainingEx oldItem, @NonNull DailyTrainingEx newItem) {
            return false;
        }
    }
}
