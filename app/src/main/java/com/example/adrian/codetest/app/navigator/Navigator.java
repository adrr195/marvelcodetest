package com.example.adrian.codetest.app.navigator;

import android.app.Activity;

/**
 * Navigator to change between Activity and control the navigation in the application
 * Using an interface allows to inject the navigation in use, maybe different navigation are
 * required on different stages (debug, production) or flavors
 */
public interface Navigator {

  void finish();

  Activity getActivity();

  void setActivity(Activity activity);

  void goToDetailView(int comicId);
}
