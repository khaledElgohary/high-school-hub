package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.User;

public interface IConnectionsManager {

    List<User> getHighSchoolConnections(User loggedIn, List<User> allUsers);

    List<User> getMatchingConnections(User loggedIn, String search, List<User> allUsers);
    
}
