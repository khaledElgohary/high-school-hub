package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

public interface IRequestsManager {
    Request updateRequest(User selectedUser, Request request);

    Request findRequest(User loggedIn, User selected, List<Request> allRequests);

    User getOtherUser(User loggedIn, Request request);
}
