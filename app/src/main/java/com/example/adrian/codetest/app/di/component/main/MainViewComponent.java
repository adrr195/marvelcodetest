package com.example.adrian.codetest.app.di.component.main;

import com.example.adrian.codetest.app.di.annotation.PerView;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.module.MainViewModule;
import com.example.adrian.codetest.ui.main.view.MainActivity;
import dagger.Component;

@Component(dependencies = ApplicationComponent.class, modules = MainViewModule.class) @PerView
public interface MainViewComponent extends ViewComponent<MainActivity> {

  @Override void inject(MainActivity mainView);
}
