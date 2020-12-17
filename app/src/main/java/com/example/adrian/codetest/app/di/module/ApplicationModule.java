package com.example.adrian.codetest.app.di.module;

import android.content.Context;
import com.example.adrian.codetest.app.MainApplication;
import com.example.adrian.codetest.app.executor.interactor.DefaultInteractorExecutor;
import com.example.adrian.codetest.app.executor.interactor.InteractorExecutor;
import com.example.adrian.codetest.app.executor.main.MainThreadExecutor;
import com.example.adrian.codetest.app.executor.main.MainThreadExecutorImp;
import com.example.adrian.codetest.app.navigator.ApplicationNavigator;
import com.example.adrian.codetest.app.navigator.Navigator;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * The application module is the base module, is create on the Application start,
 * and the context is passed on the constructor, made it available for all modules
 *
 */
@Module public class ApplicationModule {

  private final MainApplication application;

  public ApplicationModule(MainApplication mainApplication) {
    this.application = mainApplication;
  }

  @Provides @Singleton public MainThreadExecutor provideMainThreadExecutor() {
    return new MainThreadExecutorImp();
  }

  @Provides @Singleton public InteractorExecutor provideInteractorExecutor() {
    return new DefaultInteractorExecutor();
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton Navigator provideNavigator() {
    return new ApplicationNavigator();
  }
}
