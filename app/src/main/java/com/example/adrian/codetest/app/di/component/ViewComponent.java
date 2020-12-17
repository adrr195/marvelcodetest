package com.example.adrian.codetest.app.di.component;

import com.example.adrian.codetest.ui.View;

public interface ViewComponent<T extends View> {

  void inject(T view);
}
