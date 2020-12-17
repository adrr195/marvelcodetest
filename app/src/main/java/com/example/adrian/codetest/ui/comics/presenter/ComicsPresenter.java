package com.example.adrian.codetest.ui.comics.presenter;

import android.util.Log;
import com.example.adrian.codetest.domain.interactor.comics.GetComicsInteractor;
import com.example.adrian.codetest.domain.model.Comic;
import com.example.adrian.codetest.domain.repository.ComicRepository;
import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.comics.view.ComicsFragment;
import com.example.adrian.codetest.utils.LogUtils;
import java.util.List;

public class ComicsPresenter extends LifeCyclePresenter<ComicsFragment> {

  private static final int FIRST_COMIC_AMOUNT = 20;
  private GetComicsInteractor getComicsInteractor;
  private ComicsCallback comicsCallback;
  private boolean loading = false;
  private ComicRepository comicRepository;

  public ComicsPresenter(GetComicsInteractor getComicsInteractor, ComicRepository comicRepository) {
    this.getComicsInteractor = getComicsInteractor;
    this.comicRepository = comicRepository;
    init();
  }

  private void init() {
    comicsCallback = new ComicsCallback();
  }

  @Override
  public void onViewReady() {
    super.onViewReady();
    if (!view.isShowingComics()) {
      populateViewComics();
    }
    view.enableLastComicViewListener();
  }

  private void populateViewComics() {
    if (comicRepository.isEmpty()) {
      fetchFistComics();
    } else {
      view.showComics(comicRepository.getAll());
    }
  }

  private void fetchFistComics() {
    view.showLoading();
    getComicsInteractor.execute(FIRST_COMIC_AMOUNT, comicsCallback);
  }

  private void fetchMoreComics() {
    if (!loading) {
      loading = true;
      Log.i(LogUtils.generateTag(this), " more!");
      view.showLoading();
      getComicsInteractor.execute(8, comicsCallback);
    }
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  public void onLastComicResearched() {
    fetchMoreComics();
  }

  private class ComicsCallback implements GetComicsInteractor.ComicsInteractorCallback {

    @Override
    public void onComicsFetch(List<Comic> comicList) {
      Log.i(LogUtils.generateTag(this), "Received " + comicList.size() + "comics");
      view.hideLoading();
      view.showComics(comicList);
      view.enableLastComicViewListener();
      loading = false;
    }

    @Override
    public void onFetchError() {
      view.hideLoading();
      view.showError("Fetch Error");
      loading = false;
    }
  }
}
