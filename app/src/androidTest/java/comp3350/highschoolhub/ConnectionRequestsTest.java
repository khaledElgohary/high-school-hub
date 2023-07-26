package comp3350.highschoolhub;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.presentation.Login;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ConnectionRequestsTest {
    @Rule
    public ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class);

    @Before
    public void setupDatabase() {
        RequestPersistence requestPersistence = Services.getRequestPersistence();
        UserPersistence userPersistence = Services.getUserPersistence();
        List<Request> requests = requestPersistence.getRequests();

        List<User> users = userPersistence.getUsers();
        List<HighSchool> highSchools = null;

        //Make sure we can verify the first name on the UI and make sure both user 0 and user 8 have Central High School
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == 0) {
                users.get(i).changeName("Purple", users.get(i).getLastName());
                userPersistence.updateUser(users.get(i));
            }

            if (users.get(i).getUserId() == 0 || users.get(i).getUserId() == 8)
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

        //Set up the requests so that all requests sent or received by user 0 are not accepted.
        for(int i = 0; i < requests.size(); i++) {
            if(requests.get(i).getSender().getUserId() == 0 || requests.get(i).getRecipient().getUserId() == 0) {
                requests.get(i).setAccepted(false);
                requestPersistence.updateRequest(requests.get(i));
            }
        }
    }

    @Test
    public void sendConnectionRequest() {

        //Login in as user 0
        onView(withId(R.id.editTextUsername)).perform(typeText("0"));
        onView(withId(R.id.editTextPassword)).perform(typeText("password0"));
        onView(withId(R.id.buttonSubmit)).perform(click());

        //Navigate to My Connections
        onView(withId(R.id.backToConnections)).perform(click());

        //Select the third item in the list.
        onData(anything()).inAdapterView(withId(R.id.userConnections)).atPosition(3).perform(click());
        onView(withId(R.id.acceptRequestButton)).perform(click());
        onView(withId(R.id.backToMyProfile)).perform(click());
        onView(withId(R.id.logout)).perform(click());

        //Login as Gordan user 8.
        onView(withId(R.id.editTextUsername)).perform(typeText("8"));
        onView(withId(R.id.editTextPassword)).perform(typeText("password8"));
        onView(withId(R.id.buttonSubmit)).perform(click());

        //Navigate to My Connections
        onView(withId(R.id.backToConnections)).perform(click());

        //Select the third item in the list.
        onData(anything()).inAdapterView(withId(R.id.userConnections)).atPosition(0).perform(click());
        onView(withId(R.id.acceptRequestButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.userConnections)).atPosition(0).perform(click());
        onView(withId(R.id.userFirstName)).check(matches(withText("Purple")));

    }
}
