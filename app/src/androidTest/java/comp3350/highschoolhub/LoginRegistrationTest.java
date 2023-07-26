package comp3350.highschoolhub;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.presentation.Login;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginRegistrationTest {
    private int numUsers;

    @Rule
    public ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class);

    @Before
    public void setup() {
        UserPersistence userPersistence = Services.getUserPersistence();
        numUsers = userPersistence.getUsers().size();
    }

    @Test
    public void registerUser() {
        //Navigate to registration page
        onView(withId(R.id.buttonRegister)).perform(click());

        //Enter new user information
        onView(withId(R.id.editTextFirstName)).perform(typeText("John"));
        onView(withId(R.id.editTextLastName)).perform(typeText("Doe"));
        onView(withId(R.id.editTextPassword)).perform(typeText("Alphabet123"));
        onView(withId(R.id.editTextBio)).perform(typeText("I love COMP 3350."));
        closeSoftKeyboard();
        onView(withId(R.id.maritalStatusDropdown)).perform(click());
        onData(instanceOf(String.class)).atPosition(1).perform(click());
        onView(withId(R.id.highSchoolDropdown)).perform(click());
        onData(instanceOf(HighSchool.class)).atPosition(3).perform(click());
        onView(withId(R.id.buttonSubmit)).perform(click());

        //Check entered info matches profile
        onView(withId(R.id.user_name)).check(matches(withText("John Doe")));
        onView(withId(R.id.Bio)).check(matches(withText("I love COMP 3350.")));
        onView(withId(R.id.marital_status)).check(matches(withText("Single")));
        onView(withId(R.id.highSchoolsButton)).perform(click());
        onView(withText("Gordon Bell High School")).check(matches(isDisplayed()));

        //Log out
        onView(withId(R.id.backToMyProfile)).perform(click());
        onView(withId(R.id.logout)).perform(click());

        //Log back in
        onView(withId(R.id.editTextUsername)).perform(typeText(Integer.toString(numUsers + 1)));
        onView(withId(R.id.editTextPassword)).perform(typeText("Alphabet123"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonSubmit)).perform(click());

        //Check logged in user matches the newly registered user
        onView(withId(R.id.user_name)).check(matches(withText("John Doe")));
        onView(withId(R.id.Bio)).check(matches(withText("I love COMP 3350.")));
        onView(withId(R.id.marital_status)).check(matches(withText("Single")));
        onView(withId(R.id.highSchoolsButton)).perform(click());
        onView(withText("Gordon Bell High School")).check(matches(isDisplayed()));
    }
}
