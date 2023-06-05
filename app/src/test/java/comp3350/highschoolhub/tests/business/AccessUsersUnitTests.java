package comp3350.highschoolhub.tests.business;

import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.stubs.UserPersistenceStub;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.List;

public class AccessUsersUnitTests {

    private AccessUsers accessUsers;

    @Before
    public void setUp(){
        accessUsers = new AccessUsers(new UserPersistenceStub());
    }

    @Test
    public void testSetLoggedInUser() {
        User newUser = new User(0, "test", "Test", "User", "Hello World", "Single");

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
        User newUser = new User(0, "test", "Test", "User", "Hello World", "Single");

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
        User user = new User(0, "test", "Test", "User", "Hello World", "Single");

        assertEquals(user, accessUsers.insertUser(user));
    }

    @Test
    public void testUpdateUser() {
        List<User> users;
        User user = new User(0, "test", "Test", "User", "Hello World", "Single");
        accessUsers.insertUser(user);

        users = accessUsers.getUsers();
        User findUser = null;

        for(int i = 0; i < users.size() && findUser == null; i++){
            if(users.get(i).equals(user))
            {
                findUser = users.get(i);
            }
        }

        //For this test we will just update the marital status to make sure the updateUser method works correctly.
        findUser.setMaritalStatus("Married");

        assertEquals(findUser.getMaritalStatus(), accessUsers.updateUser(findUser).getMaritalStatus());
    }
}
