package com.YaroslavGorbach.delusionalgenerator.workflow.navworkflow;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.DialogNotificationBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Calendar;

public class NotificationDialog extends DialogFragment {
    public interface Host{
        void onNotificationApply(boolean checkBox, String text, Calendar calendar);
    }

    public static Bundle argsOf(int hour, int minute, String text, boolean isAllowed) {
        Bundle bundle = new Bundle();
        bundle.putInt("hour", hour);
        bundle.putInt("minute", minute);
        bundle.putString("text", text);
        bundle.putBoolean("isAllow", isAllowed);
        return bundle;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DialogNotificationBinding binding = DialogNotificationBinding.inflate(LayoutInflater.from(requireContext()));
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, requireArguments().getInt("hour"));
        c.set(Calendar.MINUTE, requireArguments().getInt("minute"));
        boolean isAllow = requireArguments().getBoolean("isAllow");
        String text  = requireArguments().getString("text");

        binding.time.setText(c.get(Calendar.HOUR_OF_DAY) +":"+ c.get(Calendar.MINUTE));
        binding.checkBox.setChecked(isAllow);
        binding.text.setText(text);
        TimePickerDialog timePicker = new TimePickerDialog(requireContext(), (view, h, m) -> {
            binding.time.setText(h +":"+ m);
            c.set(Calendar.HOUR_OF_DAY, h);
            c.set(Calendar.MINUTE, m);
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),  DateFormat.is24HourFormat(getActivity()));

        binding.time.setOnClickListener(v -> {
            timePicker.show();
        });

        return new MaterialAlertDialogBuilder(requireContext())
                .setView(binding.getRoot())
                .setPositiveButton(getString(R.string.apply), (dialog, which) -> {
                   ((Host)getParentFragment()).onNotificationApply(binding.checkBox.isChecked(), binding.text.getText().toString(), c);
                })
                .create();
    }

}
