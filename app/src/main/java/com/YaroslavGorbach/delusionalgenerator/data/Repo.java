package com.YaroslavGorbach.delusionalgenerator.data;

import java.util.List;

public interface Repo {
    List<ExModel> getExercises();

      class RepoProvider{
        public RepoImp provideRepo(){
            return new RepoImp();
        }
    }
}
