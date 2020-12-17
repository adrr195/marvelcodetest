package com.example.adrian.codetest.ui;

/**
 * All Presenters extends this Abstract Presenter and below to a view
 * In the presenter the view life cycle are mapped because it needs to know the view state
 * to perform actions
 * The methods are not abstract because is not necessary to implements all life cycle
 */
public abstract class LifeCyclePresenter<T extends View> extends Presenter<T> {

  public void onCreate() {
    //Empty
  }

  public void onStart() {
    //Empty
  }

  public void onStop() {
    //Empty
  }

  public void onResume() {
    //Empty
  }

  public void onPause() {
    //Empty
  }

  public void onDestroy() {
    //Empty
  }
}
