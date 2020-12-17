package com.example.adrian.codetest.app.di.module;

import com.example.adrian.codetest.app.di.annotation.PerView;
import com.example.adrian.codetest.app.navigator.Navigator;
import com.example.adrian.codetest.domain.repository.ComicRepository;
import com.example.adrian.codetest.ui.main.presenter.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainViewModule {

  @Provides
  @PerView
  MainPresenter provideMainPresenter(Navigator navigator, ComicRepository comicRepository) {
    return new MainPresenter(navigator, comicRepository);
  }
}
