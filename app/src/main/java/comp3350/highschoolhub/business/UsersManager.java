package comp3350.highschoolhub.business;

import comp3350.highschoolhub.objects.User;

public class UsersManager implements IUsersManager {

    @Override
    public boolean validateUser() {
        return false;
    }

    private boolean checkForDuplicateUser(User newUser) {

        return false;
    }

    public boolean validateEmail() {
        return false;
    }
}
