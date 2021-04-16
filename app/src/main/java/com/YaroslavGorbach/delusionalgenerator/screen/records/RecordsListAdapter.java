package com.YaroslavGorbach.delusionalgenerator.screen.records;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.util.Time;

import java.io.File;

public class RecordsListAdapter extends RecyclerView.Adapter<RecordsListAdapter.AudioViewHolder> {
    private final File[] allFiles;
    private Time time;
    private final Listener Listener;

    public interface Listener {
        void onPlay(File file);
    }

    public RecordsListAdapter(File[] allFiles, Listener Listener) {
        this.allFiles = allFiles;
        this.Listener = Listener;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_i, parent, false);
        time = new Time();
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        holder.bind(position);
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

        private final TextView title;
        private final TextView date;
        private final TextView duration;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_title);
            date = itemView.findViewById(R.id.list_date);
            duration = itemView.findViewById(R.id.file_duration);

            itemView.setOnClickListener(c ->{
                Listener.onPlay(allFiles[getAdapterPosition()]);
            });

        }

        private void bind(int position) {
            title.setText(allFiles[position].getName());
            date.setText(time.getTimeAgo(allFiles[position].lastModified()));
            duration.setText(time.getFileDuration(allFiles[position]));
        }
    }
}
