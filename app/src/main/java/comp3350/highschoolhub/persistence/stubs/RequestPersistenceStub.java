package comp3350.highschoolhub.persistence.stubs;

import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.persistence.RequestPersistence;

import java.util.List;
import java.util.ArrayList;

public class RequestPersistenceStub implements RequestPersistence {
    private ArrayList<Request> requests;

    public RequestPersistenceStub() {
        this.requests = new ArrayList<>();
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }
}
