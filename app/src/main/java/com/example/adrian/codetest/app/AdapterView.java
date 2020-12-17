package com.example.adrian.codetest.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.ui.Presenter;
import com.example.adrian.codetest.ui.View;
import com.example.adrian.codetest.utils.AssertUtils;

/**
 * Adapter view is used for views that lives in adapters, every view in the adapter
 * have a presenter to manage the actions
 *
 */
public abstract class AdapterView implements View {

  protected Context context;
  protected android.view.View view;
  protected ViewGroup viewGroup;
  private ViewComponent viewComponent;
  private int layout;
  private Presenter presenter;

  public AdapterView(Context context, ViewGroup viewGroup) {
    this.context = context;
    this.viewGroup = viewGroup;
    viewComponent = bindViewComponent(context.getApplicationContext());
    assertBindViewComponent();
    viewComponent.inject(this);
    presenter = bindPresenter();
    assertBindPresenter();
    initView();
    ButterKnife.bind(this, view);
    presenter.attachView(this);
    presenter.onViewReady();
    onAfterCreate();
  }

  private void initView() {
    layout = bindLayout();
    assertBindLayout();
    this.view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
  }

  public void onAfterCreate() {
    //Empty
  }

  public abstract ViewComponent bindViewComponent(Context context);

  public abstract Presenter bindPresenter();

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

  public android.view.View getView() {
    return view;
  }
}
