package com.example.adrian.codetest.domain.mapper;

import android.util.Log;
import com.example.adrian.codetest.data.comic.marvel.model.Date;
import com.example.adrian.codetest.data.comic.marvel.model.Image;
import com.example.adrian.codetest.data.comic.marvel.model.Result;
import com.example.adrian.codetest.domain.mapper.exception.DataNotFoundException;
import com.example.adrian.codetest.domain.model.Comic;
import com.example.adrian.codetest.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Map beetween the marvel api domain and application domain, this is very important, if the data
 * changes in the marvel api, there is not need to change the application model, also the
 * application  model can be use with others apis
 */
public class MarvelResultToComicMapper implements Mapper<Result, Comic> {

  @Override
  public Comic map(Result result) {
    Comic comic = new Comic();
    try {
      comic.setTitle(obtainTitle(result));
      comic.setThumbnailUrl(obtainThumbnail(result));
      comic.setDescription(obtainDescription(result));
      comic.setId(obtainId(result));
      comic.setYear(obtainYear(result));
      comic.setImagesUrls(obatainImages(result));
    } catch (DataNotFoundException e) {
      Log.w(LogUtils.generateTag(this), "Some data not found result");
    }
    return comic;
  }

  private String obtainTitle(Result result) throws DataNotFoundException {
    if (result.getTitle() != null) {
      return result.getTitle();
    } else {
      throw new DataNotFoundException();
    }
  }

  private String obtainThumbnail(Result result) throws DataNotFoundException {
    return parseImage(result.getThumbnail());
  }

  private String obtainDescription(Result result) throws DataNotFoundException {
    return result.getDescription();
  }

  private int obtainId(Result result) {
    return Integer.valueOf(result.getId());
  }

  private String obtainYear(Result result) throws DataNotFoundException {
    for (Date date : result.getDates()) {
      if ("onsaleDate".equals(date.getType())) {
        return date.getDate().substring(0, 4);
      }
    }
    throw new DataNotFoundException();
  }

  private List<String> obatainImages(Result result) {
    List<String> images = new ArrayList<>();
    for (Image image : result.getImages()) {
      images.add(parseImage(image));
    }
    return images;
  }

  private String parseImage(Image image) {
    return image.getPath() + "." + image.getExtension();
  }
}
