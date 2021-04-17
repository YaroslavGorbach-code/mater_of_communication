package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class ExercisesFragment extends Fragment {
    Repo repo = new Repo.RepoProvider().provideRepo();

    public ExercisesFragment(){ super(R.layout.fragment_exercises); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init list
        RecyclerView list = view.findViewById(R.id.exs_list);
        ExercisesVm vm = new ViewModelProvider(this, new ExercisesVm.ExercisesVmFactory(repo)).get(ExercisesVm.class);

        ExsAdapter adapter = new ExsAdapter(exModel -> Navigation.findNavController(view)
                .navigate(ExercisesFragmentDirections
                        .actionExercisesFragmentToExercisesDescriptionFragment().setExId(exModel.id)));

        adapter.submitList(vm.getAllExs());
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false));
        list.setAdapter(adapter);
    }
}

