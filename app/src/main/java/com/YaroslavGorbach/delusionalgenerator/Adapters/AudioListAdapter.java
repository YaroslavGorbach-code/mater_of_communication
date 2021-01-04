package com.YaroslavGorbach.delusionalgenerator.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Helpers.TimeAgo;

import java.io.File;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AudioViewHolder> {
    private File[] allFiles;
    private TimeAgo timeAgo;
    private onItemListClick onItemListClick;

    public interface onItemListClick {
        void onClickListener(File file, int position);
    }

    public AudioListAdapter(File[] allFiles, onItemListClick onItemListClick) {
        this.allFiles = allFiles;
        this.onItemListClick = onItemListClick;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        timeAgo = new TimeAgo();
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        holder.list_title.setText(allFiles[position].getName());
        holder.list_date.setText(timeAgo.getTimeAgo(allFiles[position].lastModified()));
    }

    @Override
    public int getItemCount() {
        if (allFiles!=null){
            return allFiles.length;
        }else {
            return 0;
        }
    }


    public class AudioViewHolder extends RecyclerView.ViewHolder {

        private TextView list_title;
        private TextView list_date;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            list_title = itemView.findViewById(R.id.list_title);
            list_date = itemView.findViewById(R.id.list_date);

            itemView.setOnClickListener(c ->{
                onItemListClick.onClickListener(allFiles[getAdapterPosition()], getAdapterPosition());
            });

        }


    }

}
