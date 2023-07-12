package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

public interface IConnectionsManager {

    List<User> getHighSchoolConnections(User loggedIn, List<User> allUsers);

    List<User> getMatchingConnections(User loggedIn, String search, List<User> allUsers);

    Request updateRequest(User selectedUser, Request request);

    Request findRequest(User loggedIn, User selected, List<Request> allRequests);
    
}
