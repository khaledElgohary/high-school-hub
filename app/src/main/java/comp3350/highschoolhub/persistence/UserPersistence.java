package comp3350.highschoolhub.persistence;

import java.util.List;

import comp3350.highschoolhub.objects.User;

public interface UserPersistence {
    List<User> getUsers();

    User insertUser(User user);

    User updateUser(User user);
}
