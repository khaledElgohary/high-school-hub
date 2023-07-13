package comp3350.highschoolhub.business;

import comp3350.highschoolhub.objects.User;

//This class is to access privacy information, simply return strings
public class PrivacyManager implements IPrivacyManager{
    private final String publicInfo = "First name\nLast name\nHigh School";

    private final String privateInfo = "UserID\nBio\nMarital Status\nSocial Links";

    @Override
    public String getPublicInfoString() {
        return publicInfo;
    }

    @Override
    public String getPrivateInfoString() {
        return privateInfo;
    }
}
