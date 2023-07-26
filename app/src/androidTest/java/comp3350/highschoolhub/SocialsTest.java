package comp3350.highschoolhub;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.presentation.Login;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SocialsTest {
    @Rule
    public ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class);

    @Before
    public void setup() {
        UserPersistence userPersistence = Services.getUserPersistence();
        User userZero = userPersistence.getUsers().get(0);
        //Remove all socials for User 0
        HashMap<String, String> socials = new HashMap<>();
        socials.putAll(userZero.getSocials());
        socials.forEach(userZero::removeSocialMedia);
    }

    @Test
    public void addSocials() {
        //Log in as User 0
        onView(withId(R.id.editTextUsername)).perform(typeText("0"));
        onView(withId(R.id.editTextPassword)).perform(typeText("password0"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonSubmit)).perform(click());

        //Navigate to socials page
        onView(withId(R.id.socialLinksButton)).perform(click());

        //Navigate to add links page
        onView(withId(R.id.addLinkButton)).perform(click());

        //Add a link for Instagram
        onView(withId(R.id.platformNameInput)).perform(typeText("instagram"));
        onView(withId(R.id.linkInput)).perform(typeText("instagram.com/canada"));
        closeSoftKeyboard();
        onView(withId(R.id.submitSocialsButton)).perform(click());

        //Check the link was added
        onView(withText("https://instagram.com/canada")).check(matches(isDisplayed()));
        onView(withId(R.id.socialsListView)).check(matches(isClickable()));

        //Remove the link
        onView(withId(R.id.removeLinkSwitch)).perform(click());
        onView(withText("https://instagram.com/canada")).perform(click());

        //Check link removal
        onView(withText("https://instagram.com/canada")).check(doesNotExist());
    }
}
