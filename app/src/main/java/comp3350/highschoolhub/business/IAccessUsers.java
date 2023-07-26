package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.User;

public interface IAccessUsers {

    List<User> getUsers();

    boolean updateUser(User user);

    boolean insertUser(User user);

    int getNumUsers();

    User findUser(int userID, String password);
}
