package comp3350.highschoolhub.persistence;

import java.util.List;

import comp3350.highschoolhub.objects.Request;

public interface RequestPersistence {
    List<Request> getRequests();

    Request insertRequest(Request newRequest);

    Request updateRequest(Request updatedRequest);
}
