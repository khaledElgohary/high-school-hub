package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import comp3350.highschoolhub.business.PrivacyManager;

public class PrivacyManagerUnitTests {

    private final String publicInfo = "First name\nLast name\nHigh School";

    private final String privateInfo = "UserID\nBio\nMarital Status\nSocial Links";

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

        assertNotNull(privacyManager.getPrivateInfoString());
        assertEquals(privacyManager.getPrivateInfoString(), privateInfo);

        System.out.println("Beginning testGetPrivateInfoString");
    }

}
