package comp3350.highschoolhub.persistence.stubs;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.objects.User;

import java.util.List;
import java.util.ArrayList;

public class RequestPersistenceStub implements RequestPersistence {
    private ArrayList<Request> requests;

    public RequestPersistenceStub() {

        this.requests = new ArrayList<>();
        //Set default data
        UserPersistence userPersistence = Services.getUserPersistence();
        List<User> users = userPersistence.getUsers();
        int numUsers = users.size();

        for(int i = 1; i < numUsers; i++) {

            Request newRequest = new Request(users.get(i - 1), users.get(i));

            if(i % 2 == 0){
                newRequest.setAccepted(true);
            }

            this.requests.add(newRequest);
        }
        Request myRequest = new Request(users.get(2), users.get(0));
        this.requests.add(myRequest);
    }

    @Override
    public List<Request> getRequests() {
        return requests;
    }

    @Override
    public Request insertRequest(Request newRequest) {
        Request returnedRequest = null;
        //Make sure duplicates are not added.
        boolean found = false;
        int i = 0;
        while(i < requests.size() && !found)
        {
            found = newRequest.equals(requests.get(i));
            i++;
        }

        if(!found) {
            requests.add(newRequest);
            returnedRequest = newRequest;
        }

        return returnedRequest;
    }

    @Override
    public Request updateRequest(Request request) {
        Request updatedRequest;
        int index = requests.indexOf(request);

        if(index < 0)
        {
            updatedRequest = insertRequest(request);
        }
        else
        {
            requests.remove(index);
            requests.add(request);
            updatedRequest = request;
        }

        return request;
    }
}
