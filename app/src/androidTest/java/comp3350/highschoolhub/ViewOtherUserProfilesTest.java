package comp3350.highschoolhub;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.presentation.Login;
import comp3350.highschoolhub.objects.User;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ViewOtherUserProfilesTest {

    @Rule
    public ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class);

    @Before
    public void setupDatabase() {
        RequestPersistence requestPersistence = Services.getRequestPersistence();
        UserPersistence userPersistence = Services.getUserPersistence();

        List<User> users = userPersistence.getUsers();
        List<HighSchool> highSchools = null;

        //Make sure we can verify the first name and last name on the UI and make sure user 8 have Central High School
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == 0) {
                users.get(i).changeName("Purple", users.get(i).getLastName());
                userPersistence.updateUser(users.get(i));
            }

            if (users.get(i).getUserId() == 8) {
                highSchools = users.get(i).getHighSchools();
                boolean found = false;


                //Set up the user data for user 8.
                users.get(i).changeName("Gordan", "Bruns");

                for (int j = 0; j < highSchools.size() && !found; j++) {
                    found = highSchools.get(j).getName().equals("Central High School");
                }

                if (!found) {
                    users.get(i).addHighSchool(new HighSchool("Central High School"));
                }

                userPersistence.updateUser(users.get(i));
            }
            else if (users.get(i).getUserId() == 2 || users.get(i).getUserId() == 4 || users.get(i).getUserId() == 6) {
                highSchools = users.get(i).getHighSchools();
                boolean found = false;


                for (int j = 0; j < highSchools.size() && !found; j++) {
                    found = highSchools.get(j).getName().equals("Central High School");
                }

                if (!found) {
                    users.get(i).addHighSchool(new HighSchool("Central High School"));
                }

                userPersistence.updateUser(users.get(i));
            }
            else {
                highSchools = users.get(i).getHighSchools();
                boolean found = false;


                for (int j = 0; j < highSchools.size() && !found; j++) {
                    found = highSchools.get(j).getName().equals("Central High School");
                }

                if (found) {
                    users.get(i).removeHighSchool(new HighSchool("Central High School"));
                }

                userPersistence.updateUser(users.get(i));
            }
        }

    }

    @Test
    public void testViewOtherProfile() {

        //Login in as user 0
        onView(withId(R.id.editTextUsername)).perform(typeText("0"));
        onView(withId(R.id.editTextPassword)).perform(typeText("password0"));
        onView(withId(R.id.buttonSubmit)).perform(click());

        //Navigate to Explore High Schools
        onView(withId(R.id.button3)).perform(click());

        //Select Central High School
        onView(withId(R.id.searchButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.usersList)).atPosition(3).perform(click());

        onView(withId(R.id.userFirstName)).check(matches(withText("Gordan")));
        onView(withId(R.id.userLastName)).check(matches(withText("Bruns")));
        onView(withId(R.id.userHighSchool)).check(matches(withText("Central High School")));
    }
}
