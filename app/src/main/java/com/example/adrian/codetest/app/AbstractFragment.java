package com.example.adrian.codetest.app;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.View;
import com.example.adrian.codetest.utils.AssertUtils;

/**
 * Abstract fragment manage the lifecycle and binding with Presenter
 * Also do the views injections and dependencies injection
 *
 */
public abstract class AbstractFragment extends Fragment implements View {

  private LifeCyclePresenter presenter;
  private int layout;
  private ViewComponent viewComponent;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    onBeforeInit();
    super.onCreate(savedInstanceState);
    init();
  }

  @Override
  public void onResume() {
    assertInitLaunched();
    super.onResume();
    presenter.onResume();
  }

  @Override
  public void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override
  public void onStop() {
    super.onStop();
    presenter.onStop();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  @Nullable
  @Override
  public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    layout = bindLayout();
    assertBindLayout();
    return inflater.inflate(layout, container, false);
  }

  @Override
  public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter.attachView(this);
    ButterKnife.bind(this, getView());
  }

  protected void init() {
    this.viewComponent = bindViewComponent();
    assertBindViewComponent();
    viewComponent.inject(this);
    presenter = bindPresenter();
    assertBindPresenter();
    presenter.onCreate();
  }

  public abstract ViewComponent bindViewComponent();

  public abstract LifeCyclePresenter bindPresenter();

  public abstract int bindLayout();

  private void assertBindViewComponent() {
    AssertUtils.assertThis(viewComponent != null,
        "no viewComponent bind, bind a viewComponent to perform injection", getClass());
  }

  private void assertBindPresenter() {
    AssertUtils.assertThis(presenter != null,
        "no presenter bind, bind a presenter on bindPresenter() method", getClass());
  }

  private void assertBindLayout() {
    AssertUtils.assertThis(layout != 0, "No layout bind, bind a layout on bindLayout() method",
        getClass());
  }

  private void assertInitLaunched() {
    AssertUtils.assertThis(viewComponent != null,
        "No layout bind, bind a layout on bindLayout() method ", getClass());
  }

  /**
   * override this method if needs to do something before the onCreate()
   */
  public void onBeforeInit() {
    //Empty
  }
}
