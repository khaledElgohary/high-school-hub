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

    public User(int userId, String firstName, String lastName, String bio, String maritalStatus) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.maritalStatus = maritalStatus;
        this.highSchool = null;
        this.connections = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() { return this.firstName + this.lastName; }

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

    public void setMaritalStatus(String newStatus){ this.maritalStatus = newStatus; }

    public HighSchool getHighSchool() {
        return highSchool;
    }

    public ArrayList<User> getConnections() {
        return connections;
    }

    public void setHighSchool(HighSchool highSchool) {
        this.highSchool = highSchool;
    }

    public void addConnection(User connection) {
        connections.add(connection);
    }

    public boolean equals(User user) { return this.userId == user.userId; }
}
