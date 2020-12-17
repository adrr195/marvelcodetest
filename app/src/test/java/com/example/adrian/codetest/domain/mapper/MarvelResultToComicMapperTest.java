package com.example.adrian.codetest.domain.mapper;

import com.example.adrian.codetest.data.comic.marvel.model.Image;
import com.example.adrian.codetest.data.comic.marvel.model.Result;
import com.example.adrian.codetest.data.comic.marvel.model.Thumbnail;
import com.example.adrian.codetest.domain.model.Comic;
import java.util.List;
import org.junit.Assert;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MarvelResultToComicMapperTest {

  @Test
  public void comicShouldBeWellCreate() {

    MarvelResultToComicMapper marvelResultToComicMapper = new MarvelResultToComicMapper();

    Thumbnail thumbnail = mock(Thumbnail.class);
    when(thumbnail.getPath()).thenReturn("http://fakeimage");
    when(thumbnail.getExtension()).thenReturn("jpg");

    List<Image> listMocked = mock(List.class);
    when(listMocked.size()).thenReturn(0);

    Result mockedResult = mock(Result.class);
    when(mockedResult.getTitle()).thenReturn("Comic");
    when(mockedResult.getThumbnail()).thenReturn(thumbnail);
    when(mockedResult.getDescription()).thenReturn("this is the description");
    when(mockedResult.getId()).thenReturn("1");
    when(mockedResult.getImages()).thenReturn(listMocked);

    Comic comic = marvelResultToComicMapper.map(mockedResult);

    Assert.assertEquals(comic.getId(), 1);
    Assert.assertEquals(comic.getTitle(), "Comic");
    Assert.assertEquals(comic.getDescription(), "this is the description");
    Assert.assertEquals(comic.getThumbnailUrl(), "http://fakeimage.jpg");
  }
}
