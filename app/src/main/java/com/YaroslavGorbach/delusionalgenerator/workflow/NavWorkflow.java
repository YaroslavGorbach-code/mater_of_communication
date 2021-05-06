package com.YaroslavGorbach.delusionalgenerator.workflow;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.WorkflowNavBinding;
import com.YaroslavGorbach.delusionalgenerator.feature.notifycation.MyNotificationManagerImp;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.NotificationDialog;
import com.YaroslavGorbach.delusionalgenerator.screen.records.RecordsFragment;

import java.util.Calendar;

public class NavWorkflow extends Fragment implements ExercisesWorkflow.Router, NotificationDialog.Host{

    public interface Router{
        void openExercise(Exercise.Name name, Exercise.Type type);
        void openTraining();
    }

    public NavWorkflow(){
        super(R.layout.workflow_nav);
    }

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

           if (menuItem.getItemId() == R.id.menu_toolber_notifications) {
               NotificationDialog dialog =  new NotificationDialog();
               dialog.setArguments(NotificationDialog.argsOf(12,10, "i`am here", true));
               dialog.show(getChildFragmentManager(), null);
           }
           return true;
       });
    }

    @Override
    public void onApply(boolean checkBox, String text, Calendar calendar) {
        NotificationManager notificationManager = ContextCompat.getSystemService(requireContext(), NotificationManager.class);
        new MyNotificationManagerImp().sendNotificationOnTime(notificationManager, requireContext(),calendar.getTimeInMillis(), text);
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

