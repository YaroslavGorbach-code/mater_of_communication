package com.YaroslavGorbach.delusionalgenerator.workflow;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.WorkflowNavBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.records.RecordsFragment;

public class NavWorkflow extends Fragment implements ExercisesWorkflow.Router{
    public interface Router{
        void openExercise(Exercise.Name name, Exercise.Type type);
        void openTraining();
    }
    private final Fragment mExercisesWorkflow = new ExercisesWorkflow();
    private final Fragment mRecordsFragment = new RecordsFragment();

    public NavWorkflow(){
        super(R.layout.workflow_nav);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.nav_container, mExercisesWorkflow)
                    .setPrimaryNavigationFragment(mExercisesWorkflow)
                    .commit();

            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.nav_container, mRecordsFragment, null)
                    .setPrimaryNavigationFragment(mRecordsFragment)
                    .hide(mRecordsFragment)
                    .commit();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       WorkflowNavBinding binding = WorkflowNavBinding.bind(view);
       binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
           if (binding.bottomNav.getSelectedItemId() != item.getItemId()) {
               switch (item.getItemId()){
                   case R.id.menu_nav_exercises:
                       getChildFragmentManager()
                               .beginTransaction()
                               .hide(mRecordsFragment)
                               .show(mExercisesWorkflow)
                               .commit();
                       break;
                   case R.id.menu_nav_records:
                       getChildFragmentManager()
                               .beginTransaction()
                               .hide(mExercisesWorkflow)
                               .show(mRecordsFragment)
                               .commit();
                       break;
               }

           } else {
               getChildFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
           }
           return true;
       });
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

