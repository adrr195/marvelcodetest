package com.example.adrian.codetest.ui.comicinfo.presenter;

import com.example.adrian.codetest.domain.model.Comic;
import com.example.adrian.codetest.domain.repository.ComicRepository;
import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.comicinfo.view.ComicInfoFragment;
import com.example.adrian.codetest.utils.RandomUtils;

public class ComicInfoPresenter extends LifeCyclePresenter<ComicInfoFragment> {

  private ComicRepository comicRepository;

  public ComicInfoPresenter(ComicRepository comicRepository) {
    this.comicRepository = comicRepository;
  }

  @Override
  public void onViewReady() {
    super.onViewReady();
    view.showNoComicSelected();
  }

  public void onComicIdAvailable(int comicId) {
    view.hideNoComicSelected();
    Comic comic = comicRepository.findById(comicId);
    view.draw(comic);
    if (comic.getImagesUrls().size() > 0) {
      view.drawHeader(selectRandomImage(comic));
    } else {
      view.drawHeader(comic.getThumbnailUrl());
    }
  }

  private String selectRandomImage(Comic comic) {
    int number = RandomUtils.getRandomNumber(0, comic.getImagesUrls().size() - 1);
    return comic.getImagesUrls().get(number);
  }
}
