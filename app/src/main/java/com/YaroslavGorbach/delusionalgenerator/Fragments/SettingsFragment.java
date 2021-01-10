package com.YaroslavGorbach.delusionalgenerator.Fragments;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.YaroslavGorbach.delusionalgenerator.BuildConfig;
import com.YaroslavGorbach.delusionalgenerator.Database.Repo;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogAboutApp;
import com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs.DialogChooseTheme;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.Helpers.ReminderBroadcast;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    private ConstraintLayout mNotifications;
    private ConstraintLayout mConnection;
    private ConstraintLayout mRate;
    private ConstraintLayout mShare;
    private ConstraintLayout mAboutApp;
    private ConstraintLayout mThemes;
    private AppCompatCheckBox mCheckBox;
    private TextView mTimePicker;
    private Repo mRepo;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private PendingIntent mPendingIntent;
    private AlarmManager mAlarmManager;
    private Intent mReminderIntent;



    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        MaterialToolbar toolbar = getActivity().findViewById(R.id.toolbar_main_a);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.getMenu().getItem(0).setVisible(false);
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        createNotificationChannel();
        mAlarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
        mReminderIntent = new Intent(getContext(), ReminderBroadcast.class);
        mPendingIntent = PendingIntent.getBroadcast(getContext(), 1, mReminderIntent, 0);
        mNotifications = view.findViewById(R.id.notifications);
        mConnection = view.findViewById(R.id.connection);
        mRate = view.findViewById(R.id.rate);
        mShare = view.findViewById(R.id.share);
        mAboutApp = view.findViewById(R.id.aboutApp);
        mThemes = view.findViewById(R.id.themes);
        mCheckBox = view.findViewById(R.id.notificationCheckBox);
        mTimePicker = view.findViewById(R.id.timePiker);
        mRepo = Repo.getInstance(getContext());
        mCheckBox.setChecked(mRepo.getNotificationState());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setNotifyTime();
        mRepo.addListener(this::setNotifyTime);

        mTimePicker.setOnClickListener(v->{
            TimePickerFragment.newInstance()
                    .show(getParentFragmentManager(), "timePicker");
            mCheckBox.setChecked(false);
        });



        mCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(mRepo.getNotifyHour()));
                calendar.set(Calendar.MINUTE, Integer.parseInt(mRepo.getNotifyMinute()));
                setAlarm(calendar, mPendingIntent);
                mRepo.changeNotificationState(1);

            } else {
                mAlarmManager.cancel(mPendingIntent);
                mRepo.changeNotificationState(0);
            }
        });

        mThemes.setOnClickListener(v -> {
            new DialogChooseTheme().show(getParentFragmentManager(),"themes");
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
        mTimePicker.setText(mRepo.getNotifyHour() + ":" + mRepo.getNotifyMinute());
    }

    private void setAlarm(Calendar calendar, PendingIntent pendingIntent) {
        assert mAlarmManager != null;
        mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY , pendingIntent);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRepo.removeListener(this::setNotifyTime);
    }
}