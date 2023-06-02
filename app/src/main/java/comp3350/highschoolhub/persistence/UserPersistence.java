package comp3350.highschoolhub.persistence;

import comp3350.highschoolhub.objects.User;

import java.util.List;

public interface UserPersistence {
    List<User> getUsers();

    User insertUser(User user);

    User updateUser(User user);
}
