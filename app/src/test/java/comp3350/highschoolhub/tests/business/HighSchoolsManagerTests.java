package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.business.HighSchoolsManager;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;

public class HighSchoolsManagerTests {
    @Test
    public void testGetUsersFromHighSchool() {
        HighSchoolsManager highSchoolsManager = new HighSchoolsManager();

        User loggedIn = new User(0, "Logged In", "User", "It's summer", "Married", "password0");
        User user1 = new User(1, "User1", "Test1", "Testing", "Single", "password0");
        User user2 = new User(2, "User2", "Test2", "Hello", "Single", "password0");
        User user3 = new User(3, "User3", "Test3", "Hello world", "Married", "password0");

        HighSchool highSchool1 = new HighSchool("Central High School");
        HighSchool highSchool2 = new HighSchool("Summertime High School");

        loggedIn.setHighSchool(highSchool1);
        user1.setHighSchool(highSchool1);
        user2.setHighSchool(highSchool2);
        user3.setHighSchool(highSchool1);

        List<User> userList = new ArrayList<>();
        userList.add(loggedIn);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        List<User> usersFromHighSchool = highSchoolsManager.getUsersFromHighSchool(loggedIn, highSchool1, userList);
        assertNotNull(usersFromHighSchool);
        assertEquals(2, usersFromHighSchool.size());
        assertTrue(usersFromHighSchool.contains(user1));
        assertTrue(usersFromHighSchool.contains(user3));
    }

    @Test
    public void testNullGetUsersFromHighSchool() {
        HighSchoolsManager highSchoolsManager = new HighSchoolsManager();

        List<User> usersFromHighSchool = highSchoolsManager.getUsersFromHighSchool(null, null, null);
        assertNotNull(usersFromHighSchool);
        assertEquals(0, usersFromHighSchool.size());
    }
}
