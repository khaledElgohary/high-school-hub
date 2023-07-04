package comp3350.highschoolhub.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;

public class UserPersistenceStub implements UserPersistence {
    private ArrayList<User> users;

    public UserPersistenceStub() {

        this.users = new ArrayList<>();
        //Add in default users.
        this.users.add(new User(0, "Purple", "Programmer", "We code in Purple.", "Single"));
        this.users.add(new User(1, "Test", "User", "Hello I am Test User.", "Married"));
        this.users.add(new User(2, "Summer", "Fun", "Let's have some summer fun today.", "Single"));
        this.users.add(new User(3, "Eric", "Smith", "How are you today?", "Married"));
        this.users.add(new User(4, "Bob", "Hugh", "Hello how are you today?", "Married"));
        this.users.add(new User(5, "Chris", "James", "Hello how are you today?", "Single"));
        this.users.add(new User(6, "Rob", "Bob", "Hello World", "Married"));
        this.users.add(new User(7, "Goose", "User", "Hello World", "Married"));
        this.users.add(new User(8, "Gordan", "Bruns", "Hello World", "Single"));
        this.users.add(new User(9, "Sally", "Green", "Hello World", "Single"));
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public boolean insertUser(User user) {
        return users.add(user);
    }

    @Override
    public boolean updateUser(User user) {
        int index = users.indexOf(user);
        boolean updated = false;

        if (index >= 0) {
            users.set(index, user);
            updated = true;
        }

        return updated;
    }
}
