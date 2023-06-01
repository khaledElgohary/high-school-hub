package comp3350.highschoolhub.objects;

public class Request {
    private User sender;
    private User recipient;

    public Request(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }
}
