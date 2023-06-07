package comp3350.highschoolhub.application;

import comp3350.highschoolhub.persistence.HighSchoolPersistence;
import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.persistence.stubs.HighSchoolPersistenceStub;
import comp3350.highschoolhub.persistence.stubs.RequestPersistenceStub;
import comp3350.highschoolhub.persistence.stubs.UserPersistenceStub;

//This class is used for keeping track of the persistence objects.
public class Services {

    private static UserPersistence userPersistence = null;

    private static RequestPersistence requestPersistence = null;

    private static HighSchoolPersistence highSchoolPersistence = null;

    //Get the user persistence
    public static synchronized UserPersistence getUserPersistence() {

        //If there is no UserPersistence set then we create a new one.
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }

        return userPersistence;
    }

    //Get the request persistence
    public static synchronized RequestPersistence getRequestPersistence() {

        //If there is no request persistence then create a new one.
        if (requestPersistence == null) {
            requestPersistence = new RequestPersistenceStub();
        }

        return requestPersistence;
    }


    //Get the high school persistence
    public static synchronized HighSchoolPersistence getHighSchoolPersistence() {

        //If there is no request persistence then create a new one.
        if (highSchoolPersistence == null) {
            highSchoolPersistence = new HighSchoolPersistenceStub();
        }

        return highSchoolPersistence;
    }


}
