package comp3350.highschoolhub.persistence.stubs;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.persistence.HighSchoolPersistence;

import java.util.List;
import java.util.ArrayList;

public class HighSchoolPersistenceStub implements HighSchoolPersistence {
    private ArrayList<HighSchool> highSchools;

    public HighSchoolPersistenceStub() {
        this.highSchools = new ArrayList<>();
    }

    @Override
    public List<HighSchool> getHighSchools() {
        return highSchools;
    }
}
