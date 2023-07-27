package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.business.RequestsManager;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

public class RequestsManagerTests {
    @Test
    public void testSetRecipient() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password1");

        RequestsManager.setRecipientUser(user1);

        assertEquals(user1, RequestsManager.getRecipientUser());
    }

    @Test
    public void testSetNullRecipient() {
        RequestsManager.setRecipientUser(null);

        assertNull(RequestsManager.getRecipientUser());
    }

    @Test
    public void testSetGetRequest() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password1");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password2");
        Request newRequest = new Request(user1, user2);

        RequestsManager.setRequest(newRequest);

        assertEquals(newRequest, RequestsManager.getRequest());
    }

    @Test
    public void testSetNullRequest() {
        RequestsManager.setRequest(null);

        assertNull(RequestsManager.getRequest());
    }

    @Test
    public void testStringAcceptRequest() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password1");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password2");
        Request newRequest = new Request(user1, user2);

        RequestsManager.setRequest(newRequest);

        assertEquals("Accept Request", RequestsManager.acceptOrRequest());
    }

    @Test
    public void testStringSendRequest() {
        RequestsManager.setRequest(null);

        assertEquals("Send Request", RequestsManager.acceptOrRequest());
    }

    @Test
    public void testStringOk() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password1");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password2");
        Request newRequest = new Request(user1, user2);
        newRequest.setAccepted(true);

        RequestsManager.setRequest(newRequest);

        assertEquals("Ok", RequestsManager.acceptOrRequest());
    }

    @Test
    public void testStringAcceptTitleString() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password1");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password2");
        Request newRequest = new Request(user1, user2);

        RequestsManager.setRequest(newRequest);

        assertEquals("Accept User's Request?", RequestsManager.getTitleText());
    }

    @Test
    public void testStringSendRequestTitle() {
        RequestsManager.setRequest(null);

        assertEquals("Send Request to User?", RequestsManager.getTitleText());
    }

    @Test
    public void testStringOkTitle() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password1");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password2");
        Request newRequest = new Request(user1, user2);
        newRequest.setAccepted(true);

        RequestsManager.setRequest(newRequest);

        assertEquals("Request has been accepted!", RequestsManager.getTitleText());
    }

    @Test
    public void testUpdateRequestWithNewRequest() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password1");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password2");

        RequestsManager requestsManager = new RequestsManager();
        RequestsManager.setRecipientUser(user1);

        Request newRequest = requestsManager.updateRequest(user2, null);

        assertNotNull(newRequest);
        assertEquals(newRequest.getRecipient(), user1);
        assertEquals(newRequest.getSender(), user2);
    }

    @Test
    public void testUpdateRequestWithExistingRequest() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password0");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password0");
        Request newRequest = new Request(user1, user2);
        RequestsManager requestsManager = new RequestsManager();

        RequestsManager.setRecipientUser(user2);

        Request updated = requestsManager.updateRequest(user1, newRequest);

        assertNotNull(updated);
        assertTrue(updated.getAccepted());
        assertEquals(updated.getSender(), user1);
        assertEquals(updated.getRecipient(), user2);
    }

    @Test
    public void testFindRequest() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password0");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password0");
        User user3 = new User(3, "Test4", "User44", "Hello World", "Single", "password0");
        User user4 = new User(4, "Test5", "User77", "Hello World", "Single", "password0");

        List<Request> allRequests = new ArrayList<>();

        RequestsManager requestsManager = new RequestsManager();

        allRequests.add(new Request(user1, user2));
        allRequests.add(new Request(user1, user3));
        allRequests.add(new Request(user2, user3));
        allRequests.add(new Request(user2, user4));

        Request found = requestsManager.findRequest(user2, user1, allRequests);
        assertNotNull(found);
        assertEquals(found.getRecipient(), user2);
        assertEquals(found.getSender(), user1);

        found = requestsManager.findRequest(user1, user2, allRequests);
        assertNull(found);

        found = requestsManager.findRequest(user3, user1, allRequests);
        assertNotNull(found);
        assertEquals(found.getRecipient(), user3);
        assertEquals(found.getSender(), user1);

        found = requestsManager.findRequest(user1, user3, allRequests);
        assertNull(found);

        found = requestsManager.findRequest(user3, user2, allRequests);
        assertNotNull(found);
        assertEquals(found.getRecipient(), user3);
        assertEquals(found.getSender(), user2);

        found = requestsManager.findRequest(user2, user3, allRequests);
        assertNull(found);

        found = requestsManager.findRequest(user4, user2, allRequests);
        assertNotNull(found);
        assertEquals(found.getRecipient(), user4);
        assertEquals(found.getSender(), user2);

        found = requestsManager.findRequest(user2, user4, allRequests);
        assertNull(found);

        found = requestsManager.findRequest(user3, user4, allRequests);
        assertNull(found);

        found = requestsManager.findRequest(user4, user3, allRequests);
        assertNull(found);
    }

    @Test
    public void testFindRequestWithNull() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password0");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password0");
        User user3 = new User(3, "Test4", "User44", "Hello World", "Single", "password0");
        User user4 = new User(4, "Test5", "User77", "Hello World", "Single", "password0");

        List<Request> allRequests = new ArrayList<>();

        RequestsManager requestsManager = new RequestsManager();

        allRequests.add(new Request(user1, user2));
        allRequests.add(new Request(user1, user3));
        allRequests.add(new Request(user2, user3));
        allRequests.add(new Request(user2, user4));

        Request found = requestsManager.findRequest(user2, user1, null);
        assertNull(found);

        found = requestsManager.findRequest(user2, null, allRequests);
        assertNull(found);

        found = requestsManager.findRequest(null, user1, allRequests);
        assertNull(found);

        found = requestsManager.findRequest(null, null, allRequests);
        assertNull(found);

        found = requestsManager.findRequest(null, user2, null);
        assertNull(found);

        found = requestsManager.findRequest(null, null, null);
        assertNull(found);
    }

    @Test
    public void testGetOtherUser() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password0");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password0");
        Request request = new Request(user1, user2);
        RequestsManager requestsManager = new RequestsManager();

        assertEquals(requestsManager.getOtherUser(user1, request), user2);
        assertEquals(requestsManager.getOtherUser(user2, request), user1);
    }
}
