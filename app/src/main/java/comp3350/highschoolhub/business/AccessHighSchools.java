package comp3350.highschoolhub.business;


import java.util.List;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.persistence.HighSchoolPersistence;

//This class is used for accessing the high school persistence
public class AccessHighSchools {

    private HighSchoolPersistence highSchoolPersistence;

    private List<HighSchool> highSchools;

    //This constructor is used mostly for testing this class.
    public AccessHighSchools(final HighSchoolPersistence highSchoolPersistence) {
        this.highSchoolPersistence = highSchoolPersistence;
        highSchools = null;
    }

    public AccessHighSchools() {
        new AccessHighSchools(Services.getHighSchoolPersistence());
    }


    public List<HighSchool> getHighSchools() {
        highSchools = highSchoolPersistence.getHighSchools();
        return highSchools;
    }


}
