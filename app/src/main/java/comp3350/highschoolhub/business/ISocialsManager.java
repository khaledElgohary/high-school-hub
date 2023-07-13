package comp3350.highschoolhub.business;

import comp3350.highschoolhub.objects.User;

public interface ISocialsManager {
    boolean addLink(User loggedIn, String platform, String link)
            throws InvalidLinkException, InvalidPlatformException;

}
