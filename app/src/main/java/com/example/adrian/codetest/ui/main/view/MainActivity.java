package com.example.adrian.codetest.ui.main.view;

import android.os.Bundle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.example.adrian.codetest.R;
import com.example.adrian.codetest.app.AbstractActivity;
import com.example.adrian.codetest.app.MainApplication;
import com.example.adrian.codetest.app.di.component.ViewComponent;
import com.example.adrian.codetest.app.di.component.application.ApplicationComponent;
import com.example.adrian.codetest.app.di.provider.ComponentProvider;
import com.example.adrian.codetest.ui.LifeCyclePresenter;
import com.example.adrian.codetest.ui.comicinfo.view.ComicInfoFragment;
import com.example.adrian.codetest.ui.comics.view.ComicsFragment;
import com.example.adrian.codetest.ui.main.presenter.MainPresenter;
import javax.inject.Inject;

public class MainActivity extends AbstractActivity {

  public static final String TAG_PORTRAIT = "V11-portrait";
  public static final String TAG_LANDSCAPE = "V11-landscape";
  @Inject
  protected MainPresenter mainPresenter;
  @BindView(R.id.comics_frame)
  protected FrameLayout charactersFrame;
  @BindView(R.id.toolbar)
  protected Toolbar toolbar;
  @BindView(R.id.drawer_layout)
  protected DrawerLayout drawerLayout;
  private ActionBarDrawerToggle actionBarDrawerToggle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setSupportActionBar(toolbar);

    actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name,
            R.string.app_name);
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    actionBarDrawerToggle.syncState();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onViewReady() {
    super.onViewReady();
    mainPresenter.onViewReady();

    if (TAG_PORTRAIT.equals(view.getTag())) {
      mainPresenter.onPortrait();
    } else {
      mainPresenter.onLandScape();
    }
  }

  @Override
  public ViewComponent bindViewComponent() {
    ApplicationComponent applicationComponent =
        ((MainApplication) getApplication()).getApplicationComponent();
    return ComponentProvider.getMainViewComponent(applicationComponent);
  }

  @Override
  public LifeCyclePresenter bindPresenter() {
    return mainPresenter;
  }

  @Override
  public int bindLayout() {
    return R.layout.activity_main;
  }

  public void showComicsView() {
    ComicsFragment comicsFragment = ComicsFragment.newInstance();
    getSupportFragmentManager().beginTransaction().add(R.id.comics_frame, comicsFragment).commit();
  }

  public void showComicInfoView(int comicId) {
    ComicInfoFragment comicInfoFragment = ComicInfoFragment.newInstance(comicId);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.details_frame, comicInfoFragment)
        .commit();
  }
}
