package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.PrivacyManager;
import comp3350.highschoolhub.objects.User;

public class PrivacyManagerUnitTests {

    private final String publicInfo = "First name\nLast name\nHigh School";

    private final PrivacyManager privacyManager = new PrivacyManager();

    @Test
    public void testGetPublicInfoString()
    {
        System.out.println("Beginning testGetPublicInfoString");

        assertNotNull(privacyManager.getPublicInfoString());
        assertEquals(privacyManager.getPublicInfoString(), publicInfo);

        System.out.println("Ending testGetPublicInfoString");
    }

    @Test
    public void testGetPrivateInfoString()
    {
        System.out.println("Beginning testGetPrivateInfoString");
        User loggedIn = new User(0, "Test", "User", "Bio", "MS", "password0");
        AccessUsers.setLoggedInUser(loggedIn);
        String privateInfo = "UserID: " + AccessUsers.getLoggedInUser().getUserId() +
                "\nPassword: " + AccessUsers.getLoggedInUser().getPassword() +
                "\nBio\nMarital Status\nSocial Links";
        assertNotNull(privacyManager.getPrivateInfoString());
        assertEquals(privacyManager.getPrivateInfoString(), privateInfo);

        System.out.println("Beginning testGetPrivateInfoString");
    }

}
