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
                if (!user.equals(loggedIn) && user.getHighSchools() != null
                        && user.getHighSchools().contains(highSchool)) {
                    usersFromHighSchool.add(user);
                }
            }
        }
        return usersFromHighSchool;
    }

    //This method converts the high schools of a user into a string for output
    public String getHighSchoolNames(User user) {
        StringBuilder highSchoolNames = new StringBuilder();

        if (user != null) {
            for (HighSchool highSchool : user.getHighSchools()) {
                if (highSchoolNames.length() == 0) {
                    highSchoolNames.append(highSchool.getName());
                } else {
                    highSchoolNames.append(", ").append(highSchool.getName());
                }
            }
        }

        return highSchoolNames.toString();
    }

    //This method either adds or removes a high school depending on whether the high school
    //is already in the given list
    public List<HighSchool> processNewHighSchool(HighSchool newHighSchool, List<HighSchool> highSchools) {
        List<HighSchool> processedHighSchools = new ArrayList<>();

        if (newHighSchool != null) {
            processedHighSchools.addAll(highSchools);

            if (highSchools.contains(newHighSchool)) {
                processedHighSchools.remove(newHighSchool);
            } else {
                processedHighSchools.add(newHighSchool);
            }
        }

        return processedHighSchools;
    }
}
