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
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

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

    public void addMeeting_shouldAddMeeting() {

    }

    @Test
    public void defineSubject_shouldNotBeEmpty() {
        onView(withId(R.id.about_add_meeting)).perform(click()).perform(typeText("Sujet"));
    }

    @Test
    public void selectRooms_shouldNotBeEmpty() {
        //onView(ViewMatchers.withId(R.id.list_autocomplete)).perform(scrollTo());

    }

    @Test
    public void selectUsers_shouldNotBeEmpty() {
        onView(withText("yoannroche")).perform(click());
    }

    @Test
    public void error_throwMessageEmptySubject() {
        onView(withText("yoannroche")).perform(click());

        onView(withId(R.id.fab)).perform(click());
    }

    @Test
    public void error_throwMessageNoUsersSelect() {
        onView(withId(R.id.about_add_meeting)).perform(click()).perform(typeText("Sujet"));
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.fab)).perform(click());
    }

    @Test
    public void error_throwMessageRoomNotFree() {
       // mActivity.setHoursView("19");
        onView(withText("yoannroche")).perform(click());
        onView(withId(R.id.about_add_meeting)).perform(click()).perform(typeText("Sujet"));
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.fab)).perform(click());
    }
}
