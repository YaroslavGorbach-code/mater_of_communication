package com.YaroslavGorbach.delusionalgenerator.screen.settings;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.oldDataLayer.Repo_SQLite;

public class DialogChooseTheme extends AppCompatDialogFragment {


    public interface ChooseThemesListener {
         void onClickTheme(DialogFragment dialog);
    }

    private ChooseThemesListener listener;

    // Override the Fragment.onAttach() method to instantiate the Listener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            listener = (ChooseThemesListener) context;

        } catch (ClassCastException e) {

            throw new ClassCastException("must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chose_theme,null);

        builder.setView(view)
                .setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

          view.findViewById(R.id.redButton).setOnClickListener(v -> setTheme("red"));

          view.findViewById(R.id.greenButton).setOnClickListener(v -> setTheme("green"));

          view.findViewById(R.id.purpleButton).setOnClickListener(v -> setTheme("purple"));

          view.findViewById(R.id.orangeButton).setOnClickListener(v -> setTheme("orange"));

          view.findViewById(R.id.blueButton).setOnClickListener(v -> setTheme("blue"));

            return builder.create();

    }

    private void setTheme(String color){
        Repo_SQLite.getInstance(getContext()).resetOldThemeState();
        Repo_SQLite.getInstance(getContext()).changeTheme(color);
        listener.onClickTheme(DialogChooseTheme.this);
    }

}




