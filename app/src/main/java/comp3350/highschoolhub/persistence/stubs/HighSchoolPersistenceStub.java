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
        highSchools.add(new HighSchool("École Secondaire Sisler"));
        highSchools.add(new HighSchool("Gordon Bell High School"));
        highSchools.add(new HighSchool("Fort Richmond Collegiate"));
        highSchools.add(new HighSchool("Grant Park High School"));

        UserPersistence userPersistence = Services.getUserPersistence();
        List<User> users = userPersistence.getUsers();

        for (int i = 0; i < users.size(); i++) {

            if (i % 2 == 0) {
                users.get(i).setHighSchool(highSchools.get(0));
            } else {
                users.get(i).setHighSchool(highSchools.get(1));
            }

        }
    }

    @Override
    public List<HighSchool> getHighSchools() {
        return highSchools;
    }
}
