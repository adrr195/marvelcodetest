package com.example.adrian.codetest.app.di.provider;

import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.component.comic.ComicComponent;
import com.example.adrian.codetest.app.di.component.comic.DaggerComicComponent;
import com.example.adrian.codetest.app.di.component.comicinfo.ComicInfoComponent;
import com.example.adrian.codetest.app.di.component.comicinfo.DaggerComicInfoComponent;
import com.example.adrian.codetest.app.di.component.comics.ComicsComponent;
import com.example.adrian.codetest.app.di.component.comics.DaggerComicsComponent;
import com.example.adrian.codetest.app.di.component.detail.DaggerDetailViewComponent;
import com.example.adrian.codetest.app.di.component.detail.DetailViewComponent;
import com.example.adrian.codetest.app.di.component.main.DaggerMainViewComponent;
import com.example.adrian.codetest.app.di.component.main.MainViewComponent;
import com.example.adrian.codetest.app.di.module.ComicInfoModule;
import com.example.adrian.codetest.app.di.module.ComicModule;
import com.example.adrian.codetest.app.di.module.ComicsModule;
import com.example.adrian.codetest.app.di.module.DetailViewModule;
import com.example.adrian.codetest.app.di.module.MainViewModule;

/**
 * This component provider create and store the components on demand
 * Currently im working on a better solution but this solved the problem for now
 */
public final class ComponentProvider {

  private static MainViewComponent mainViewComponent = null;
  private static ComicComponent comicComponent = null;
  private static ComicsComponent comicsComponent = null;
  private static DetailViewComponent detailViewComponent = null;
  private static ComicInfoComponent comicInfoComponent = null;

  private ComponentProvider() {
  }

  public static ComicComponent getComicComponent(ApplicationComponent applicationComponent) {
    if (comicComponent == null) {
      comicComponent = DaggerComicComponent.builder()
          .applicationComponent(applicationComponent)
          .comicModule(new ComicModule())
          .build();
    }
    return comicComponent;
  }

  public static ComicInfoComponent getComicInfoComponent(
      ApplicationComponent applicationComponent) {
    if (comicInfoComponent == null) {
      comicInfoComponent = DaggerComicInfoComponent.builder()
          .applicationComponent(applicationComponent)
          .comicInfoModule(new ComicInfoModule())
          .build();
    }
    return comicInfoComponent;
  }

  public static ComicsComponent getComicsComponent(ApplicationComponent applicationComponent) {
    if (comicsComponent == null) {
      comicsComponent = DaggerComicsComponent.builder()
          .applicationComponent(applicationComponent)
          .comicsModule(new ComicsModule())
          .build();
    }
    return comicsComponent;
  }

  public static DetailViewComponent getDetailViewComponent(
      ApplicationComponent applicationComponent) {
    if (detailViewComponent == null) {
      detailViewComponent = DaggerDetailViewComponent.builder()
          .applicationComponent(applicationComponent)
          .detailViewModule(new DetailViewModule())
          .build();
    }
    return detailViewComponent;
  }

  public static MainViewComponent getMainViewComponent(ApplicationComponent applicationComponent) {
    if (mainViewComponent == null) {
      mainViewComponent = DaggerMainViewComponent.builder()
          .applicationComponent(applicationComponent)
          .mainViewModule(new MainViewModule())
          .build();
    }
    return mainViewComponent;
  }
}