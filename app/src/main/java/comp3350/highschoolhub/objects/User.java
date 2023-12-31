package comp3350.highschoolhub.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String bio;
    private String maritalStatus;
    private List<HighSchool> highSchools;
    private HashMap<String, String> socials;
    private String password;

    public User(int userId, String firstName, String lastName, String bio, String maritalStatus,
                String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.maritalStatus = maritalStatus;
        this.highSchools = new ArrayList<>();
        this.socials = new HashMap<>();
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return this.firstName + this.lastName;
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

    public void setMaritalStatus(String newStatus) {
        this.maritalStatus = newStatus;
    }

    public List<HighSchool> getHighSchools() {
        return highSchools;
    }

    public void addHighSchool(HighSchool highSchool) {
        highSchools.add(highSchool);
    }

    public void removeHighSchool(HighSchool highSchool) {
        highSchools.remove(highSchool);
    }

    public void setHighSchools(List<HighSchool> highSchools) {
        this.highSchools = highSchools;
    }

    public HashMap<String, String> getSocials() {
        return socials;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public void addSocialMedia(String type, String link) {
        socials.put(type, link);
    }

    public boolean equals(User user) {
        return this.userId == user.userId;
    }

    public void changeName(String newFirst,String newLast){
        this.firstName=newFirst;
        this.lastName=newLast;
    }

    public void changeBio(String newBio){
        this.bio=newBio;
    }

    public void changeStatus(String newStatus){
        this.maritalStatus=newStatus;
    }
    public void removeSocialMedia(String type, String link) { socials.remove(type, link); }

}
