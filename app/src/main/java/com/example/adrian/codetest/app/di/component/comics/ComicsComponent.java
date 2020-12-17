package com.example.adrian.codetest.app.di.component.comics;

import com.example.adrian.codetest.app.di.annotation.PerView;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.module.ComicsModule;
import com.example.adrian.codetest.ui.comics.view.ComicsFragment;
import dagger.Component;

@Component(dependencies = ApplicationComponent.class, modules = ComicsModule.class)
@PerView
public interface ComicsComponent extends ViewComponent<ComicsFragment> {

}
