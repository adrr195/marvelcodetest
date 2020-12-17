package com.example.adrian.codetest.app.di.component.application;

import com.example.adrian.codetest.app.MainApplication;
import com.example.adrian.codetest.app.di.module.ApplicationModule;
import com.example.adrian.codetest.app.di.module.DataSourceModule;
import com.example.adrian.codetest.app.di.module.RepositoryModule;
import com.example.adrian.codetest.app.executor.interactor.InteractorExecutor;
import com.example.adrian.codetest.app.executor.main.MainThreadExecutor;
import com.example.adrian.codetest.app.navigator.Navigator;
import com.example.adrian.codetest.domain.repository.ComicRepository;
import dagger.Component;
import javax.inject.Singleton;

/**
 * The application component provide common dependencies to all sub-components
 */
@Singleton
@Component(modules = { ApplicationModule.class, RepositoryModule.class, DataSourceModule.class })
public interface ApplicationComponent {

  void inject(MainApplication mainApplication);

  MainThreadExecutor mainThreadExecutor();

  InteractorExecutor interactorExecutor();

  Navigator navigator();

  ComicRepository comicRepository();
}
