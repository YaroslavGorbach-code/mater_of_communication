package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.BuildConfig;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo_SQLite;
import com.YaroslavGorbach.delusionalgenerator.ViewModels.SettingsFragmentViewModel;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogAboutApp;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogChooseTheme;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.TimePickerFragment;
import com.YaroslavGorbach.delusionalgenerator.R;

public class
SettingsFragment extends Fragment {

    private ConstraintLayout mNotifications;
    private ConstraintLayout mConnection;
    private ConstraintLayout mRate;
    private ConstraintLayout mShare;
    private ConstraintLayout mAboutApp;
    private ConstraintLayout mThemes;
    private AppCompatCheckBox mCheckBox;
    private TextView mTimePicker;
    private Repo_SQLite mRepoSQLite;

    private SettingsFragmentViewModel mViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mNotifications = view.findViewById(R.id.notifications);
        mConnection = view.findViewById(R.id.connection);
        mRate = view.findViewById(R.id.rate);
        mShare = view.findViewById(R.id.share);
        mAboutApp = view.findViewById(R.id.aboutApp);
        mThemes = view.findViewById(R.id.themes);
        mCheckBox = view.findViewById(R.id.notificationCheckBox);
        mTimePicker = view.findViewById(R.id.timePiker);
        mRepoSQLite = Repo_SQLite.getInstance(getContext());
        mCheckBox.setChecked(mRepoSQLite.getNotificationState());
        mViewModel = new ViewModelProvider(this).get(SettingsFragmentViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setNotifyTime();
        mRepoSQLite.addListener(this::setNotifyTime);

        mTimePicker.setOnClickListener(v->{
            TimePickerFragment dialog =  TimePickerFragment.newInstance();
            dialog.show(getParentFragmentManager(), "timePicker");
            mCheckBox.setChecked(false);
        });

        mCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mViewModel.setNotification();
            } else {
                mViewModel.cancelNotification();
            }
        });

        mThemes.setOnClickListener(v -> {
            DialogChooseTheme dialog = new DialogChooseTheme();
            dialog.show(getParentFragmentManager(),"themes");
        });

        mNotifications.setOnClickListener(v->{
            mCheckBox.setChecked(!mCheckBox.isChecked());
        });
        mConnection.setOnClickListener(v->{
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "yaroslavgorbach2@gmail.com", null));
            startActivity(Intent.createChooser(emailIntent, null));
        });
        mRate.setOnClickListener(v->{
            Uri uriUrl = Uri.parse("https://play.google.com/store/apps/details?id=com.YaroslavGorbach.delusionalgenerator");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        });
        mShare.setOnClickListener(v->{
            Intent sendIntent = new Intent();
                   sendIntent.setAction(Intent.ACTION_SEND);
                   sendIntent.putExtra(Intent.EXTRA_TEXT,
                           "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                   sendIntent.setType("text/plain");
                   startActivity(sendIntent);
        });
        mAboutApp.setOnClickListener(v->{
            DialogAboutApp dialog2 = new DialogAboutApp();
                   dialog2.show(getParentFragmentManager(),"AboutApp");
        });
    }

    private void setNotifyTime() {
        mTimePicker.setText(mRepoSQLite.getNotifyHourAndMinute());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRepoSQLite.removeListener(this::setNotifyTime);
    }
}