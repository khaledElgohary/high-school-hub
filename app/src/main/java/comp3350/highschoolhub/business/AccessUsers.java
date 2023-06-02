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

    private User user;

    private int currentUser;

    private static User loggedIn = null;//Keeps track of the user that is logged in.

    public AccessUsers(){
        userPersistence = Services.getUserPersistence();
        users = null;
        user = null;
        currentUser = 0;
    }

    //Gets the list of users from the persistence.
    public List<User> getUsers(){
        users = userPersistence.getUsers();
        return Collections.unmodifiableList(users);
    }

    //Used to update a user in the persistence
    public User updateUser(User user){
        user = userPersistence.updateUser(user);
        return user;
    }

    //Used to insert a new user into the persistence
    public User insertUser(User user){
        user = userPersistence.insertUser(user);
        return user;
    }

    //Get the user that is logged in.
    public User getLoggedInUser(){
        return loggedIn;
    }

    //Set the user that is logged in.
    public User setLoggedInUser(User login){
        loggedIn = login;
        return loggedIn;
    }
}
