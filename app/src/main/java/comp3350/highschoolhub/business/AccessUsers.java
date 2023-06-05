package comp3350.highschoolhub.business;

import comp3350.highschoolhub.application.Services;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.objects.User;
import java.util.List;
import java.util.Collections;

//This class is used to provide access to the user persistence, and the user currently logged into the system
public class AccessUsers {

    private UserPersistence userPersistence;

    private List<User> users;

    private static User loggedIn = null;//Keeps track of the user that is logged in.

    private static User seeUserProfile = null;//Keeps track of which user profile to look at.

    public AccessUsers(){
        userPersistence = Services.getUserPersistence();
        users = null;
    }

    //This constructor is used mostly for completing testing of this class.
    public AccessUsers(final UserPersistence userPersistence) {
        this();
        this.userPersistence = userPersistence;
    }

    //Gets the list of users from the persistence.
    public List<User> getUsers(){
        users = userPersistence.getUsers();
        return Collections.unmodifiableList(users);
    }

    //Used to update a user in the persistence
    public User updateUser(User user){
        return userPersistence.updateUser(user);
    }

    //Used to insert a new user into the persistence
    public User insertUser(User user){
        user = userPersistence.insertUser(user);
        return user;
    }

    //Get the user that is logged in.
    public static User getLoggedInUser(){
        return loggedIn;
    }

    //Set the user that is logged in.
    public static User setLoggedInUser(User login){
        loggedIn = login;
        return loggedIn;
    }

    //Get the user in which to look at their profile.
    public static User getProfileUser() { return seeUserProfile; }

    //Set the user to look at their profile.
    public static User setProfileUser(User setUser) {
        seeUserProfile = setUser;
        return seeUserProfile;
    }
}
