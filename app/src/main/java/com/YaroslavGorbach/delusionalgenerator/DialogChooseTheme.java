package com.YaroslavGorbach.delusionalgenerator;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogChooseTheme extends AppCompatDialogFragment {
    private Button mRedButton;
    private Button mGreenButton;
    private Button mPurpleButton;
    private Button mOrandeButton;
    private Button mBlueButton;





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

        view.findViewById(R.id.redButton).setOnClickListener(v -> {
                    Repo.getInstance(view.getContext()).resetOldThemeState();
                   Repo.getInstance(view.getContext()).changeTheme("red");
                });

          view.findViewById(R.id.greenButton).setOnClickListener(v -> {
              Repo.getInstance(view.getContext()).resetOldThemeState();
              Repo.getInstance(view.getContext()).changeTheme("green");
          });

          view.findViewById(R.id.purpleButton).setOnClickListener(v -> {
              Repo.getInstance(view.getContext()).resetOldThemeState();
              Repo.getInstance(view.getContext()).changeTheme("purple");
          });

          view.findViewById(R.id.orangeButton).setOnClickListener(v -> {
              Repo.getInstance(view.getContext()).resetOldThemeState();
              Repo.getInstance(view.getContext()).changeTheme("orange");
          });

          view.findViewById(R.id.blueButton).setOnClickListener(v -> {
              Repo.getInstance(view.getContext()).resetOldThemeState();
              Repo.getInstance(view.getContext()).changeTheme("blue");
          });


        return builder.create();

    }


}




