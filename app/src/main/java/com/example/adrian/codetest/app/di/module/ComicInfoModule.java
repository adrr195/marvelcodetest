package com.example.adrian.codetest.app.di.module;

import com.example.adrian.codetest.domain.repository.ComicRepository;
import com.example.adrian.codetest.ui.comicinfo.presenter.ComicInfoPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ComicInfoModule {

  @Provides
  public ComicInfoPresenter provideComicInfoPresenter(ComicRepository comicRepository) {
    return new ComicInfoPresenter(comicRepository);
  }
}
