package com.YaroslavGorbach.delusionalgenerator.screen.exercise.vocabulary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.component.vocabulary.Vocabulary;
import com.YaroslavGorbach.delusionalgenerator.databinding.DialogVocabularyResultBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class FinishDialog extends DialogFragment {
    public static Bundle argsOf(Vocabulary.Result result) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("result", result);
        return bundle;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Vocabulary.Result result = (Vocabulary.Result) requireArguments().getSerializable("result");
        DialogVocabularyResultBinding binding
                = DialogVocabularyResultBinding.inflate(LayoutInflater.from(requireContext()));
        initResult(binding, result);
        return new MaterialAlertDialogBuilder(requireContext())
                .setView(binding.getRoot())
                .setPositiveButton(getString(R.string.close), (dialog, which) -> {
                  onCancel(dialog);
                })
                .create();
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        requireActivity().onBackPressed();
    }

    private void initResult(DialogVocabularyResultBinding binding, Vocabulary.Result result){
        switch (result){
            case GOOD:
                binding.resultText.setText(getString(R.string.resultGood, result.getNumberWords()));
                binding.imageView.setImageResource(R.drawable.ic_good_result);
                break;
            case BAD:
                binding.resultText.setText(getString(R.string.resultBad, result.getNumberWords()));
                binding.imageView.setImageResource(R.drawable.ic_bad_result);
                break;
            case VERY_GOOD:
                binding.resultText.setText(getString(R.string.resultVeryGood, result.getNumberWords()));
                binding.imageView.setImageResource(R.drawable.ic_very_goot_result);
                break;
        }
    }
}
