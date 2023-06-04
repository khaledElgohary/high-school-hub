package comp3350.highschoolhub.persistence.stubs;

import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;

import java.util.List;
import java.util.ArrayList;

public class UserPersistenceStub implements UserPersistence {
    private ArrayList<User> users;

    public UserPersistenceStub() {

        this.users = new ArrayList<User>();
        //Add in default users.
        this.users.add(new User(0,"sgreen20","Sally", "Green", "Hello how are you today?", "Single"));
        this.users.add(new User(1,"testUser88","Test", "User", "Hello I am Test User.", "Married"));
        this.users.add(new User(2,"summerFun21","Summer", "Fun", "Let's have some summer fun today.", "Single"));
        this.users.add(new User(3,"ericSmith","Eric", "Smith", "How are you today?", "Married"));
        this.users.add(new User(4,"bobHugh30","Bob", "Hugh", "Hello how are you today?", "Married"));
        this.users.add(new User(5,"chrisJames","Chris", "James", "Hello how are you today?", "Single"));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User insertUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        int index = users.indexOf(user);

        if (index >= 0) {
            users.set(index, user);
        }

        return user;
    }
}
