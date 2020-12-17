package com.example.adrian.codetest.data.comic.marvel;

import com.example.adrian.codetest.data.comic.ComicDataSource;
import com.example.adrian.codetest.data.comic.exception.ComicsNotFoundException;
import com.example.adrian.codetest.data.comic.marvel.factory.SynchronousCallAdapterFactory;
import com.example.adrian.codetest.data.comic.marvel.interceptor.MarvelRequestInterceptor;
import com.example.adrian.codetest.data.comic.marvel.model.ComicsResponse;
import com.example.adrian.codetest.domain.mapper.MarvelResponseToComicsMapper;
import com.example.adrian.codetest.domain.model.Comic;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Custom implementation of Comic Data Source using retrofit
 *
 */
public class MarvelServiceDataSource implements ComicDataSource {

  private MarvelService marvelService;
  private String baseUrl;
  private String publicKey;
  private String privateKey;
  private MarvelResponseToComicsMapper marvelResponseToComicsMapper;

  public MarvelServiceDataSource(String baseUrl, String publicKey, String privateKey) {
    this.baseUrl = baseUrl;
    this.publicKey = publicKey;
    this.privateKey = privateKey;
    init();
  }

  private void init() {
    buildRetrofitMarvelService();
    buildMarvelResponseMapper();
  }

  private void buildRetrofitMarvelService() {

    /*RestAdapter restAdapter =
        new RestAdapter.Builder().setRequestInterceptor(buildMarvenRequestInterceptor())
            .setConverter(new GsonConverter(new Gson()))
            .setEndpoint(baseUrl)
            .build();
    marvelService = restAdapter.create(MarvelService.class);*/

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(buildMarvenRequestInterceptor())
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(SynchronousCallAdapterFactory.create())
            .client(client)
            .build();

    marvelService = retrofit.create(MarvelService.class);

  }

  private void buildMarvelResponseMapper() {
    marvelResponseToComicsMapper = new MarvelResponseToComicsMapper();
  }

  private MarvelRequestInterceptor buildMarvenRequestInterceptor() {
    return new MarvelRequestInterceptor(publicKey, privateKey);
  }

  @Override
  public List<Comic> getComics(int limit, int characterId) throws ComicsNotFoundException {
    try {
      ComicsResponse marvelResult = marvelService.getComics(characterId, limit);
      return marvelResponseToComicsMapper.map(marvelResult);
    } catch (Exception error) {
      ComicsNotFoundException comicsNotFoundException = new ComicsNotFoundException();
      comicsNotFoundException.setStackTrace(error.getStackTrace());
      throw comicsNotFoundException;
    }
  }

  @Override
  public List<Comic> getComics(int limit, int offset, int characterId)
      throws ComicsNotFoundException {
    try {
      ComicsResponse marvelResult = marvelService.getComics(characterId, limit, offset);
      return marvelResponseToComicsMapper.map(marvelResult);
    } catch (Exception error) {
      ComicsNotFoundException comicsNotFoundException = new ComicsNotFoundException();
      comicsNotFoundException.setStackTrace(error.getStackTrace());
      throw comicsNotFoundException;
    }
  }
}
