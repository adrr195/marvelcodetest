package com.example.adrian.codetest.app.di.annotation;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Custom annotation for use with Views
 *
 */
@Scope @Retention(RUNTIME) public @interface PerView {
}
