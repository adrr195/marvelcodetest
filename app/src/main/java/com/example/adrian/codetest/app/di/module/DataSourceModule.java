package com.example.adrian.codetest.app.di.module;

import com.example.adrian.codetest.data.comic.ComicDataSource;
import com.example.adrian.codetest.data.comic.marvel.MarvelServiceDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class DataSourceModule {

  @Provides
  @Named("marvel_endpoint")
  public String provideMarvelEndpoint() {
    return "http://gateway.marvel.com";
  }

  @Provides
  @Named("marvel_public_api_key")
  public String provideMarvelPublicKey() {
    return "648450c9f88cd86f6cb714f4c023c7ef";
  }

  @Provides
  @Named("marvel_private_api_key")
  public String provideMarvelPrivateKey() {
    return "47105a14875d3abf7609f2c2791c428131ed891f";
  }

  @Provides
  @Singleton
  public ComicDataSource provideComicDataSource(@Named("marvel_endpoint") String endpoint,
      @Named("marvel_public_api_key") String publicKey,
      @Named("marvel_private_api_key") String privateKey) {
    return new MarvelServiceDataSource(endpoint, publicKey, privateKey);
  }
}
