package comp3350.highschoolhub.persistence.stubs;

import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;

import java.util.List;
import java.util.ArrayList;

public class UserPersistenceStub implements UserPersistence {
    private ArrayList<User> users;

    public UserPersistenceStub() {
        this.users = new ArrayList<>();
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
