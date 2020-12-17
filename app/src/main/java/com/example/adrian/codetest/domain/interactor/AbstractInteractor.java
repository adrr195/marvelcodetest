package com.example.adrian.codetest.domain.interactor;

import com.example.adrian.codetest.app.executor.interactor.Interactor;
import com.example.adrian.codetest.app.executor.interactor.InteractorExecutor;
import com.example.adrian.codetest.app.executor.main.MainThreadExecutor;

/**
 * @author adrian
 */
public abstract class AbstractInteractor implements Interactor {

  protected MainThreadExecutor mainThreadExecutor;
  protected InteractorExecutor interactorExecutor;

  protected AbstractInteractor(MainThreadExecutor mainThreadExecutor,
      InteractorExecutor interactorExecutor) {
    this.mainThreadExecutor = mainThreadExecutor;
    this.interactorExecutor = interactorExecutor;
  }

  protected void launch() {
    interactorExecutor.execute(this);
  }

  protected void launchOnMainThread(Runnable runnable) {
    mainThreadExecutor.execute(runnable);
  }
}
