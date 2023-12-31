package comp3350.highschoolhub.business;

import java.util.Collections;
import java.util.List;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;

//This class is used to provide access to the user persistence, and the user currently logged into the system
public class AccessUsers implements IAccessUsers{

    private UserPersistence userPersistence;

    private List<User> users;

    private User user;

    private int currentUser;

    private static User loggedIn = null;//Keeps track of the user that is logged in.

    private static User seeUserProfile = null;//Keeps track of which user profile to look at.

    private static boolean goBackToConnections = false;//Keeps track if we should go back to the connections page upon leaving the user's profile.

    public AccessUsers() {
        userPersistence = Services.getUserPersistence();
        users = null;
        user = null;
        currentUser = 0;
    }

    //This constructor is used mostly for completing testing of this class.
    public AccessUsers(final UserPersistence userPersistence) {
        this();
        this.userPersistence = userPersistence;
    }

    //Gets the list of users from the persistence.
    public List<User> getUsers() {
        users = userPersistence.getUsers();
        return Collections.unmodifiableList(users);
    }

    //Used to update a user in the persistence
    public boolean updateUser(User user) {return userPersistence.updateUser(user);}

    //Used to insert a new user into the persistence
    public boolean insertUser(User user) {
        return userPersistence.insertUser(user);
    }

    public int getNumUsers() {
        return userPersistence.countUsers();
    }

    public User findUser(int userID, String password) {
        return userPersistence.findUser(userID, password);
    }

    //Get the user that is logged in.
    public static User getLoggedInUser() {
        return loggedIn;
    }

    //Set the user that is logged in.
    public static User setLoggedInUser(User login) {
        loggedIn = login;
        return loggedIn;
    }

    public static void clearLoggedInUser() {
        loggedIn = null;
    }

    //Get the user in which to look at their profile.
    public static User getProfileUser() {
        return seeUserProfile;
    }

    //Set the user to look at their profile.
    public static User setProfileUser(User setUser) {
        seeUserProfile = setUser;
        return seeUserProfile;
    }

    public static boolean goBackToConnections(){
        return goBackToConnections;
    }

    public static void setGoBackToConnections(boolean setBoolean) {
        goBackToConnections = setBoolean;
    }
}
