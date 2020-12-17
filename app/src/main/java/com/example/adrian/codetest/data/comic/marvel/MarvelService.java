package com.example.adrian.codetest.data.comic.marvel;

import com.example.adrian.codetest.data.comic.marvel.model.ComicsResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarvelService {

  @GET("/v1/public/characters/{characterId}/comics")
  ComicsResponse getComics(@Path("characterId") int characterId, @Query("limit") int limit);

  @GET("/v1/public/characters/{characterId}/comics")
  ComicsResponse getComics(@Path("characterId") int characterId, @Query("limit") int limit,
                           @Query("offset") int offset);
}
