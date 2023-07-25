package comp3350.highschoolhub.persistence;

import java.util.List;

import comp3350.highschoolhub.objects.User;

public interface UserPersistence {
    List<User> getUsers();

    boolean insertUser(User user);

    boolean updateUser(User user);

    int countUsers();

    User findUser(int userID, String password);
}
