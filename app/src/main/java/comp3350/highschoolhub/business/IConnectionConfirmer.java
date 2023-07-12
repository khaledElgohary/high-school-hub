package comp3350.highschoolhub.business;

import comp3350.highschoolhub.objects.User;

public interface IConnectionConfirmer {
    boolean areConnected(User loggedIn, User other);
}
