package comp3350.highschoolhub.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

//This class is used for managing the Connections UI.
public class ConnectionsManager {

    private static User recipient;

    private static Request request;

    //This method is used to return the list of users that attend the same high school as the logged in user.
    public List<User> getHighSchoolConnections(User loggedIn, List<User> allUsers) {

        //Create a new list to add high school users to.
        List<User> highSchoolConnections = new ArrayList<>();

        //Now go through the allUsers list and add those users who attend the same high school.
        //as long as the loggedIn user has a high school set.
        if (loggedIn != null && allUsers != null && loggedIn.getHighSchool() != null) {

            for (int i = 0; i < allUsers.size(); i++) {
                if (allUsers.get(i).getHighSchool() != null) {

                    if (!(allUsers.get(i).equals(loggedIn)) && allUsers.get(i).getHighSchool().equals(loggedIn.getHighSchool())) {

                        highSchoolConnections.add(allUsers.get(i));
                    }
                }
            }
        }

        //Note that if there are no high school connections then just an empty list will be returned.
        return highSchoolConnections;
    }

    public static User getRecipientUser() {
        return recipient;
    }

    public static void setRecipientUser(User newUser) {
        recipient = newUser;
    }

    public static Request getRequest() {
        return request;
    }

    public static void setRequest(Request newRequest) {
        request = newRequest;
    }

    //Accept or Request
    //This method is used to determine whether the user_connections_options should show accept and cancel
    //or request and cancel.
    public static String acceptOrRequest() {
        String option = "Send Request";

        if (request != null && !request.getAccepted()) {
            option = "Accept Request";
        } else if (request != null && request.getAccepted()) {
            option = "Ok";
        }

        return option;
    }

    public static String getTitleText() {
        String text = "Send Request to User?";

        if (request != null && !request.getAccepted()) {
            text = "Accept User's Request?";
        } else if (request != null && request.getAccepted()) {
            text = "Request has been accepted!";
        }

        return text;
    }

    //This method is used to update a request.
    public Request updateRequest(User selectedUser, Request request) {

        Request updated;

        if (request == null) {
            updated = new Request(selectedUser, recipient);
        } else {
            request.setAccepted(true);
            updated = request;
        }

        return updated;
    }

    //This method is used to determine if there is a request that the selected user sent to the loggedIn user.
    public Request findRequest(User loggedIn, User selected, List<Request> allRequests) {
        Request foundRequest = null;

        if (loggedIn != null && selected != null && allRequests != null) {
            for (int i = 0; i < allRequests.size() && foundRequest == null; i++) {
                if (allRequests.get(i) != null && selected.equals(allRequests.get(i).getSender()) && loggedIn.equals(allRequests.get(i).getRecipient())) {
                    foundRequest = allRequests.get(i);
                }
            }
        }

        return foundRequest;
    }
}
