package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

//this class tells us whether two users are connected
public class ConnectionConfirmer implements IConnectionConfirmer{

    private IRequestsManager requestsManager;

    private IAccessRequests accessRequests;

    public boolean areConnected(User loggedIn, User other) {
        boolean connected = false;
        requestsManager = new RequestsManager();
        accessRequests = new AccessRequests();

        List<Request> requestList = accessRequests.getRequests();

        Request theRequest = requestsManager.findRequest(loggedIn, other, requestList);

        if(theRequest != null) {
            connected = theRequest.getAccepted();
        }

        return connected;
    }
}
