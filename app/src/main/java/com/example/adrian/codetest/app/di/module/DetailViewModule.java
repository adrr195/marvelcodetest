package com.example.adrian.codetest.app.di.module;

import com.example.adrian.codetest.ui.detail.presenter.DetailPresenter;
import dagger.Module;
import dagger.Provides;

@Module public class DetailViewModule {

  @Provides public DetailPresenter provideDetailPresenter() {
    return new DetailPresenter();
  }
}
