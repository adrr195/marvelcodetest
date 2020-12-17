package com.example.adrian.codetest.ui.comics.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.example.adrian.codetest.R;
import com.example.adrian.codetest.app.AbstractFragment;
import com.example.adrian.codetest.app.MainApplication;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.provider.ComponentProvider;
import com.example.adrian.codetest.domain.model.Comic;
import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.comics.adapter.ComicsAdapter;
import com.example.adrian.codetest.ui.comics.presenter.ComicsPresenter;
import java.util.List;
import javax.inject.Inject;

public class ComicsFragment extends AbstractFragment {

  @Inject
  protected ComicsPresenter comicsPresenter;
  @BindView(R.id.comics_list)
  protected RecyclerView comicsListView;
  @BindView(R.id.loading)
  protected ProgressBar loading;
  private ComicsAdapter comicsAdapter;
  private StaggeredGridLayoutManager layoutManager;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public ViewComponent bindViewComponent() {
    ApplicationComponent applicationComponent =
        ((MainApplication) getActivity().getApplication()).getApplicationComponent();
    return ComponentProvider.getComicsComponent(applicationComponent);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    buildComicAdapter();
    initComicListView();
  }

  @Override
  public void onResume() {
    super.onResume();
    comicsPresenter.onViewReady();
  }

  @Override
  public LifeCyclePresenter bindPresenter() {
    return comicsPresenter;
  }

  @Override
  public int bindLayout() {
    return R.layout.comics_view;
  }

  private void buildComicAdapter() {
    comicsAdapter = new ComicsAdapter(this.getContext());
  }

  private void initComicListView() {
    layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    comicsListView.setLayoutManager(layoutManager);
    comicsListView.setAdapter(comicsAdapter);
  }

  public void showComics(List<Comic> comicList) {
    comicsAdapter.addComics(comicList);
  }

  public void showLoading() {
    loading.setVisibility(View.VISIBLE);
  }

  public void hideLoading() {
    loading.setVisibility(View.INVISIBLE);
  }

  public void showError(String error) {
    Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show(); // Don’t forget to show!
  }

  public void enableLastComicViewListener() {
    enableSearchOnFinish();
  }

  private void enableSearchOnFinish() {
    comicsListView.addOnScrollListener(new FinishScrollListener());
  }

  public boolean isShowingComics() {
    return comicsAdapter.getItemCount() > 0;
  }

  public static ComicsFragment newInstance() {
    return new ComicsFragment();
  }

  private class FinishScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      int lastVisibleItemPosition =
          layoutManager.findLastCompletelyVisibleItemPositions(null)[1] + 1;
      int modelsCount = layoutManager.getItemCount();

      if (lastVisibleItemPosition == modelsCount) {
        comicsPresenter.onLastComicResearched();
      }
    }
  }
}
