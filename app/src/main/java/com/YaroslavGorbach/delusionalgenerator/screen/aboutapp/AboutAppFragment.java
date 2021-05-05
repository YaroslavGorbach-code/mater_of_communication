package com.YaroslavGorbach.delusionalgenerator.screen.aboutapp;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentAboutAppBinding;

public class AboutAppFragment extends Fragment {
    public AboutAppFragment(){
        super(R.layout.fragment_about_app);
    }
    private static final int NUM_PAGES = 4;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentAboutAppBinding binding = FragmentAboutAppBinding.bind(view);
        binding.toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());

        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
        binding.pager.setAdapter(adapter);
    }

    private static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new PageOneFragment();
                case 1:
                    return new PageTwoFragment();
                case 2:
                    return new PageThreeFragment();
                case 3:
                    return new PageFourFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


}
