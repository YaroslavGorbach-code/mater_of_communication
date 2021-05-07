package com.YaroslavGorbach.delusionalgenerator.screen.exercises.bycategory;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Exercise;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;
import com.YaroslavGorbach.delusionalgenerator.databinding.FragmentByCategoryBinding;
import com.YaroslavGorbach.delusionalgenerator.screen.exercises.ExercisesVm;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Exercise.Category category = (Exercise.Category)requireArguments().getSerializable("category");

        // init vm
        ExercisesVm vm = new ViewModelProvider(this,
                new ExercisesVm.ExercisesVmFactory(new Repo.RepoProvider().provideRepo(requireContext()))).get(ExercisesVm.class);

        // init view
        ByCategoryView v = new ByCategoryView(FragmentByCategoryBinding.bind(view), exercise ->
                ((Router)requireParentFragment()).openExercise(exercise.getName(), exercise.type));

        v.setExercises(vm.exercises.getExercises(category));

    }
}
