package comp3350.highschoolhub.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.highschoolhub.tests.utils.TestUtils;
import comp3350.highschoolhub.business.AccessUsers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessUsersIntegrationTests {

    private AccessUsers accessUsers;

    private File tempDB;

    @Before
    public void setUp() throws IOException{
        this.tempDB = TestUtils.copyDB();
        final UserPersistence persistence = new UserPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessUsers = new AccessUsers(persistence);
    }

    @Test
    public void testGetUsers(){
        List<User> users = accessUsers.getUsers();
        assertEquals(10, users.size());
    }

    @Test
    public void testInsertUser(){
        User newUser = new User(50, "Integration", "Test", "Just testing the seams.", "Deciding", "password50");
        newUser.addHighSchool(new HighSchool("Central High School"));

        assertTrue(accessUsers.insertUser(newUser));

        List<User> users = accessUsers.getUsers();

        boolean found = false;

        for(int i = 0; i < users.size() && !found; i++){
            found = users.get(i).equals(newUser);
        }

        assertTrue(found);
    }

    @Test
    public void testUpdateUser(){
        List<User> users = accessUsers.getUsers();
        User toUpdate = users.get(0);

        toUpdate.addSocialMedia("Facebook", "https://facebook.com/");
        toUpdate.addSocialMedia("Twitter", "https://twitter.com/");
        toUpdate.setMaritalStatus("Deciding");

        assertTrue(accessUsers.updateUser(toUpdate));

        users = accessUsers.getUsers();

        User compare = null;

        for(int i = 0; i < users.size() && compare == null; i++){
            if(toUpdate.equals(users.get(i))){
                compare = users.get(i);
            }
        }

        assertNotNull(compare);

        assertEquals(compare.getMaritalStatus(), toUpdate.getMaritalStatus());
        HashMap<String, String> links1 = compare.getSocials();
        HashMap<String, String> links2 = toUpdate.getSocials();

        assertTrue(links1.containsKey("Facebook"));
        assertTrue(links2.containsKey("Facebook"));

        assertEquals(links1.get("Facebook"), links2.get("Facebook"));

    }

    @Test
    public void testGetNumUsers() {
        int count = accessUsers.getNumUsers();

        assertNotEquals(-1, count);
    }

    @Test
    public void testFindUser() {
        User newUser = new User(accessUsers.getNumUsers()+1, "Test", "User", "Hello World", "Single", "password0");

        accessUsers.insertUser(newUser);
        User foundUser = accessUsers.findUser(newUser.getUserId(), newUser.getPassword());

        assertNotNull(foundUser);
        assertTrue(newUser.equals(foundUser));
    }

    @After
    public void tearDown(){
        this.tempDB.delete();
    }


}
