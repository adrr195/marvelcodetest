package com.example.adrian.codetest.ui.comics.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import com.example.adrian.codetest.domain.model.Comic;
import com.example.adrian.codetest.ui.comic.view.ComicView;
import java.util.ArrayList;
import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter {

  private Context context;
  private List<Comic> comics;

  public ComicsAdapter(Context context) {
    this.context = context;
    this.comics = new ArrayList<>();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return makeComicView(parent);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    Comic comic = comics.get(position);
    ((ComicViewHolder) holder).comicView.render(comic);
  }

  @Override
  public int getItemCount() {
    return comics.size();
  }

  private RecyclerView.ViewHolder makeComicView(ViewGroup viewGroup) {
    ComicView comicView = new ComicView(context, viewGroup);
    return new ComicViewHolder(comicView);
  }

  public void addComic(Comic comic) {
    comics.add(comic);
  }

  public void addComics(List<Comic> comics) {
    for (Comic comic : comics) {
      addComic(comic);
    }
    notifyDataSetChanged();
  }

  private class ComicViewHolder extends RecyclerView.ViewHolder {

    protected ComicView comicView;

    ComicViewHolder(ComicView comicView) {
      super(comicView.getView());
      this.comicView = comicView;
    }
  }
}
