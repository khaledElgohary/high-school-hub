package comp3350.highschoolhub.business;


import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.persistence.HighSchoolPersistence;
import comp3350.highschoolhub.objects.HighSchool;
import java.util.List;

//This class is used for accessing the high school persistence
public class AccessHighSchools {

    private HighSchoolPersistence highSchoolPersistence;

    private List<HighSchool> highSchools;

    public AccessHighSchools(){
        highSchoolPersistence = Services.getHighSchoolPersistence();
        highSchools = null;
    }

    //This constructor is used mostly for testing this class.
    public AccessHighSchools(final HighSchoolPersistence highSchoolPersistence) {
        this();
        this.highSchoolPersistence = highSchoolPersistence;
    }

    public List<HighSchool> getHighSchools(){
        highSchools = highSchoolPersistence.getHighSchools();
        return highSchools;
    }


}
