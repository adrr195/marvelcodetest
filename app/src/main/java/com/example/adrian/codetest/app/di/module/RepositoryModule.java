package com.example.adrian.codetest.app.di.module;

import com.example.adrian.codetest.data.comic.ComicDataSource;
import com.example.adrian.codetest.domain.model.Character;
import com.example.adrian.codetest.domain.repository.CloudCharacterComicRepository;
import com.example.adrian.codetest.domain.repository.ComicRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class RepositoryModule {

  @Provides
  @Singleton
  public ComicRepository provideComicRepository(ComicDataSource comicDataSource) {
    CloudCharacterComicRepository comicRepository =
            new CloudCharacterComicRepository(comicDataSource);
    Character captainAmerica = new Character();
    captainAmerica.setId(1009220);
    //Character nomad = new Character();
    //nomad.setId(1009475);
    comicRepository.initialize(captainAmerica);
    return comicRepository;
  }
}
