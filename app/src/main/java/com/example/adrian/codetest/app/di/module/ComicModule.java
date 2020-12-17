package com.example.adrian.codetest.app.di.module;

import com.example.adrian.codetest.app.navigator.Navigator;
import com.example.adrian.codetest.ui.comic.presenter.ComicPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ComicModule {

  @Provides
  public ComicPresenter provideComicPresenter(Navigator navigator) {
    return new ComicPresenter(navigator);
  }
}
