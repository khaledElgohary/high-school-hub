package comp3350.highschoolhub.objects;

import java.util.ArrayList;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String bio;
    private String maritalStatus;
    private HighSchool highSchool;
    private ArrayList<User> connections;

    private String password;

    public User(int userId, String firstName, String lastName, String bio, String maritalStatus) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.maritalStatus = maritalStatus;
        this.highSchool = null;
        this.connections = new ArrayList<>();
        this.password = null;
    }

    public User(int userId, String firstName, String lastName, String bio, String maritalStatus,
                String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.maritalStatus = maritalStatus;
        this.highSchool = null;
        this.connections = new ArrayList<>();
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public HighSchool getHighSchool() {
        return highSchool;
    }

    public ArrayList<User> getConnections() {
        return connections;
    }

    public String getPassword() { return password; }

    public void setHighSchool(HighSchool highSchool) {
        this.highSchool = highSchool;
    }

    public void addConnection(User connection) {
        connections.add(connection);
    }

    public void setPassword(String password) { this.password = password; }
}
