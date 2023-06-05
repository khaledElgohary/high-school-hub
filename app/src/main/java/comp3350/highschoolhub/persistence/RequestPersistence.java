package comp3350.highschoolhub.persistence;

import comp3350.highschoolhub.objects.Request;

import java.util.List;

public interface RequestPersistence {
    List<Request> getRequests();

    Request insertRequest(Request newRequest);

    Request updateRequest(Request updatedRequest);
}
