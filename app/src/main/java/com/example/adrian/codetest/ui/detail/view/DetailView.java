package com.example.adrian.codetest.ui.detail.view;

import com.example.adrian.codetest.R;
import com.example.adrian.codetest.app.AbstractActivity;
import com.example.adrian.codetest.app.MainApplication;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.provider.ComponentProvider;
import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.comicinfo.view.ComicInfoFragment;
import com.example.adrian.codetest.ui.detail.presenter.DetailPresenter;
import javax.inject.Inject;


public class DetailView extends AbstractActivity {

  public static final String COMIC_ID = "comicId";
  @Inject protected DetailPresenter detailPresenter;

  @Override
  public void onViewReady() {
    super.onViewReady();
    int comidId = getIntent().getExtras().getInt(COMIC_ID);
    detailPresenter.onComicIdAvailable(comidId);
  }

  @Override
  public ViewComponent bindViewComponent() {
    ApplicationComponent applicationComponent =
        ((MainApplication) getApplication()).getApplicationComponent();
    return ComponentProvider.getDetailViewComponent(applicationComponent);
  }

  @Override
  public LifeCyclePresenter bindPresenter() {
    return detailPresenter;
  }

  @Override
  public int bindLayout() {
    return R.layout.detail_view;
  }

  public void showComicInfoView(int comicId) {
    ComicInfoFragment comicInfoFragment = ComicInfoFragment.newInstance(comicId);
    getSupportFragmentManager().beginTransaction()
        .add(R.id.comic_info_frame, comicInfoFragment)
        .commit();
  }
}
