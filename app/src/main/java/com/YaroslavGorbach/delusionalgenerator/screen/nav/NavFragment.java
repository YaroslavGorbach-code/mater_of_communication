package com.YaroslavGorbach.delusionalgenerator.screen.nav;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentNavBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.billing.BillingManager;
import com.YaroslavGorbach.delusionalgenerator.feature.billing.BillingManagerImp;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManager;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManagerImp;
import com.YaroslavGorbach.delusionalgenerator.screen.records.RecordsFragment;
import com.YaroslavGorbach.delusionalgenerator.workflow.ExercisesWorkflow;

import java.util.Calendar;

public class NavFragment extends Fragment implements ExercisesWorkflow.Router, NotificationDialog.Host {

    public interface Router {
        void openExercise(Exercise.Name name, Exercise.Type type);
        void openTraining();
    }

    public NavFragment() {
        super(R.layout.fragment_nav);
    }

    private NavVm mVm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Fragment fragment = new ExercisesWorkflow();
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.nav_container, fragment)
                    .setPrimaryNavigationFragment(fragment)
                    .commit();
        }
        Repo repo = new Repo.RepoProvider().provideRepo(requireContext());
        BillingManager billingManager = new BillingManagerImp(requireActivity());
        MyNotificationManager myNotificationManager = new MyNotificationManagerImp();
        mVm = new ViewModelProvider(this, new NavVm.NavWorkflowVmFactory(
                repo, billingManager, myNotificationManager)).get(NavVm.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                    mVm.showNotificationDialog(getChildFragmentManager());
                }
                if (itemId == R.id.menu_toolbar_them) {
                    mVm.changeNightMod(requireActivity());
                }
                if (itemId == R.id.menu_toolbar_remove_ad) {
                    mVm.billingManager.showPurchasesDialog();
                }
            }
        });

        if (!mVm.getAdIsAllow()) {
            v.removeRemoveAdMenuItem();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mVm.billingManager.queryPurchases(isAdRemoved -> {
            if (isAdRemoved && mVm.getAdIsAllow()) {
                mVm.disallowAd();
                requireActivity().recreate();
            }
        });
    }

    @Override
    public void onNotificationApply(boolean isAllow, String text, Calendar calendar) {
        mVm.setNotification(isAllow, text, calendar, requireContext());
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

