package com.example.adrian.codetest.domain.interactor.comics;

import android.content.res.Resources;
import com.example.adrian.codetest.app.executor.interactor.InteractorExecutor;
import com.example.adrian.codetest.app.executor.main.MainThreadExecutor;
import com.example.adrian.codetest.domain.interactor.AbstractInteractor;
import com.example.adrian.codetest.domain.model.Comic;
import com.example.adrian.codetest.domain.repository.ComicRepository;
import java.util.List;

/**
 * This case use get comics from a injected Repository
 *
 */
public class GetComicsInteractor extends AbstractInteractor {

  private ComicRepository comicRepository;
  private int amont;
  private ComicsInteractorCallback comicsInteractorCallback;

  public GetComicsInteractor(MainThreadExecutor mainThreadExecutor,
      InteractorExecutor interactorExecutor, ComicRepository comicRepository) {
    super(mainThreadExecutor, interactorExecutor);
    this.comicRepository = comicRepository;
  }

  public void execute(ComicsInteractorCallback comicsInteractorCallback) {
    this.comicsInteractorCallback = comicsInteractorCallback;
    launch();
  }

  public void execute(int amount, ComicsInteractorCallback comicsInteractorCallback) {
    this.comicsInteractorCallback = comicsInteractorCallback;
    this.amont = amount;
    launch();
  }

  @Override
  public void run() {
    try {
      List<Comic> comics;
      if (amont != 0) {
        comics = comicRepository.fetch(amont);
      } else {
        comics = comicRepository.fetch();
      }
      doOnComicsSuccess(comics);
    } catch (Resources.NotFoundException e) {
      doOnComicsError();
    }
  }

  private void doOnComicsSuccess(final List<Comic> comics) {
    launchOnMainThread(new Runnable() {
      @Override
      public void run() {
        comicsInteractorCallback.onComicsFetch(comics);
      }
    });
  }

  public void doOnComicsError() {
    launchOnMainThread(new Runnable() {
      @Override
      public void run() {
        comicsInteractorCallback.onFetchError();
      }
    });
  }

  public interface ComicsInteractorCallback {

    void onComicsFetch(List<Comic> comicList);

    void onFetchError();
  }
}
