package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;

public interface IHighSchoolsManager {
    public List<User> getUsersFromHighSchool(User loggedIn, HighSchool highSchool, List<User> allUsers);
}
