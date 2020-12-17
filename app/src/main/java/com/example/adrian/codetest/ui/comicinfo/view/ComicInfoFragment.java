package com.example.adrian.codetest.ui.comicinfo.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.example.adrian.codetest.R;
import com.example.adrian.codetest.app.AbstractFragment;
import com.example.adrian.codetest.app.MainApplication;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.provider.ComponentProvider;
import com.example.adrian.codetest.domain.model.Comic;
import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.comicinfo.presenter.ComicInfoPresenter;
import com.example.adrian.codetest.ui.custom.TriangleImageView;
import com.example.adrian.codetest.utils.LogUtils;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

public class ComicInfoFragment extends AbstractFragment {

  public static final String COMIC_ID = "comicId";
  @Inject protected ComicInfoPresenter comicInfoPresenter;
  @BindView(R.id.header_image) protected TriangleImageView triangleImageView;
  @BindView(R.id.title) protected TextView title;
  @BindView(R.id.year) protected TextView year;
  @BindView(R.id.description) protected TextView description;
  @BindView(R.id.no_character) protected TextView noCharacter;

  @Override
  public ViewComponent bindViewComponent() {
    ApplicationComponent applicationComponent =
        ((MainApplication) getActivity().getApplication()).getApplicationComponent();
    return ComponentProvider.getComicInfoComponent(applicationComponent);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (getArguments() != null) {
      int comicId = getArguments().getInt(COMIC_ID);
      comicInfoPresenter.onComicIdAvailable(comicId);
    }
  }

  @Override
  public LifeCyclePresenter bindPresenter() {
    return comicInfoPresenter;
  }

  @Override
  public int bindLayout() {
    return R.layout.comic_info_view;
  }

  public static ComicInfoFragment newInstance(int comicId) {
    Bundle args = new Bundle();
    args.putInt(COMIC_ID, comicId);
    ComicInfoFragment comicInfoFragment = new ComicInfoFragment();
    comicInfoFragment.setArguments(args);
    return comicInfoFragment;
  }

  public void draw(Comic comic) {
    Log.i(LogUtils.generateTag(this), "Draw comic " + comic);
    title.setText(comic.getTitle());
    year.setText(comic.getYear());
    description.setText(comic.getDescription());
  }

  public void drawHeader(String image) {
    Picasso.get()
        .load(image)
        .placeholder(R.drawable.placeholder)
        .into(triangleImageView);
  }

  public void showNoComicSelected() {
    noCharacter.setVisibility(View.VISIBLE);
  }

  public void hideNoComicSelected() {
    noCharacter.setVisibility(View.INVISIBLE);
  }
}
