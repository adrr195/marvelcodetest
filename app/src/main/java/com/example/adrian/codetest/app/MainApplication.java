package com.example.adrian.codetest.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.component.application.DaggerApplicationComponent;
import com.example.adrian.codetest.app.di.module.ApplicationModule;
import com.example.adrian.codetest.app.navigator.Navigator;
import javax.inject.Inject;

/**
 * Custom application for create and provide the Application Module on all
 * the activities
 *
 */
public class MainApplication extends Application implements Application.ActivityLifecycleCallbacks {

  @Inject
  protected Navigator navigator;
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    buildApplicationComponent();
    inject();
    this.registerActivityLifecycleCallbacks(this);
  }

  private void buildApplicationComponent() {
    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  private void inject() {
    applicationComponent.inject(this);
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    navigator.setActivity(activity);
  }

  @Override public void onActivityStarted(Activity activity) {
    //Empty
  }

  @Override public void onActivityResumed(Activity activity) {
    //Empty
  }

  @Override public void onActivityPaused(Activity activity) {
    //Empty
  }

  @Override public void onActivityStopped(Activity activity) {
    //Empty
  }

  @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    //Empty
  }

  @Override public void onActivityDestroyed(Activity activity) {
    //Empty
  }
}
