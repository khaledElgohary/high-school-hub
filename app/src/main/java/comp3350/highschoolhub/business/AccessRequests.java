package comp3350.highschoolhub.business;

import java.util.Collections;
import java.util.List;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.persistence.RequestPersistence;

//This class is used to access the Requests Persistence
public class AccessRequests implements IAccessRequests{

    private RequestPersistence requestPersistence;

    private List<Request> requests;

    public AccessRequests() {
        requestPersistence = Services.getRequestPersistence();
        requests = null;
    }

    //This constructor is used mostly for completing testing of this class.
    public AccessRequests(final RequestPersistence requestPersistence) {
        this();
        this.requestPersistence = requestPersistence;
    }

    public List<Request> getRequests() {
        requests = requestPersistence.getRequests();
        return Collections.unmodifiableList(requests);
    }

    public boolean insertRequest(Request request) {
        return requestPersistence.insertRequest(request);
    }

    public boolean updateRequest(Request request) {
        return requestPersistence.updateRequest(request);
    }

}
