package com.example.adrian.codetest.ui.detail.view;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.github.glomadrian.wallapopcodetest.R;
import com.github.glomadrian.wallapopcodetest.ui.main.view.MainActivity;
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
public class DetailViewTest {

  @Rule public ActivityScenarioRule<MainActivity> mActivityRule =
      new ActivityScenarioRule<>(MainActivity.class);

  @Test
  public void testGoToDetailView() throws InterruptedException {
    wait(5);
    onView(withId(R.id.comics_list)).perform(
        RecyclerViewActions.actionOnItemAtPosition(0, click()));
    wait(5);
    onView(withId(R.id.header_image)).check(matches(isDisplayed()));
  }

  /**
   * Use of Thread.sleep is no the best solution but is the faster one
   */
  private void wait(int seconds) throws InterruptedException {
    Thread.sleep(seconds * 1000);
  }
}
