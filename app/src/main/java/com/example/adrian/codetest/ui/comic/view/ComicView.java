package com.example.adrian.codetest.ui.comic.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.adrian.codetest.R;
import com.example.adrian.codetest.app.AdapterView;
import com.example.adrian.codetest.app.MainApplication;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.provider.ComponentProvider;
import com.example.adrian.codetest.domain.model.Comic;
import com.example.adrian.codetest.ui.Presenter;
import com.example.adrian.codetest.ui.comic.presenter.ComicPresenter;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

public class ComicView extends AdapterView {

  @Inject protected ComicPresenter comicPresenter;
  @BindView(R.id.thumbnail) protected ImageView thumbnail;
  @BindView(R.id.title) protected TextView title;

  private Comic comic;

  public ComicView(Context context, ViewGroup viewGroup) {
    super(context, viewGroup);
  }

  public void render(Comic comic) {
    this.comic = comic;
    comicPresenter.onComicAvailable();
  }

  public void drawComic() {
    title.setText(comic.getTitle());
    Picasso.get()
        .load(comic.getThumbnailUrl())
        .placeholder(R.drawable.placeholder_portrait)
        .into(thumbnail);
  }

  @Override
  public ViewComponent bindViewComponent(Context context) {
    ApplicationComponent applicationComponent =
        ((MainApplication) context.getApplicationContext()).getApplicationComponent();
    return ComponentProvider.getComicComponent(applicationComponent);
  }

  @Override
  public Presenter bindPresenter() {
    return comicPresenter;
  }

  @Override
  public int bindLayout() {
    return R.layout.comic_view;
  }

  @OnClick(R.id.thumbnail)
  public void onThumbnailCLick() {
    comicPresenter.onComicClicked(comic);
  }
}
