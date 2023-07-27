package comp3350.highschoolhub.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.highschoolhub.objects.User;

//This class is used for managing the Connections UI.
public class ConnectionsManager implements IConnectionsManager{

    //This method is used to return the list of users that attend the same high school as the logged in user.
    public List<User> getHighSchoolConnections(User loggedIn, List<User> allUsers) {

        //Create a new list to add high school users to.
        List<User> highSchoolConnections = new ArrayList<>();

        //Now go through the allUsers list and add those users who attend the same high school.
        //as long as the loggedIn user has a high school set.
        if (loggedIn != null && allUsers != null && loggedIn.getHighSchools() != null) {

            for (int i = 0; i < allUsers.size(); i++) {
                if (!allUsers.get(i).getHighSchools().isEmpty()) {

                    if (!(allUsers.get(i).equals(loggedIn)) && !Collections.disjoint(allUsers.get(i).getHighSchools(), loggedIn.getHighSchools())) {

                        highSchoolConnections.add(allUsers.get(i));
                    }
                }
            }
        }

        //Note that if there are no high school connections then just an empty list will be returned.
        return highSchoolConnections;
    }

    //This method finds users with names matching the given input (case insensitive)
    public List<User> getMatchingConnections(User loggedIn, String search, List<User> allUsers) {
        List<User> matchingUsers = new ArrayList<>();

        if (loggedIn != null && search != null && allUsers != null) {
            String searchToLower = search.toLowerCase();

            for (int i = 0; i < allUsers.size(); i++) {
                if (!allUsers.get(i).equals(loggedIn)) {
                    if (allUsers.get(i).getUserName().toLowerCase().contains(searchToLower)
                            || allUsers.get(i).getFirstName().toLowerCase().contains(searchToLower)
                            || allUsers.get(i).getLastName().toLowerCase().contains(searchToLower)) {
                        matchingUsers.add(allUsers.get(i));
                    }
                }
            }
        }

        return matchingUsers;
    }

}
