package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import comp3350.highschoolhub.business.AccessRequests;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.stubs.RequestPersistenceStub;

public class AccessRequestsUnitTests {

    private AccessRequests accessRequests;

    @Before
    public void setUp() {
        accessRequests = new AccessRequests(new RequestPersistenceStub());
    }

    @Test
    public void testGetRequests() {
        List<Request> requests = accessRequests.getRequests();

        assertNotNull(requests);
    }

    @Test
    public void testInsertRequests() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
        Request newRequest = new Request(user1, user2);
        accessRequests.insertRequest(newRequest);

        List<Request> requests = accessRequests.getRequests();

        boolean requestFound = false;

        for (int i = 0; i < requests.size() && !requestFound; i++) {
            requestFound = requests.get(i).equals(newRequest);
        }

        assertTrue(requestFound);
    }

    @Test
    public void testUpdateRequests() {
        User user1 = new User(1, "Test2", "User23", "Hello World", "Married");
        User user2 = new User(2, "Test3", "User63", "Hello World", "Married");
        Request newRequest = new Request(user1, user2);

        accessRequests.insertRequest(newRequest);

        List<Request> requests = accessRequests.getRequests();

        Request requestFound = null;

        for (int i = 0; i < requests.size() && requestFound == null; i++) {
            if (requests.get(i).equals(newRequest)) {
                requestFound = requests.get(i);
            }
        }

        if (requestFound != null) {
            requestFound.setAccepted(true);
        }

        Request returned = accessRequests.updateRequest(requestFound);

        assertEquals(returned, requestFound);
    }
}
