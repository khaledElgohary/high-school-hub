package comp3350.highschoolhub.persistence.hsqldb;

import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;

import java.util.List;

public class UserPersistenceHSQLDB implements UserPersistence {

    @Override
    public List<User> getUsers() {

        return null;
    }

    @Override
    public boolean insertUser(User user) {

        return true;
    }

    @Override
    public boolean updateUser(User user) {

        return true;
    }
}
