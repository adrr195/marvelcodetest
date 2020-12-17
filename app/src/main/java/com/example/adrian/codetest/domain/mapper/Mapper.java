package com.example.adrian.codetest.domain.mapper;

/**
 * Define a map
 *
 */
public interface Mapper<TFrom, TTo> {

  TTo map(TFrom from);
}
