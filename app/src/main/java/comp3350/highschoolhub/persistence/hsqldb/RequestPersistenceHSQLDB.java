package comp3350.highschoolhub.persistence.hsqldb;

import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.objects.Request;

import java.util.List;

public class RequestPersistenceHSQLDB implements RequestPersistence {

    @Override
    public List<Request> getRequests() {

        return null;
    }

    @Override
    public boolean insertRequest(Request request) {

        return true;
    }

    @Override
    public boolean updateRequest(Request request) {

        return true;
    }
}
