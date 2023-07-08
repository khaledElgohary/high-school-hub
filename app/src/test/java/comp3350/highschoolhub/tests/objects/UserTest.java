package comp3350.highschoolhub.tests.objects;

import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.objects.HighSchool;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testUserCreate() {
        System.out.println("Starting testUserCreate.");

        User user = new User(0, "Test", "User", "Hello world!", "Single");

        assertEquals(user.getUserId(), 0);
        assertEquals(user.getFirstName(), "Test");
        assertEquals(user.getLastName(), "User");
        assertEquals(user.getBio(), "Hello world!");
        assertEquals(user.getMaritalStatus(), "Single");
        assertNull(user.getHighSchool());
        assertTrue(user.getSocials().isEmpty());

        System.out.println("Finished testUserCreate.");
    }

    @Test
    public void testUserCreateWithPassword() {
        System.out.println("Starting testUserCreateWithPassword.");

        User user = new User(0, "Test", "User", "Hello world!", "Single", "Password0");

        assertEquals(user.getUserId(), 0);
        assertEquals(user.getFirstName(), "Test");
        assertEquals(user.getLastName(), "User");
        assertEquals(user.getBio(), "Hello world!");
        assertEquals(user.getMaritalStatus(), "Single");
        assertNull(user.getHighSchool());
        assertNotNull(user.getConnections());
        assertTrue(user.getConnections().isEmpty());
        assertEquals(user.getPassword(), "Password0");

        System.out.println("Finished testUserCreateWithPassword.");
    }

    @Test
    public void testSetHighSchool() {
        System.out.println("Starting testSetHighSchool.");

        User user = new User(0, "Test", "User", "Hello world!", "Single");
        HighSchool highSchool = new HighSchool("Generic High School");
        user.setHighSchool(highSchool);

        assertEquals(user.getHighSchool(), highSchool);

        System.out.println("Finished testSetHighSchool.");
    }

    @Test
    public void testAddSocialMedia() {
        System.out.println("Starting testAddSocialMedia.");

        User user1 = new User(0, "Test", "User", "Hello world!", "Single");
        user1.addSocialMedia("Facebook", "facebook.com/test.user");

        assertTrue(user1.getSocials().containsKey("Facebook"));
        assertTrue(user1.getSocials().containsValue("facebook.com/test.user"));
        assertEquals(user1.getSocials().size(), 1);

        System.out.println("Finished testAddSocialMedia.");
    }

    @Test
    public void testGetUserName() {
        User user1 = new User(0, "Test", "User", "Hello world!", "Single");

        assertEquals(user1.getUserName(), "TestUser");
    }
}
