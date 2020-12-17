package com.example.adrian.codetest.app.executor.interactor;

import com.example.adrian.codetest.app.executor.Executor;

/**
 * Interactor executor interface
 */
public interface InteractorExecutor extends Executor<Interactor> {

  void execute(Interactor interactor);
}
