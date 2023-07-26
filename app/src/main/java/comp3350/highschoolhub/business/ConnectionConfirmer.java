package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

//this class tells us whether two users are connected
public class ConnectionConfirmer implements IConnectionConfirmer{

    private IConnectionsManager connectionsManager;

    private IAccessRequests accessRequests;

    public boolean areConnected(User loggedIn, User other) {
        boolean connected = false;
        connectionsManager = new ConnectionsManager();
        accessRequests = new AccessRequests();

        List<Request> requestList = accessRequests.getRequests();

        Request theRequest = connectionsManager.findRequest(loggedIn, other, requestList);

        if(theRequest != null) {
            connected = theRequest.getAccepted();
        }

        return connected;
    }
}
