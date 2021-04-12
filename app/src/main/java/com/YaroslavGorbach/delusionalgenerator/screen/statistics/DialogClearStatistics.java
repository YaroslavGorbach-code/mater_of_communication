package com.YaroslavGorbach.delusionalgenerator.screen.statistics;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.YaroslavGorbach.delusionalgenerator.data.Repo_SQLite;

public class DialogClearStatistics extends AppCompatDialogFragment {

    private static final String ARG_EX_ID = "ARG_EX_ID";

    public static DialogClearStatistics crete(int idEx){

        Bundle args = new Bundle();
        args.putInt(ARG_EX_ID, idEx);
        DialogClearStatistics dialog = new DialogClearStatistics();
        dialog.setArguments(args);
        return dialog;

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder(requireContext()).setTitle("Вы действительно хотите очитить статистику?")
                .setPositiveButton("Да", (dialog, which) ->
                        Repo_SQLite.getInstance(getContext()).clearStatistic(getArguments().getInt(ARG_EX_ID)))
                .setNegativeButton("Нет",null)
                .create();
    }
}
