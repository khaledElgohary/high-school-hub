package comp3350.highschoolhub;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.presentation.Login;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserPageTest {
    @Rule
    public ActivityTestRule<Login> activityRule=new ActivityTestRule<>(Login.class);

    @Before
    public void setupDatabase(){
        UserPersistence userPersistence=Services.getUserPersistence();
        List<User> users= userPersistence.getUsers();
        //We will pick 2 random users to test if all their info is displayed
        //User 2 and 4 will be our choice

        /*need to pick user first and last name, bio, and status*/
        //high school is predicted to be Central High School
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserId()==2){
                users.get(i).changeName("John","Edelman");
                users.get(i).changeStatus("Married");
                users.get(i).changeBio("Hello World !!!");
                userPersistence.updateUser(users.get(i));
            }
            else if(users.get(i).getUserId()==4){
                users.get(i).changeName("Michael","Kors");
                users.get(i).changeStatus("Single");
                users.get(i).changeBio("Hello World Part 2 !!!");
                userPersistence.updateUser(users.get(i));
            }
        }

    }

    @Test
    public void navigateUserPage(){

        //logging in as user 2
        onView(withId(R.id.editTextUsername)).perform(typeText("2"));
        onView(withId(R.id.editTextPassword)).perform(typeText("password2"));
        onView(withId(R.id.buttonSubmit)).perform(click());

        //Checking the displayed info matches what we changed for the users
        onView(withId(R.id.user_name)).check(matches(withText("John Edelman")));
        onView(withId(R.id.Bio)).check(matches(withText("Hello World !!!")));
        onView(withId(R.id.marital_status)).check(matches(withText("Married")));
        onView(withId(R.id.profile_image)).check(matches(isDisplayed()));
        onView(withId(R.id.highSchoolsButton)).perform(click());
        onView(withText("Central High School")).check(matches(isDisplayed()));
        //logout
        onView(withId(R.id.backToMyProfile)).perform(click());
        onView(withId(R.id.logout)).perform(click());
        //logging in as user 4
        onView(withId(R.id.editTextUsername)).perform(typeText("4"));
        onView(withId(R.id.editTextPassword)).perform(typeText("password4"));
        onView(withId(R.id.buttonSubmit)).perform(click());

        //Checking the displayed info matches what we changed for the users
        onView(withId(R.id.user_name)).check(matches(withText("Michael Kors")));
        onView(withId(R.id.Bio)).check(matches(withText("Hello World Part 2 !!!")));
        onView(withId(R.id.marital_status)).check(matches(withText("Single")));
        onView(withId(R.id.profile_image)).check(matches(isDisplayed()));
        onView(withId(R.id.highSchoolsButton)).perform(click());
        onView(withText("Central High School")).check(matches(isDisplayed()));
    }

}
