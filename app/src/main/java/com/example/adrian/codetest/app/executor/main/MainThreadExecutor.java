package com.example.adrian.codetest.app.executor.main;

import com.example.adrian.codetest.app.executor.Executor;

/**
 * Mark interface to be able to inject distinct MainThreadExecutor implementations*
 */
public interface MainThreadExecutor<T extends Runnable> extends Executor<T> {
}
