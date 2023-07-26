package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.stubs.UserPersistenceStub;

public class AccessUsersUnitTests {

    private AccessUsers accessUsers;

    @Before
    public void setUp() {
        accessUsers = new AccessUsers(new UserPersistenceStub());
    }

    @Test
    public void testSetLoggedInUser() {
        User newUser = new User(0, "Test", "User", "Hello World", "Single", "password0");

        assertEquals(newUser, AccessUsers.setLoggedInUser(newUser));
        assertEquals(newUser, AccessUsers.getLoggedInUser());
    }

    @Test
    public void testSetNullLoggedInUser() {

        assertNull(AccessUsers.setLoggedInUser(null));
    }

    @Test
    public void testSetNullProfileUser() {

        assertNull(AccessUsers.setProfileUser(null));
    }

    @Test
    public void testGetProfileUser() {
        User newUser = new User(0, "Test", "User", "Hello World", "Single", "password0");

        assertEquals(newUser, AccessUsers.setProfileUser(newUser));
        assertEquals(newUser, AccessUsers.getProfileUser());

    }

    @Test
    public void testGetUsers() {
        List<User> users = accessUsers.getUsers();

        assertNotNull(users);
    }

    @Test
    public void testInsertUser() {
        User user = new User(0, "Test", "User", "Hello World", "Single", "password0");

        assertTrue(accessUsers.insertUser(user));
    }

    @Test
    public void testUpdateUser() {
        List<User> users;
        User user = new User(0, "Test", "User", "Hello World", "Single", "password0");
        accessUsers.insertUser(user);

        users = accessUsers.getUsers();
        User findUser = null;

        for (int i = 0; i < users.size() && findUser == null; i++) {
            if (users.get(i).equals(user)) {
                findUser = users.get(i);
            }
        }

        //For this test we will just update the marital status to make sure the updateUser method works correctly.
        if (findUser != null) {
            findUser.setMaritalStatus("Married");
        }

        boolean returned = accessUsers.updateUser(findUser);

        assertTrue(returned);
    }

    @Test
    public void testGetNumUsers() {
        int count = accessUsers.getNumUsers();

        assertNotEquals(-1, count);
    }

    @Test
    public void testFindUser() {
        User newUser = new User(0, "Test", "User", "Hello World", "Single", "password0");
        accessUsers.insertUser(newUser);
        User foundUser = accessUsers.findUser(newUser.getUserId(), newUser.getPassword());

        assertNotNull(foundUser);
        assertEquals(newUser, foundUser);
    }

    @Test
    public void testGoBackToConnections() {
        AccessUsers.setGoBackToConnections(false);

        assertFalse(AccessUsers.goBackToConnections());

        AccessUsers.setGoBackToConnections(true);

        assertTrue(AccessUsers.goBackToConnections());
    }
}
