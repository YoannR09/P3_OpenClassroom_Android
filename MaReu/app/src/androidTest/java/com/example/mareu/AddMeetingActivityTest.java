package com.example.mareu;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.ui.AddMeetingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AddMeetingActivityTest {

    private AddMeetingActivity mActivity;

    @Rule
    public ActivityTestRule<AddMeetingActivity> mActivityRule =
            new ActivityTestRule(AddMeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void addMeeting_shouldAddMeeting() {
        onView(withText("yoannroche")).perform(click());
        onView(withId(R.id.about_add_meeting)).perform(click()).perform(typeText("Sujet"));
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.view_bottom)).perform(scrollTo());
        onView(withText("Select your room")).perform(click());
        onView(withText("Room D")).inRoot(withDecorView(not(mActivity.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.fab)).perform(click());

        assertTrue(mActivity.getActivity().isFinishing());
    }

    @Test
    public void defineSubject_shouldNotBeEmpty() {
        onView(withId(R.id.about_add_meeting)).perform(click()).perform(typeText("Sujet"));
    }

    @Test
    public void selectRooms_shouldNotBeEmpty() {
        onView(withId(R.id.view_bottom)).perform(scrollTo());
        onView(withText("Select your room")).perform(click());
        onView(withText("Room C")).inRoot(withDecorView(not(mActivity.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void selectUsers_shouldNotBeEmpty() {
        onView(withText("yoannroche")).perform(click());
    }

    @Test
    public void error_throwMessageEmptySubject() {
        onView(withText("yoannroche")).perform(click());

        onView(withId(R.id.view_bottom)).perform(scrollTo());
        onView(withText("Select your room")).perform(click());
        onView(withText("Room D")).inRoot(withDecorView(not(mActivity.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.fab)).perform(click());

        onView(allOf(withText(R.string.error_subject)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void error_throwMessageNoUsersSelect() {
        onView(withId(R.id.about_add_meeting)).perform(click()).perform(typeText("Sujet"));
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.view_bottom)).perform(scrollTo());
        onView(withText("Select your room")).perform(click());
        onView(withText("Room C")).inRoot(withDecorView(not(mActivity.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.fab)).perform(click());

        onView(allOf(withText(R.string.error_user)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void error_throwMessageRoomNotSelect() {
        onView(withText("yoannroche")).perform(click());
        onView(withId(R.id.about_add_meeting)).perform(click()).perform(typeText("Sujet"));
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.fab)).perform(click());

        onView(allOf(withText(R.string.error_rooms)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void error_throwMessageRoomNotFree() {
        onView(withText("yoannroche")).perform(click());
        onView(withId(R.id.about_add_meeting)).perform(click()).perform(typeText("Sujet"));
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.view_bottom)).perform(scrollTo());
        onView(withText("Select your room")).perform(click());
        onView(withText("Room A")).inRoot(withDecorView(not(mActivity.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.fab)).perform(click());

        onView(allOf(withText(R.string.not_free)))
                .check(matches(isDisplayed()));
    }
}
