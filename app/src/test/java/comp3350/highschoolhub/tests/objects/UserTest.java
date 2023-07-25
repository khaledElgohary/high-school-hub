package comp3350.highschoolhub.tests.objects;

import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.objects.HighSchool;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class UserTest {
    @Test
    public void testUserCreateWithPassword() {
        System.out.println("Starting testUserCreateWithPassword.");

        User user = new User(0, "Test", "User", "Hello world!", "Single", "Password0");

        assertEquals(user.getUserId(), 0);
        assertEquals(user.getFirstName(), "Test");
        assertEquals(user.getLastName(), "User");
        assertEquals(user.getBio(), "Hello world!");
        assertEquals(user.getMaritalStatus(), "Single");
        assertTrue(user.getHighSchools().isEmpty());
        assertNotNull(user.getSocials());
        assertTrue(user.getSocials().isEmpty());
        assertEquals(user.getPassword(), "Password0");

        System.out.println("Finished testUserCreateWithPassword.");
    }

    @Test
    public void testAddHighSchool() {
        System.out.println("Starting testAddHighSchool.");

        User user = new User(0, "Test", "User", "Hello world!", "Single", "password0");
        HighSchool highSchool = new HighSchool("Generic High School");
        user.addHighSchool(highSchool);

        assertEquals(user.getHighSchools().get(0), highSchool);

        System.out.println("Finished testAddHighSchool.");
    }

    @Test
    public void testRemoveHighSchool() {
        System.out.println("Starting testRemoveHighSchool.");

        User user = new User(0, "Test", "User", "Hello world!", "Single, password0");
        HighSchool highSchool1 = new HighSchool("Generic High School");
        HighSchool highSchool2 = new HighSchool("Interesting High School");

        user.addHighSchool(highSchool1);
        user.removeHighSchool(highSchool1);

        assertEquals(0, user.getHighSchools().size());

        user.addHighSchool(highSchool2);
        user.removeHighSchool(new HighSchool("Test High School"));

        assertEquals(1, user.getHighSchools().size());
        assertTrue(user.getHighSchools().contains(highSchool2));

        System.out.println("Finished testRemoveHighSchool.");
    }

    @Test
    public void testSetHighSchools() {
        System.out.println("Starting testSetHighSchools.");

        User user = new User(0, "Test", "User", "Hello world!", "Single, password0");
        HighSchool highSchool1 = new HighSchool("Generic High School");
        HighSchool highSchool2 = new HighSchool("Interesting High School");

        List<HighSchool> testList = new ArrayList<>();
        testList.add(highSchool1);
        testList.add(highSchool2);

        user.setHighSchools(testList);

        assertEquals(2, user.getHighSchools().size());
        assertTrue(user.getHighSchools().contains(highSchool1));
        assertTrue(user.getHighSchools().contains(highSchool2));

        user.setHighSchools(null);

        assertNull(user.getHighSchools());

        System.out.println("Finished testSetHighSchools.");
    }

    @Test
    public void testAddSocialMedia() {
        System.out.println("Starting testAddSocialMedia.");

        User user1 = new User(0, "Test", "User", "Hello world!", "Single", "password0");
        user1.addSocialMedia("Facebook", "facebook.com/test.user");

        assertTrue(user1.getSocials().containsKey("Facebook"));
        assertTrue(user1.getSocials().containsValue("facebook.com/test.user"));
        assertEquals(user1.getSocials().size(), 1);

        System.out.println("Finished testAddSocialMedia.");
    }

    @Test
    public void testGetUserName() {
        User user1 = new User(0, "Test", "User", "Hello world!", "Single", "password0");

        assertEquals(user1.getUserName(), "TestUser");
    }
}
