package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.objects.HighSchool;
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

        User loggedIn = new User(0, "Test", "User", "Hello World", "Single", "password0");

        List<User> testList = connectionsManager.getHighSchoolConnections(loggedIn, null);

        assertNotNull(testList);
        assertEquals(0, testList.size());
    }

    @Test
    public void testGetAllHighSchoolConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        HighSchool highSchool = new HighSchool("Central High School");
        User loggedIn = new User(0, "Test", "User", "Hello World", "Single", "password0");
        loggedIn.addHighSchool(highSchool);
        List<User> allUsers = new ArrayList<>();

        User user1 = new User(1, "Test2", "User23", "Hello World", "Married", "password1");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married", "password2");
        User user3 = new User(3, "Test4", "User44", "Hello World", "Single", "password3");
        User user4 = new User(4, "Test5", "User77", "Hello World", "Single", "password4");

        allUsers.add(user1);
        allUsers.add(user2);
        allUsers.add(user3);
        allUsers.add(user4);

        for (int i = 0; i < allUsers.size(); i++) {
            allUsers.get(i).addHighSchool(highSchool);
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

        User loggedIn = new User(0, "Test0", "User0", "Test User", "Single", "password0");
        User user1 = new User(1, "Test1", "User1", "Hello", "Married", "password1");
        User user2 = new User(2, "User2", "Test2", "Goodbye", "Single", "password2");
        User user3 = new User(3, "3Te", "st3", "Hi", "Married", "password3");
        User otherUser = new User(4, "FirstName", "LastName", "Good Morning", "Single", "password4");
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

        User loggedIn = new User(0, "Test0", "User0", "Test User", "Single", "password0");
        String search = "Test";
        List<User> userList = new ArrayList<>();

        List<User> matchingList = connectionsManager.getMatchingConnections(loggedIn, search, userList);

        assertNotNull(matchingList);
        assertEquals(0, matchingList.size());
    }

    @Test
    public void testCaseInsensitiveGetMatchingConnections() {
        ConnectionsManager connectionsManager = new ConnectionsManager();

        User loggedIn = new User(0, "Logged", "In", "Logged In User", "Single", "password0");
        User user1 = new User(1, "Test1", "User1", "Testing1", "Married", "password1");
        User user2 = new User(2, "Test2", "User2", "Testing2", "Single", "password2");
        User user3 = new User(3, "Test3", "User3", "Testing3", "Married", "password3");
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
}
