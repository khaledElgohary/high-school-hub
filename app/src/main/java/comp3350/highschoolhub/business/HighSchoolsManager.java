package comp3350.highschoolhub.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;

public class HighSchoolsManager implements IHighSchoolsManager {
    //This method gets a list of all the users that have the specified high school in their profile
    public List<User> getUsersFromHighSchool(User loggedIn, HighSchool highSchool, List<User> allUsers) {
        List<User> usersFromHighSchool = new ArrayList<>();

        if (allUsers != null && loggedIn != null) {
            for (User user : allUsers) {
                if (user != loggedIn && user.getHighSchool() != null
                        && user.getHighSchool().equals(highSchool)) {
                    usersFromHighSchool.add(user);
                }
            }
        }

        return usersFromHighSchool;
    }
}
