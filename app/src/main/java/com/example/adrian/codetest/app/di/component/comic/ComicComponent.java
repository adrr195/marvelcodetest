package com.example.adrian.codetest.app.di.component.comic;

import com.example.adrian.codetest.app.di.annotation.PerView;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.module.ComicModule;
import com.example.adrian.codetest.ui.comic.view.ComicView;
import dagger.Component;


@Component(dependencies = ApplicationComponent.class, modules = ComicModule.class)
@PerView
public interface ComicComponent extends ViewComponent<ComicView> {
}
