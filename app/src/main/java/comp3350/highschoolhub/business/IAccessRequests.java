package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.Request;

public interface IAccessRequests {

    List<Request> getRequests();

    boolean insertRequest(Request request);

    boolean updateRequest(Request request);
}
