package com.YaroslavGorbach.delusionalgenerator.screen.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.YaroslavGorbach.delusionalgenerator.R;
import com.YaroslavGorbach.delusionalgenerator.data.Repo;

public class ExercisesFragment extends Fragment {
    Repo repo = new Repo.RepoProvider().provideRepo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        // init list
        RecyclerView list = view.findViewById(R.id.allExList);
        ExercisesVm vm = new ViewModelProvider(this, new ExercisesVm.ExercisesVmFactory(repo)).get(ExercisesVm.class);

            ExsAdapter adapter = new ExsAdapter(exModel -> Navigation.findNavController(view)
                    .navigate(ExercisesFragmentDirections
                                    .actionExercisesFragmentToExercisesDescriptionFragment().setExId(exModel.id)));

            adapter.submitList(vm.getAllExs());
            list.setHasFixedSize(true);
            list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                    false));
            list.setAdapter(adapter);

        return view;
    }
}

