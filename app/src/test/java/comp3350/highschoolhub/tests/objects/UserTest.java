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
        assertNotNull(user.getConnections());
        assertTrue(user.getConnections().isEmpty());

        System.out.println("Finished testUserCreate.");
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
    public void testAddConnection() {
        System.out.println("Starting testAddConnection.");

        User user1 = new User(0, "Test", "User", "Hello world!", "Single");
        User user2 = new User(1, "Summer", "Sun", "It's hot outside!", "Single");
        user1.addConnection(user2);

        assertEquals(user1.getConnections().size(), 1);
        assertTrue(user1.getConnections().contains(user2));
        assertFalse(user2.getConnections().contains(user1));

        System.out.println("Finished testAddConnection.");
    }
}
