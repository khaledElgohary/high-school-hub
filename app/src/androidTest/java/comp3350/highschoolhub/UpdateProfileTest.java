package comp3350.highschoolhub;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.presentation.Login;

@RunWith(AndroidJUnit4.class)
public class UpdateProfileTest {
    @Rule
    public ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class);

    @Test
    public void updateProfile() {
        //Login in as user 0
        onView(withId(R.id.editTextUsername)).perform(typeText("0"));
        onView(withId(R.id.editTextPassword)).perform(typeText("password0"));
        onView(withId(R.id.buttonSubmit)).perform(click());

        //update name
        onView(withId(R.id.edit_name)).perform(click());
        onView(withId(R.id.user_name)).perform(replaceText("Orange Programmer"));
        onView(withId(R.id.edit_name)).perform(click());
        onView(withId(R.id.user_name)).check(matches(withText("Orange Programmer")));

        //update bio
        onView(withId(R.id.edit_bio)).perform(click());
        onView(withId(R.id.Bio)).perform(replaceText("We no longer code in Purple."));
        onView(withId(R.id.edit_bio)).perform(click());
        onView(withId(R.id.Bio)).check(matches(withText("We no longer code in Purple.")));

        //update marital status
        onView(withId(R.id.chooseStatus)).perform(click());
        onData(instanceOf(String.class)).atPosition(3).perform(click());
        onView(withId(R.id.marital_status)).check(matches(withText("Widowed")));

        //update high schools
        onView(withId(R.id.highSchoolsButton)).perform(click());
        onView(withId(R.id.editHighSchoolsButton)).perform(click());
        onData(instanceOf(HighSchool.class)).atPosition(2).perform(click());
        onData(instanceOf(HighSchool.class)).atPosition(3).perform(click());
        onView(withId(R.id.submitHighSchools)).perform(click());
        onView(withText("Fort Richmond Collegiate")).check(matches(isDisplayed()));
        onView(withText("Gordon Bell High School")).check(matches(isDisplayed()));
        onView(withId(R.id.backToMyProfile)).perform(click());
    }
}
