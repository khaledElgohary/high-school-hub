package comp3350.highschoolhub.business;


import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.persistence.HighSchoolPersistence;
import comp3350.highschoolhub.objects.HighSchool;
import java.util.List;
import java.util.Collections;

//This class is used for accessing the high school persistence
public class AccessHighSchools {

    private HighSchoolPersistence highSchoolPersistence;

    private List<HighSchool> highSchools;

    public AccessHighSchools(){
        highSchoolPersistence = Services.getHighSchoolPersistence();
        highSchools = null;
    }

    public List<HighSchool> getHighSchool(){
        highSchools = highSchoolPersistence.getHighSchools();
        return highSchools;
    }


}
