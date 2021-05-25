package com.YaroslavGorbach.delusionalgenerator.screen.exercises.bycategory;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.component.exercises.Exercises;
import com.YaroslavGorbach.delusionalgenerator.data.domain.Exercise;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentByCategoryBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.ExercisesVm;

import javax.inject.Inject;

public class ByCategoryFragment extends Fragment {
    public interface Router{
        void openExercise(Exercise.Name name, Exercise.Type type);
    }

    public ByCategoryFragment(){
        super(R.layout.fragment_by_category);
    }

    public static Bundle argsOf(Exercise.Category category){
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", category);
        return bundle;
    }

    @Inject Exercises exercises;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Exercise.Category category = (Exercise.Category)requireArguments().getSerializable("category");

        ExercisesVm vm = new ViewModelProvider(this).get(ExercisesVm.class);
        vm.exercisesComponent.inject(this);

        // init view
        ByCategoryView v = new ByCategoryView(FragmentByCategoryBinding.bind(view), exercise ->
                ((Router)requireParentFragment()).openExercise(exercise.getName(), Exercise.Type.COMMON));

        v.setExercises(exercises.getExercises(category));

    }
}
