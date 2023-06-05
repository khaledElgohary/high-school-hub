package comp3350.highschoolhub.tests.business;



import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.business.ConnectionsManager;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;


public class ConnectionManagerTests {

    @Test
    public void testNullGetHighSchoolConnections() {

        ConnectionsManager connectionsManager = new ConnectionsManager();

        List<User> testList = connectionsManager.getHighSchoolConnections(null, null);

        assertNotNull(testList);
        assertTrue(testList.size() == 0);
    }

    @Test
    public void testNullAllUsersGetHighSchoolConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        User loggedIn = new User(0, "testUser", "Test", "User", "Hello World", "Single");

        List<User> testList = connectionsManager.getHighSchoolConnections(loggedIn, null);

        assertNotNull(testList);
        assertTrue(testList.size() == 0);
    }

    @Test
    public void testGetAllHighSchoolConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        HighSchool highSchool = new HighSchool("Central High School");
        User loggedIn = new User(0, "testUser", "Test", "User", "Hello World", "Single");
        loggedIn.setHighSchool(highSchool);
        List<User> allUsers = new ArrayList<User>();

        User user1 = new User(1, "testUser23", "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "testUser63", "Test3", "User63", "Hello World", "Married");
        User user3 = new User(3, "testUser44", "Test4", "User44", "Hello World", "Single");
        User user4 = new User(4, "testUser77", "Test5", "User77", "Hello World", "Single");

        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);
        allUsers.add(user4);

        for(int i = 0; i < allUsers.size(); i++)
        {
            allUsers.get(i).setHighSchool(highSchool);
        }

        List<User> getConnections = connectionsManager.getHighSchoolConnections(loggedIn, allUsers);

        assertNotNull(getConnections);
        assertTrue(getConnections.size() == 4);
        assertTrue(getConnections.contains(user1));
        assertTrue(getConnections.contains(user2));
        assertTrue(getConnections.contains(user3));
        assertTrue(getConnections.contains(user4));
    }

    @Test
    public void testSetRecipient() {
        User user1 = new User(1, "testUser23", "Test2", "User23", "Hello World", "Married");

        ConnectionsManager.setRecipientUser(user1);

        assertEquals(user1, ConnectionsManager.getRecipientUser());
    }

    @Test
    public void testSetNullRecipient() {
        ConnectionsManager.setRecipientUser(null);

        assertNull(ConnectionsManager.getRecipientUser());
    }

    @Test
    public void testSetGetRequest() {
        User user1 = new User(1, "testUser23", "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "testUser63", "Test3", "User63", "Hello World", "Married");
        Request newRequest = new Request(user1, user2);

        ConnectionsManager.setRequest(newRequest);

        assertEquals(newRequest, ConnectionsManager.getRequest());
    }

    @Test
    public void testSetNullRequest() {
        ConnectionsManager.setRequest(null);

        assertNull(ConnectionsManager.getRequest());
    }

    @Test
    public void testStringAcceptRequest() {
        User user1 = new User(1, "testUser23", "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "testUser63", "Test3", "User63", "Hello World", "Married");
        Request newRequest = new Request(user1, user2);

        ConnectionsManager.setRequest(newRequest);

        assertEquals("Accept Request", ConnectionsManager.acceptOrRequest());
    }

    @Test
    public void testStringAccept() {
        ConnectionsManager.setRequest(null);

        assertEquals("Send Request", ConnectionsManager.acceptOrRequest());
    }

    @Test
    public void testUpdateRequestWithNewRequest() {
        User user1 = new User(1, "testUser23", "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "testUser63", "Test3", "User63", "Hello World", "Married");

        ConnectionsManager connectionsManager = new ConnectionsManager();
        ConnectionsManager.setRecipientUser(user1);

        Request newRequest = connectionsManager.updateRequest(user2, null);

        assertNotNull(newRequest);
        assertEquals(newRequest.getRecipient(), user1);
        assertEquals(newRequest.getSender(), user2);
    }

    @Test
    public void testUpdateRequestWithExistingRequest() {
        User user1 = new User(1, "testUser23", "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "testUser63", "Test3", "User63", "Hello World", "Married");
        Request newRequest = new Request(user1, user2);
        ConnectionsManager connectionsManager = new ConnectionsManager();

        ConnectionsManager.setRecipientUser(user2);

        Request updated = connectionsManager.updateRequest(user1, newRequest);

        assertNotNull(updated);
        assertTrue(updated.getAccepted());
        assertEquals(updated.getSender(), user1);
        assertEquals(updated.getRecipient(), user2);
    }

    @Test
    public void testFindRequest() {
        User user1 = new User(1, "testUser23", "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "testUser63", "Test3", "User63", "Hello World", "Married");
        User user3 = new User(3, "testUser44", "Test4", "User44", "Hello World", "Single");
        User user4 = new User(4, "testUser77", "Test5", "User77", "Hello World", "Single");

        List<Request> allRequests = new ArrayList<Request>();

        ConnectionsManager connectionsManager = new ConnectionsManager();

        allRequests.add(new Request(user1, user2));
        allRequests.add(new Request(user1, user3));
        allRequests.add(new Request(user2, user3));
        allRequests.add(new Request(user2, user4));

        Request found = connectionsManager.findRequest(user2, user1, allRequests);
        assertNotNull(found);
        assertEquals(found.getRecipient(), user2);
        assertEquals(found.getSender(), user1);

        found = connectionsManager.findRequest(user1, user2, allRequests);
        assertNull(found);

        found = connectionsManager.findRequest(user3, user1, allRequests);
        assertNotNull(found);
        assertEquals(found.getRecipient(), user3);
        assertEquals(found.getSender(), user1);

        found = connectionsManager.findRequest(user1, user3, allRequests);
        assertNull(found);

        found = connectionsManager.findRequest(user3, user2, allRequests);
        assertNotNull(found);
        assertEquals(found.getRecipient(), user3);
        assertEquals(found.getSender(), user2);

        found = connectionsManager.findRequest(user2, user3, allRequests);
        assertNull(found);

        found = connectionsManager.findRequest(user4, user2, allRequests);
        assertNotNull(found);
        assertEquals(found.getRecipient(), user4);
        assertEquals(found.getSender(), user2);

        found = connectionsManager.findRequest(user2, user4, allRequests);
        assertNull(found);

        found = connectionsManager.findRequest(user3, user4, allRequests);
        assertNull(found);

        found = connectionsManager.findRequest(user4, user3, allRequests);
        assertNull(found);
    }

    @Test
    public void testFindRequestWithNull() {
        User user1 = new User(1, "testUser23", "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "testUser63", "Test3", "User63", "Hello World", "Married");
        User user3 = new User(3, "testUser44", "Test4", "User44", "Hello World", "Single");
        User user4 = new User(4, "testUser77", "Test5", "User77", "Hello World", "Single");

        List<Request> allRequests = new ArrayList<Request>();

        ConnectionsManager connectionsManager = new ConnectionsManager();

        allRequests.add(new Request(user1, user2));
        allRequests.add(new Request(user1, user3));
        allRequests.add(new Request(user2, user3));
        allRequests.add(new Request(user2, user4));

        Request found = connectionsManager.findRequest(user2, user1, null);
        assertNull(found);

        found = connectionsManager.findRequest(user2, null, allRequests);
        assertNull(found);

        found = connectionsManager.findRequest(null, user1, allRequests);
        assertNull(found);

        found = connectionsManager.findRequest(null, null, allRequests);
        assertNull(found);

        found = connectionsManager.findRequest(null, user2, null);
        assertNull(found);

        found = connectionsManager.findRequest(null, null, null);
        assertNull(found);
    }
}
