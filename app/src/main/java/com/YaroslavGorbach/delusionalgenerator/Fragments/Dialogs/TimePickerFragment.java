package com.YaroslavGorbach.delusionalgenerator.Fragments.Dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import com.YaroslavGorbach.delusionalgenerator.Database.Repo;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public static TimePickerFragment newInstance() {
        return new TimePickerFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Repo repo = Repo.getInstance(getContext());
        String hour = String.valueOf(hourOfDay);
        String min = String.valueOf(minute);
        if (hourOfDay < 10){
            hour = "0"+ hourOfDay;
        }
        if (minute < 10){
            min = "0"+ minute;
        }
        repo.setNotificationTime(hour, min);
    }
}
