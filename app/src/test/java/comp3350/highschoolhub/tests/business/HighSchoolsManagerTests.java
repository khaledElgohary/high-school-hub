package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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

        loggedIn.addHighSchool(highSchool1);
        user1.addHighSchool(highSchool1);
        user2.addHighSchool(highSchool2);
        user3.addHighSchool(highSchool1);

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

    @Test
    public void testGetHighSchoolNames() {
        HighSchoolsManager highSchoolsManager = new HighSchoolsManager();

        User user = new User(0, "Test", "User", "Hello", "Single", "password0");
        user.addHighSchool(new HighSchool("Summer High School"));
        user.addHighSchool(new HighSchool("Winter High School"));
        user.addHighSchool(new HighSchool("Autumn High School"));

        assertEquals("Summer High School, Winter High School, Autumn High School", highSchoolsManager.getHighSchoolNames(user));
    }

    @Test
    public void testOneGetHighSchoolNames() {
        HighSchoolsManager highSchoolsManager = new HighSchoolsManager();

        User user = new User(0, "Test", "User", "Hello", "Single", "password0");
        user.addHighSchool(new HighSchool("Summer High School"));

        assertEquals("Summer High School", highSchoolsManager.getHighSchoolNames(user));
    }

    @Test
    public void testEmptyGetHighSchoolNames() {
        HighSchoolsManager highSchoolsManager = new HighSchoolsManager();

        User user = new User(0, "Test", "User", "Hello", "Single", "password0");

        assertEquals("", highSchoolsManager.getHighSchoolNames(user));
    }

    @Test
    public void testNullGetHighSchoolNames() {
        HighSchoolsManager highSchoolsManager = new HighSchoolsManager();
        assertEquals("", highSchoolsManager.getHighSchoolNames(null));
    }

    @Test
    public void testProcessNewHighSchool() {
        HighSchoolsManager highSchoolsManager = new HighSchoolsManager();

        HighSchool summerHighSchool = new HighSchool("Summer High School");
        HighSchool winterHighSchool = new HighSchool("Winter High School");
        HighSchool autumnHighSchool = new HighSchool("Autumn High School");
        HighSchool springHighSchool = new HighSchool("Spring High School");

        List<HighSchool> testList = new ArrayList<>();
        testList.add(summerHighSchool);
        testList.add(winterHighSchool);
        testList.add(autumnHighSchool);

        testList = highSchoolsManager.processNewHighSchool(summerHighSchool, testList);

        assertEquals(2, testList.size());
        assertFalse(testList.contains(summerHighSchool));

        testList = highSchoolsManager.processNewHighSchool(springHighSchool, testList);

        assertEquals(3, testList.size());
        assertTrue(testList.contains(springHighSchool));
    }

    @Test
    public void testNullProcessNewHighSchool() {
        HighSchoolsManager highSchoolsManager = new HighSchoolsManager();
        assertNotNull(highSchoolsManager.processNewHighSchool(null, null));
    }
}
