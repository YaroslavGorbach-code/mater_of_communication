package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.component.vocabularyEx.VocabularyEx;
import com.YaroslavGorbach.delusionalgenerator.databinding.DialogVocabularyResultBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class FinishDialog extends DialogFragment {
    // TODO: 4/17/2021 translate

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        VocabularyEx.Result result = FinishDialogArgs.fromBundle(requireArguments()).getResult();
        DialogVocabularyResultBinding binding
                = DialogVocabularyResultBinding.inflate(LayoutInflater.from(requireContext()));
        initResult(binding, result);
        return new MaterialAlertDialogBuilder(requireContext())
                .setView(binding.getRoot())
                .setPositiveButton("OK", null)
                .create();
    }

    public void initResult(DialogVocabularyResultBinding binding, VocabularyEx.Result result){
        switch (result){
            case GOOD:
                binding.resultText.setText(getString(R.string.resultGood, result.getNumber())
                        .replaceFirst(" ", ""));
                binding.imageView.setImageResource(R.drawable.ic_good_result);
                break;
            case BAD:
                binding.resultText.setText(getString(R.string.resultBad, result.getNumber())
                        .replaceFirst(" ", ""));
                binding.imageView.setImageResource(R.drawable.ic_bad_result);
                break;
            case VERY_GOOD:
                binding.resultText.setText(getString(R.string.resultVeryGood, result.getNumber())
                        .replaceFirst(" ", ""));
                binding.imageView.setImageResource(R.drawable.ic_very_goot_result);
                break;
        }
    }
}
