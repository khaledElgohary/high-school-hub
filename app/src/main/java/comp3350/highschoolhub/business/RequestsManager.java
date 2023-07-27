package comp3350.highschoolhub.business;

import java.util.List;

import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

public class RequestsManager implements IRequestsManager {
    private static User recipient;

    private static Request request;

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

    //This method is used to return the other user from a reuqest that is not logged in.
    public User getOtherUser(User loggedIn, Request request) {
        User theUser = null;

        if(request != null && request.getSender().equals(loggedIn)) {
            theUser = request.getRecipient();
        }
        else if(request != null) {
            theUser = request.getSender();
        }

        return theUser;
    }
}
