package comp3350.highschoolhub.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;
import comp3350.highschoolhub.persistence.HighSchoolPersistence;

public class UserPersistenceStub implements UserPersistence {
    private ArrayList<User> users;

    public UserPersistenceStub() {

        this.users = new ArrayList<>();
        //Add in default users.
        this.users.add(new User(0, "Purple", "Programmer", "We code in Purple.", "Single", "password0"));
        this.users.add(new User(1, "Test", "User", "Hello I am Test User.", "Married", "password1"));
        this.users.add(new User(2, "Summer", "Fun", "Times are awesome!", "Single", "password2"));
        this.users.add(new User(3, "Eric", "Smith", "How are you today?", "Married", "password3"));
        this.users.add(new User(4, "Bob", "Hugh", "Hello how are you today?", "Married", "password4"));
        this.users.add(new User(5, "Chris", "James", "Hello how are you today?", "Single", "password5"));
        this.users.add(new User(6, "Rob", "Bob", "Hello World", "Married", "password6"));
        this.users.add(new User(7, "Goose", "User", "Hello World", "Married", "password7"));
        this.users.add(new User(8, "Gordan", "Bruns", "Hello World", "Single", "password8"));
        this.users.add(new User(9, "Sally", "Green", "Hello World", "Single", "password9"));

        HighSchoolPersistence highSchoolPersistence = new HighSchoolPersistenceStub();
        List<HighSchool> highSchools = highSchoolPersistence.getHighSchools();

        for (int i = 0; i < users.size(); i++) {

            if (i % 2 == 0) {
                users.get(i).setHighSchool(highSchools.get(0));
            } else {
                users.get(i).setHighSchool(highSchools.get(1));
            }

        }
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

    @Override
    public int countUsers() {
        return users.size();
    }

    @Override
    public User findUser(int userID, String password) {
        User found = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId()==userID && users.get(i).getPassword().equals(password)) {
                found = users.get(i);
            }
        }
        return found;
    }
}
