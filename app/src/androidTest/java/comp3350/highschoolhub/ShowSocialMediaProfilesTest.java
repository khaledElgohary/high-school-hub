package comp3350.highschoolhub;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.presentation.Login;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowSocialMediaProfilesTest {
    @Rule
    public ActivityTestRule<Login> activityRule = new ActivityTestRule<>(Login.class);

    @Before
    public void setupDatabase() {
        RequestPersistence requestPersistence = Services.getRequestPersistence();
        UserPersistence userPersistence = Services.getUserPersistence();
        List<Request> requests = requestPersistence.getRequests();

        //Make sure userID = 2 has a social link so they have atleast one link,
        //has Central High School as their highschool and has a name
        User summerFun = userPersistence.findUser(2, "password2");
        summerFun.addSocialMedia("Test", "https://test.com/user2");
        summerFun.changeName("Summer", "Fun");

        boolean hasRequiredSchool = false;
        List<HighSchool> schools = summerFun.getHighSchools();
        for(int i = 0; i < schools.size() && !hasRequiredSchool; i++) {
            hasRequiredSchool = schools.get(i).getName().equals("Central High School");
        }
        if(!hasRequiredSchool) {
            summerFun.addHighSchool(new HighSchool("Central High School"));
        }
        userPersistence.updateUser(summerFun);

        //Set up the requests so that all requests sent or received by user 0 are accepted.
        for(int k = 0; k < requests.size(); k++) {
            if(requests.get(k).getSender().getUserId() == 0 ||
                    requests.get(k).getRecipient().getUserId() == 0)  {
                requests.get(k).setAccepted(true);
                requestPersistence.updateRequest(requests.get(k));
            }
        }
    }

    @Test
    public void showConnectedUserLinks() {

        //Login in as user 0
        onView(withId(R.id.editTextUsername)).perform(typeText("0"));
        onView(withId(R.id.editTextPassword)).perform(typeText("password0"));
        onView(withId(R.id.buttonSubmit)).perform(click());

        //Click ExploreHighSchool button to navigate to ExploreHighSchool from MyProfile
        onView(withId(R.id.button3)).perform(click());

        //Click on search button to go view users
        onView(withId(R.id.searchButton)).perform(click());

        //Select Summer Fun from list of users (first one)
        onData(anything()).inAdapterView(withId(R.id.usersList)).atPosition(0).perform(click());

        //Check if at least one link is shown
        onData(anything()).inAdapterView(withId(R.id.userLinks)).atPosition(0).perform(click());
    }

}
