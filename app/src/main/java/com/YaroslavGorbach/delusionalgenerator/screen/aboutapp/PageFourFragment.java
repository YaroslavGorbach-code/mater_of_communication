package com.YaroslavGorbach.delusionalgenerator.screen.aboutapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentPageFourBinding;

public class PageFourFragment extends Fragment {
    public PageFourFragment(){
        super(R.layout.fragment_page_four);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentPageFourBinding.bind(view).start.setOnClickListener(v -> requireActivity().onBackPressed());
    }
}
