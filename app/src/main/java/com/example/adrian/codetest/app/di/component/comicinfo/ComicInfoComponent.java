package com.example.adrian.codetest.app.di.component.comicinfo;

import com.example.adrian.codetest.app.di.annotation.PerView;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.module.ComicInfoModule;
import com.example.adrian.codetest.ui.comicinfo.view.ComicInfoFragment;
import dagger.Component;


@Component(dependencies = ApplicationComponent.class, modules = ComicInfoModule.class)
@PerView
public interface ComicInfoComponent extends ViewComponent<ComicInfoFragment> {
}
