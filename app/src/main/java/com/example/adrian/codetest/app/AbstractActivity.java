package com.example.adrian.codetest.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.View;
import com.example.adrian.codetest.utils.AssertUtils;

/**
 * Abstract activity do all the boilerplate code (injection, view injection, presenter lifecycle
 * binding)
 *
 * The activity must call the init method in the onCreate() and implements bindPresenter
 * with his injected presenter because the Abstract Activity need the instance of the presenter
 * to bind the lifecycle
 *
 * The module used to inject is provide by the children activity, so the view must create his own
 * dependency injection component
 *
 * If custom initialization is needed then override the init method
 *
 */
public abstract class AbstractActivity extends AppCompatActivity implements View {

  protected android.view.View view;
  private LifeCyclePresenter presenter;
  private int layout;
  private ViewComponent viewComponent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    onBeforeInit();
    super.onCreate(savedInstanceState);
    init();
  }

  @Override
  protected void onResume() {
    assertInitLaunched();
    super.onResume();
    presenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  @Override
  protected void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override
  protected void onStop() {
    super.onStop();
    presenter.onStop();
  }

  protected void init() {
    this.viewComponent = bindViewComponent();
    assertBindViewComponent();
    viewComponent.inject(this);
    presenter = bindPresenter();
    assertBindPresenter();
    layout = bindLayout();
    assertBindLayout();
    presenter.attachView(this);
    presenter.onCreate();
    view = getLayoutInflater().inflate(layout, null);
    setContentView(view);
    ButterKnife.bind(this);
    onViewReady();
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

  public void onViewReady() {
    //Empty
  }
}

