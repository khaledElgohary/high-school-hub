package comp3350.highschoolhub.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.HighSchoolPersistence;
import comp3350.highschoolhub.persistence.UserPersistence;

public class HighSchoolPersistenceStub implements HighSchoolPersistence {
    private ArrayList<HighSchool> highSchools;

    public HighSchoolPersistenceStub() {

        this.highSchools = new ArrayList<>();

        //Create some default high schools.
        highSchools.add(new HighSchool("Central High School"));
        highSchools.add(new HighSchool("Summertime High School"));
        highSchools.add(new HighSchool("Kelvin High School"));
        highSchools.add(new HighSchool("Ecole Secondaire Sisler"));
        highSchools.add(new HighSchool("Gordon Bell High School"));
        highSchools.add(new HighSchool("Fort Richmond Collegiate"));
        highSchools.add(new HighSchool("Grant Park High School"));

    }

    @Override
    public List<HighSchool> getHighSchools() {
        return highSchools;
    }
}
