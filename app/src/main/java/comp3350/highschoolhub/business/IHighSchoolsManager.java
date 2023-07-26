package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;

public interface IHighSchoolsManager {
    List<User> getUsersFromHighSchool(User loggedIn, HighSchool highSchool, List<User> allUsers);
    String getHighSchoolNames(User user);
    List<HighSchool> processNewHighSchool(HighSchool newHighSchool, List<HighSchool> highSchools);
}
