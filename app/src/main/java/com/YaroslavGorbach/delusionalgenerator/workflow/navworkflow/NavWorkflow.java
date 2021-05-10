package com.YaroslavGorbach.delusionalgenerator.workflow.navworkflow;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.feature.billing.BillingManager;
import com.YaroslavGorbach.delusionalgenerator.feature.billing.BillingManagerImp;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.WorkflowNavBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManager;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManagerImp;
import com.YaroslavGorbach.delusionalgenerator.screen.records.RecordsFragment;
import com.YaroslavGorbach.delusionalgenerator.workflow.ExercisesWorkflow;
import com.YaroslavGorbach.delusionalgenerator.workflow.NotificationDialog;

import java.util.Calendar;

public class NavWorkflow extends Fragment implements ExercisesWorkflow.Router, NotificationDialog.Host {

    public interface Router{
        void openExercise(Exercise.Name name, Exercise.Type type);
        void openTraining();
    }

    public NavWorkflow(){
        super(R.layout.workflow_nav);
    }

    private NavWorkflowVm mVm;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
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
        mVm = new ViewModelProvider(this, new NavWorkflowVm.NavWorkflowVmFactory(
                repo, billingManager, myNotificationManager)).get(NavWorkflowVm.class);
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       WorkflowNavBinding binding = WorkflowNavBinding.bind(view);
       binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
           if (binding.bottomNav.getSelectedItemId() != item.getItemId()) {
               Fragment fragment = new ExercisesWorkflow();

               switch (item.getItemId()){
                   case R.id.menu_nav_exercises:
                       fragment = new ExercisesWorkflow();
                       binding.toolbar.setTitle(getString(R.string.title_exercises));
                       break;
                   case R.id.menu_nav_records:
                       fragment = new RecordsFragment();
                       binding.toolbar.setTitle(getString(R.string.title_records));
                       break;
               }
               getChildFragmentManager()
                       .beginTransaction()
                       .replace(R.id.nav_container, fragment)
                       .setPrimaryNavigationFragment(fragment)
                       .commit();
           } else {
               getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
           }
           return true;
       });

       binding.toolbar.setOnMenuItemClickListener(menuItem-> {
           if (menuItem.getItemId() == R.id.menu_toolbar_notifications) {
               mVm.showNotificationDialog(getChildFragmentManager());
           }
           if (menuItem.getItemId() == R.id.menu_toolbar_them){
               mVm.changeNightMod(requireActivity());
           }
           if (menuItem.getItemId() == R.id.menu_toolbar_remove_ad){
                mVm.billingManager.showPurchasesDialog();
           }
           return true;
       });
       if (!mVm.getAdIsAllow()){
           binding.toolbar.getMenu().removeItem(R.id.menu_toolbar_remove_ad);
       }
    }

    @Override
    public void onResume() {
        super.onResume();
        mVm.billingManager.queryPurchases(isAdRemoved -> {
            if (isAdRemoved && mVm.getAdIsAllow()){
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
        ((Router)requireActivity()).openExercise(name, type);
    }

    @Override
    public void openTraining() {
        ((Router)requireActivity()).openTraining();
    }

}

