package comp3350.highschoolhub.tests.objects;

import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
    @Test
    public void testRequestCreate() {
        System.out.println("Starting testRequestCreate");

        User user1 = new User(0,"testUser", "Test", "User", "Hello world!", "Single");
        User user2 = new User(1,"summerSun", "Summer", "Sun", "It's hot outside!", "Single");
        Request request = new Request(user1, user2);

        assertEquals(request.getSender(), user1);
        assertEquals(request.getRecipient(), user2);
        assertFalse(request.getAccepted());

        System.out.println("Finished testRequestCreate");
    }

    @Test
    public void testSetAccepted() {
        System.out.println("Starting testSetAccepted");

        User user1 = new User(0,"testUser", "Test", "User", "Hello world!", "Single");
        User user2 = new User(1,"summerSun", "Summer", "Sun", "It's hot outside!", "Single");
        Request request = new Request(user1, user2);
        request.setAccepted(true);

        assertTrue(request.getAccepted());

        System.out.println("Finished testSetAccepted");
    }
}
