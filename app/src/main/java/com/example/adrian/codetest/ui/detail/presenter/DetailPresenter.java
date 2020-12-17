package com.example.adrian.codetest.ui.detail.presenter;

import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.detail.view.DetailView;

public class DetailPresenter extends LifeCyclePresenter<DetailView> {
  private boolean fistTime = true;

  public void onComicIdAvailable(int comidId) {
    if (fistTime) {
      fistTime = false;
      view.showComicInfoView(comidId);
    }
  }
}
