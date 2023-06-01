package comp3350.highschoolhub.objects;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String bio;
    private String maritalStatus;

    public User(int userId, String firstName, String lastName, String bio, String maritalStatus) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.maritalStatus = maritalStatus;
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
}
