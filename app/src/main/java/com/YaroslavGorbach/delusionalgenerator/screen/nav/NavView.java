package com.YaroslavGorbach.delusionalgenerator.screen.nav;

import androidx.fragment.app.Fragment;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentNavBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.records.RecordsFragment;
import com.YaroslavGorbach.delusionalgenerator.workflow.ExercisesWorkflow;

public class NavView {

    interface Callback {
        void onNavItem(Fragment fragment);
        void onNavSameItem();
        void onMenuItem(int itemId);
    }

    private final FragmentNavBinding mBinding;

    public NavView(FragmentNavBinding binding, Callback callback) {
        mBinding = binding;
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            if (binding.bottomNav.getSelectedItemId() != item.getItemId()) {
                if (item.getItemId() == R.id.menu_nav_exercises) {
                    callback.onNavItem(new ExercisesWorkflow());
                    binding.toolbar.setTitle(binding.getRoot().getContext().getString(R.string.title_exercises));
                }
                if (item.getItemId() == R.id.menu_nav_records) {
                    callback.onNavItem(new RecordsFragment());
                    binding.toolbar.setTitle(binding.getRoot().getContext().getString(R.string.title_records));
                }
            } else {
                callback.onNavSameItem();
            }
            return true;
        });

        binding.toolbar.setOnMenuItemClickListener(item -> {
            callback.onMenuItem(item.getItemId());
            return true;
        });
    }

    void removeAdMenuItem() {
        mBinding.toolbar.getMenu().removeItem(R.id.menu_toolbar_remove_ad);
    }

}
