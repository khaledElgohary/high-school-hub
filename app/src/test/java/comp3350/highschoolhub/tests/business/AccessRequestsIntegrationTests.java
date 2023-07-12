package comp3350.highschoolhub.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3350.highschoolhub.business.AccessRequests;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.hsqldb.RequestPersistenceHSQLDB;
import comp3350.highschoolhub.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.highschoolhub.tests.utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AccessRequestsIntegrationTests {

    private AccessRequests accessRequests;

    private AccessUsers accessUsers;

    private File tempDB;

    @Before
    public void setUp() throws IOException{
        this.tempDB = TestUtils.copyDB();
        final RequestPersistence persistence = new RequestPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        final UserPersistenceHSQLDB userPersistence = new UserPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessRequests = new AccessRequests(persistence);
        this.accessUsers = new AccessUsers(userPersistence);
    }

    @Test
    public void testGetRequests(){
        List<Request> requests = accessRequests.getRequests();
        assertEquals(13, requests.size());
    }

    @Test
    public void testInsertRequest(){
        List<User> users = accessUsers.getUsers();
        User sender = users.get(0);
        User recipient = users.get(9);
        Request newRequest = new Request(sender, recipient);

        assertTrue(accessRequests.insertRequest(newRequest));

        List<Request> requests = accessRequests.getRequests();

        boolean found = false;

        for(int i = 0; i < requests.size() && !found; i++){
            found = requests.get(i).equals(newRequest);
        }

        assertTrue(found);
    }

    @Test
    public void testUpdateRequest(){

        List<Request> requests = accessRequests.getRequests();

        Request falseRequest = requests.get(5);
        Request trueRequest = requests.get(6);


        trueRequest.setAccepted(false);
        falseRequest.setAccepted(true);

        assertTrue(accessRequests.updateRequest(trueRequest));
        assertTrue(accessRequests.updateRequest(falseRequest));


        boolean foundTrueRequest = false;
        boolean foundFalseRequest = false;

        for(int i = 0; i < requests.size() && !foundTrueRequest && !foundFalseRequest; i++){
            if(requests.get(i).equals(trueRequest)) {
                foundTrueRequest = true;
            }
            if(requests.get(i).equals(trueRequest)) {
                foundFalseRequest = true;
            }
        }

        assertTrue(foundTrueRequest);
        assertTrue(foundFalseRequest);
    }

    @After
    public void tearDown(){
        this.tempDB.delete();
    }
}
