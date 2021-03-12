package com.example.mareu;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class MainActivityTest {

    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void meetingList_shouldNotBeEmpty() {
        onView(withId(R.id.rvContacts))
                .check(matches(hasMinimumChildCount(2)));
    }

    @Test
    public void meeting_deleteAction_shouldRemoveItem() {
        onView(withId(R.id.rvContacts)).check(withItemCount(3));
        onView(withId(R.id.rvContacts))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withId(R.id.rvContacts)).check(withItemCount(2));
    }

    @Test
    public void meeting_addAction_shouldOpenForm() {
        onView(ViewMatchers.withId(R.id.button_adding_meeting)).perform(click());
        onView(ViewMatchers.withId(R.id.fab)).check(matches(isDisplayed()));
    }

    @Test
    public void meeting_filterByHour() {
        onView(ViewMatchers.withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.hour_filter)).perform(click()).perform(typeText("12"));
        onView(withId(R.id.rvContacts)).check(withItemCount(1));
    }
}
