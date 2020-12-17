package com.example.adrian.codetest.ui.comic.presenter;

import com.example.adrian.codetest.app.navigator.Navigator;
import com.example.adrian.codetest.domain.model.Comic;
import com.example.adrian.codetest.ui.Presenter;
import com.example.adrian.codetest.ui.comic.view.ComicView;

public class ComicPresenter extends Presenter<ComicView> {

  private Navigator navigator;

  public ComicPresenter(Navigator navigator) {
    this.navigator = navigator;
  }

  public void onComicAvailable() {
    view.drawComic();
  }

  public void onComicClicked(Comic comic) {
    navigator.goToDetailView(comic.getId());
  }
}
