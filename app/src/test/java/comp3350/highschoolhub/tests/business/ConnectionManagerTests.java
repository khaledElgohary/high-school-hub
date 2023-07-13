package comp3350.highschoolhub.tests.business;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;


public class ConnectionManagerTests {

    @Test
    public void testNullGetHighSchoolConnections() {

        ConnectionsManager connectionsManager = new ConnectionsManager();

        List<User> testList = connectionsManager.getHighSchoolConnections(null, null);

        assertNotNull(testList);
        assertEquals(0, testList.size());
    }

    @Test
    public void testNullAllUsersGetHighSchoolConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        User loggedIn = new User(0, "Test", "User", "Hello World", "Single");

        List<User> testList = connectionsManager.getHighSchoolConnections(loggedIn, null);

        assertNotNull(testList);
        assertEquals(0, testList.size());
    }

    @Test
    public void testGetAllHighSchoolConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        HighSchool highSchool = new HighSchool("Central High School");
        User loggedIn = new User(0, "Test", "User", "Hello World", "Single");
        loggedIn.setHighSchool(highSchool);
        List<User> allUsers = new ArrayList<>();

        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
        User user3 = new User(3, "Test4", "User44", "Hello World", "Single");
        User user4 = new User(4, "Test5", "User77", "Hello World", "Single");

        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);
        allUsers.add(user4);

        for (int i = 0; i < allUsers.size(); i++) {
            allUsers.get(i).setHighSchool(highSchool);
        }

        List<User> getConnections = connectionsManager.getHighSchoolConnections(loggedIn, allUsers);

        assertNotNull(getConnections);
        assertEquals(4, getConnections.size());
        assertTrue(getConnections.contains(user1));
        assertTrue(getConnections.contains(user2));
        assertTrue(getConnections.contains(user3));
        assertTrue(getConnections.contains(user4));
    }

    @Test
    public void testGetMatchingConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        User loggedIn = new User(0, "Test0", "User0", "Test User", "Single");
        User user1 = new User(1, "Test1", "User1", "Hello", "Married");
        User user2 = new User(2, "User2", "Test2", "Goodbye", "Single");
        User user3 = new User(3, "3Te", "st3", "Hi", "Married");
        User otherUser = new User(4, "FirstName", "LastName", "Good Morning", "Single");
        String search = "Test";

        List<User> userList = new ArrayList<>();
        userList.add(loggedIn);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(otherUser);

        List<User> matchingList = connectionsManager.getMatchingConnections(loggedIn, search, userList);
        assertNotNull(matchingList);
        assertEquals(3, matchingList.size());
        assertTrue(matchingList.contains(user1));
        assertTrue(matchingList.contains(user2));
        assertTrue(matchingList.contains(user3));
        assertFalse(matchingList.contains(loggedIn));
        assertFalse(matchingList.contains(otherUser));
    }

    @Test
    public void testNullInputGetMatchingConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        List<User> matchingList = connectionsManager.getMatchingConnections(null, null, null);
        assertNotNull(matchingList);
        assertEquals(0, matchingList.size());
    }

    @Test
    public void testEmptyListGetMatchingConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        User loggedIn = new User(0, "Test0", "User0", "Test User", "Single");
        String search = "Test";
        List<User> userList = new ArrayList<>();

        List<User> matchingList = connectionsManager.getMatchingConnections(loggedIn, search, userList);

        assertNotNull(matchingList);
        assertEquals(0, matchingList.size());
    }

    @Test
    public void testCaseInsensitiveGetMatchingConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        User loggedIn = new User(0, "Logged", "In", "Logged In User", "Single");
        User user1 = new User(1, "Test1", "User1", "Testing1", "Married");
        User user2 = new User(2, "Test2", "User2", "Testing2", "Single");
        User user3 = new User(3, "Test3", "User3", "Testing3", "Married");
        String search1 = "test";
        String search2 = "tEsT";
        String search3 = "TEST";

        List<User> userList = new ArrayList<>();
        userList.add(loggedIn);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        List<User> matchingList1 = connectionsManager.getMatchingConnections(loggedIn, search1, userList);
        assertEquals(3, matchingList1.size());

        List<User> matchingList2 = connectionsManager.getMatchingConnections(loggedIn, search2, userList);
        assertEquals(3, matchingList2.size());

        List<User> matchingList3 = connectionsManager.getMatchingConnections(loggedIn, search3, userList);
        assertEquals(3, matchingList3.size());
    }

    @Test
    public void testSetRecipient() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");

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
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
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
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
        Request newRequest = new Request(user1, user2);

        ConnectionsManager.setRequest(newRequest);

        assertEquals("Accept Request", ConnectionsManager.acceptOrRequest());
    }

    @Test
    public void testStringSendRequest() {
        ConnectionsManager.setRequest(null);

        assertEquals("Send Request", ConnectionsManager.acceptOrRequest());
    }

    @Test
    public void testStringOk() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
        Request newRequest = new Request(user1, user2);
        newRequest.setAccepted(true);

        ConnectionsManager.setRequest(newRequest);

        assertEquals("Ok", ConnectionsManager.acceptOrRequest());
    }

    @Test
    public void testStringAcceptTitleString() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
        Request newRequest = new Request(user1, user2);

        ConnectionsManager.setRequest(newRequest);

        assertEquals("Accept User's Request?", ConnectionsManager.getTitleText());
    }

    @Test
    public void testStringSendRequestTitle() {
        ConnectionsManager.setRequest(null);

        assertEquals("Send Request to User?", ConnectionsManager.getTitleText());
    }

    @Test
    public void testStringOkTitle() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
        Request newRequest = new Request(user1, user2);
        newRequest.setAccepted(true);

        ConnectionsManager.setRequest(newRequest);

        assertEquals("Request has been accepted!", ConnectionsManager.getTitleText());
    }

    @Test
    public void testUpdateRequestWithNewRequest() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");

        ConnectionsManager connectionsManager = new ConnectionsManager();
        ConnectionsManager.setRecipientUser(user1);

        Request newRequest = connectionsManager.updateRequest(user2, null);

        assertNotNull(newRequest);
        assertEquals(newRequest.getRecipient(), user1);
        assertEquals(newRequest.getSender(), user2);
    }

    @Test
    public void testUpdateRequestWithExistingRequest() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
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
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
        User user3 = new User(3, "Test4", "User44", "Hello World", "Single");
        User user4 = new User(4, "Test5", "User77", "Hello World", "Single");

        List<Request> allRequests = new ArrayList<>();

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
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
        User user3 = new User(3, "Test4", "User44", "Hello World", "Single");
        User user4 = new User(4, "Test5", "User77", "Hello World", "Single");

        List<Request> allRequests = new ArrayList<>();

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
