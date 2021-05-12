package com.YaroslavGorbach.delusionalgenerator.di;

import com.YaroslavGorbach.delusionalgenerator.data.RepoProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = RepoProvider.class)
public interface AppComponent extends RepoProvider {
    @Component.Factory
    interface Factory {
        AppComponent create(RepoProvider repoProvider);
    }
}
