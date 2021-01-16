package com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.YaroslavGorbach.delusionalgenerator.R;

public class DialogDeleteRecords  extends AppCompatDialogFragment {

        public interface DeleteRecordsListener{
            void onClickDelete();
        }
        private DeleteRecordsListener mListener;


        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            try {
                mListener = (DeleteRecordsListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException("must implement Listener");
            }
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());


            TextView textView = new TextView(getContext());
            textView.setText("Удалить все записи ?");
            textView.setTextSize(20F);
            textView.setPadding(40,40,40,20);
            builder
                    .setCustomTitle(textView)
                    .setPositiveButton("Да", (dialog, which) ->{
                        mListener.onClickDelete();
                        this.dismiss();
                    })
                    .setNegativeButton("Нет", (dialog, which) -> {
                    });
            return builder.create();
        }
    }


