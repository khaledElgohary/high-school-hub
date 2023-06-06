package comp3350.highschoolhub.persistence.stubs;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.persistence.HighSchoolPersistence;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.objects.User;

import java.util.List;
import java.util.ArrayList;

public class HighSchoolPersistenceStub implements HighSchoolPersistence {
    private ArrayList<HighSchool> highSchools;

    public HighSchoolPersistenceStub() {

        this.highSchools = new ArrayList<>();

        //Create some default high schools.
        this.highSchools.add(new HighSchool("Central High School"));
        this.highSchools.add(new HighSchool("Summertime High School"));

        UserPersistence userPersistence = Services.getUserPersistence();
        List<User> users = userPersistence.getUsers();

        for(int i = 0; i < users.size(); i++) {

            if(i % 2 == 0){
                users.get(i).setHighSchool(highSchools.get(0));
            }
            else {
                users.get(i).setHighSchool(highSchools.get(1));
            }

        }
    }

    @Override
    public List<HighSchool> getHighSchools() {
        return highSchools;
    }
}
