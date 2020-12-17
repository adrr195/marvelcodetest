package com.example.adrian.codetest.app.executor;

/**
 * Interface for create a executor
 */
public interface Executor<T extends Runnable> {

  void execute(T runnable);
}
