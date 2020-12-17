package com.example.adrian.codetest.app.di.module;

import com.example.adrian.codetest.app.executor.interactor.InteractorExecutor;
import com.example.adrian.codetest.app.executor.main.MainThreadExecutor;
import com.example.adrian.codetest.domain.interactor.comics.GetComicsInteractor;
import com.example.adrian.codetest.domain.repository.ComicRepository;
import com.example.adrian.codetest.ui.comics.presenter.ComicsPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ComicsModule {

  @Provides
  public GetComicsInteractor provideGetComicsInteractor(MainThreadExecutor mainThreadExecutor,
      InteractorExecutor interactorExecutor, ComicRepository comicRepository) {
    return new GetComicsInteractor(mainThreadExecutor, interactorExecutor, comicRepository);
  }

  @Provides
  public ComicsPresenter provideComicsPresenter(GetComicsInteractor getComicsInteractor,
      ComicRepository comicRepository) {
    return new ComicsPresenter(getComicsInteractor, comicRepository);
  }
}
