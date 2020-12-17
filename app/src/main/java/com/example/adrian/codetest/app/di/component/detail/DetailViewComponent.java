package com.example.adrian.codetest.app.di.component.detail;

import com.example.adrian.codetest.app.di.annotation.PerView;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.module.DetailViewModule;
import com.example.adrian.codetest.ui.detail.view.DetailView;
import dagger.Component;


@Component(dependencies = ApplicationComponent.class, modules = DetailViewModule.class) @PerView
public interface DetailViewComponent extends ViewComponent<DetailView> {

  @Override void inject(DetailView detailView);
}
