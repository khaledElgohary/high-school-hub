package comp3350.highschoolhub.objects;

public class Request {
    private User sender;
    private User recipient;
    private boolean accepted;

    public Request(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.accepted = false;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
