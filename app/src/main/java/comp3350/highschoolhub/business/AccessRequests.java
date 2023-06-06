package comp3350.highschoolhub.business;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.objects.Request;
import java.util.List;
import java.util.Collections;

//This class is used to access the Requests Persistence
public class AccessRequests {

    private RequestPersistence requestPersistence;

    private List<Request> requests;

    public AccessRequests(){
        requestPersistence = Services.getRequestPersistence();
        requests = null;
    }

    //This constructor is used mostly for completing testing of this class.
    public AccessRequests(final RequestPersistence requestPersistence) {
        this();
        this.requestPersistence = requestPersistence;
    }

    public List<Request> getRequests(){
        requests = requestPersistence.getRequests();
        return Collections.unmodifiableList(requests);
    }

    public Request insertRequest(Request request){
        return requestPersistence.insertRequest(request);
    }

    public Request updateRequest(Request request) {
        return requestPersistence.updateRequest(request);
    }

}
