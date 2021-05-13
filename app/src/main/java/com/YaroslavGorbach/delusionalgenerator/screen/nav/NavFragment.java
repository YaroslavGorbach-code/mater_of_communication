package com.YaroslavGorbach.delusionalgenerator.screen.nav;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.component.navmenu.NavMenu;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentNavBinding;
import com.YaroslavGorbach.delusionalgenerator.workflow.ExercisesWorkflow;

import java.util.Calendar;

import javax.inject.Inject;

public class NavFragment extends Fragment implements ExercisesWorkflow.Router, NotificationDialog.Host {

    public interface Router {
        void openExercise(Exercise.Name name, Exercise.Type type);
        void openTraining();
    }

    public NavFragment() {
        super(R.layout.fragment_nav);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            ExercisesWorkflow fragment = new ExercisesWorkflow();
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_container, fragment)
                    .setPrimaryNavigationFragment(fragment)
                    .commit();
        }
    }

    @Inject NavMenu navMenu;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init vm
        NavVm vm  = new ViewModelProvider(this).get(NavVm.class);
        vm.getNavComponent(requireActivity()).inject(this);

        // init view
        NavView v = new NavView(FragmentNavBinding.bind(view), new NavView.Callback() {

            @Override
            public void onNavItem(Fragment fragment) {
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_container, fragment)
                        .setPrimaryNavigationFragment(fragment)
                        .commit();
            }

            @Override
            public void onNavSameItem() {
                getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            @Override
            public void onMenuItem(int itemId) {
                if (itemId == R.id.menu_toolbar_notifications) {
                    navMenu.showNotificationDialog(getChildFragmentManager());
                }
                if (itemId == R.id.menu_toolbar_them) {
                    navMenu.changeNightMod(requireActivity());
                }
                if (itemId == R.id.menu_toolbar_remove_ad) {
                    navMenu.showPurchasesDialog(requireActivity());
                }
            }
        });

        if (navMenu.getAdMenuItemAllow()) {
            v.removeAdMenuItem();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        navMenu.queryPurchases(requireActivity());
    }

    @Override
    public void onNotificationApply(boolean isAllow, String text, Calendar calendar) {
        navMenu.onNotificationApply(isAllow, text, calendar, requireContext());
    }

    @Override
    public void openExercise(Exercise.Name name, Exercise.Type type) {
        ((Router) requireActivity()).openExercise(name, type);
    }

    @Override
    public void openTraining() {
        ((Router) requireActivity()).openTraining();
    }

}

