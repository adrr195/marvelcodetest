package com.example.adrian.codetest.ui.main.view;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.github.glomadrian.wallapopcodetest.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainViewTest {


  @Rule public ActivityScenarioRule<MainActivity> mActivityRule =
      new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void testIfInPortraitModeTheFrameIsShowing() throws InterruptedException {
    onView(withId(R.id.comics_frame)).check(matches(isDisplayed()));
  }

  @Test
  public void testIfScrollGetsMoreComics() throws InterruptedException {
    wait(5);
    onView(withId(R.id.comics_list)).perform(RecyclerViewActions.scrollToPosition(10));
    wait(2);
    onView(withId(R.id.comics_list)).perform(
        RecyclerViewActions.actionOnItemAtPosition(11, click()));
    wait(2);
    onView(withId(R.id.comic_info_frame)).check(matches(isDisplayed()));
  }

  /**
   * Use of Thread.sleep is no the best solution but is the faster one
   */
  private void wait(int seconds) throws InterruptedException {
    Thread.sleep(seconds * 1000);
  }
}
