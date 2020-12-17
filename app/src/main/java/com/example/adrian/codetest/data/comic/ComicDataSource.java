package com.example.adrian.codetest.data.comic;

import com.example.adrian.codetest.data.comic.exception.ComicsNotFoundException;
import com.example.adrian.codetest.domain.model.Comic;
import java.util.List;

/**
 * Define how a Comic data sourve should be implemented
 *
 */
public interface ComicDataSource {

  List<Comic> getComics(int limit, int characterId) throws ComicsNotFoundException;

  List<Comic> getComics(int limit, int offset, int characterId) throws ComicsNotFoundException;
}
