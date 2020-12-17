package com.example.adrian.codetest.ui.main.presenter;

import com.example.adrian.codetest.app.navigator.Navigator;
import com.example.adrian.codetest.domain.repository.ComicRepository;
import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.main.view.MainActivity;

public class MainPresenter extends LifeCyclePresenter<MainActivity> {

  private Navigator navigator;
  private boolean fistTime = true;
  private ComicRepository comicRepository;

  public MainPresenter(Navigator navigator, ComicRepository comicRepository) {
    this.navigator = navigator;
    this.comicRepository = comicRepository;
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onViewReady() {
    super.onViewReady();
    if (fistTime) {
      fistTime = false;
      view.showComicsView();
    }
  }

  public void onPortrait() {
    //Empty
  }

  public void onLandScape() {
    if (!comicRepository.isEmpty()) {
      view.showComicInfoView(comicRepository.getAll().get(0).getId());
    }
  }
}
