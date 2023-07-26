package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import comp3350.highschoolhub.business.InvalidLinkException;
import comp3350.highschoolhub.business.InvalidPlatformException;
import comp3350.highschoolhub.business.SocialsManager;
import comp3350.highschoolhub.objects.User;

public class SocialsManagerTest {
    @Test
    public void testValidLinkAllValid() {
        System.out.println("Starting testValidLinkAllValid.");

        String valid1 = "facebook.com/me";
        String valid2 = "www.facebook.com/me";
        String valid3 = "my-blog.tumblr.com";
        String valid4 = "facebook.com/first.last.3";
        String valid5 = "www.facebook.com/first.last.3";

        assertTrue(SocialsManager.validLink(valid1));
        assertTrue(SocialsManager.validLink(valid2));
        assertTrue(SocialsManager.validLink(valid3));
        assertTrue(SocialsManager.validLink(valid4));
        assertTrue(SocialsManager.validLink(valid5));

        System.out.println("Finished testValidLinkAllValid.");
    }

    @Test
    public void testValidLinkAllInvalid() {
        System.out.println("Starting testValidLinkAllInvalid.");

        String notValid1 = "facebook";
        String notValid2 = "facebook.com";
        String notValid3 = "facebook./me";
        String notValid4 = null;
        String notValid5 = "";
        String notValid6 = "..";

        assertFalse(SocialsManager.validLink(notValid1));
        assertFalse(SocialsManager.validLink(notValid2));
        assertFalse(SocialsManager.validLink(notValid3));
        assertFalse(SocialsManager.validLink(notValid4));
        assertFalse(SocialsManager.validLink(notValid5));
        assertFalse(SocialsManager.validLink(notValid6));

        System.out.println("Finished testValidLinkAllInvalid.");
    }

    @Test
    public void testAddLink() throws InvalidLinkException, InvalidPlatformException {
        System.out.println("Starting testAddLink.");

        User testUser = new User(0, "Test", "User", "Hello world!", "Single", "password0");
        String testPlatform = "Facebook";
        String testLink = "facebook.com/my.name";
        String invalidLink = "facebook.com";
        SocialsManager socialsManager = new SocialsManager();
        assertFalse(socialsManager.addLink(null, testPlatform, testLink));
        assertTrue(socialsManager.addLink(testUser, testPlatform, testLink));
        assertThrows(InvalidPlatformException.class, () -> {socialsManager.addLink(testUser, null, testLink);});
        assertThrows(InvalidLinkException.class, () -> {socialsManager.addLink(testUser, testPlatform, null);});
        assertThrows(InvalidLinkException.class, () -> {socialsManager.addLink(testUser, testPlatform, invalidLink);});

        System.out.println("Finished testAddLink.");
    }

    @Test
    public void testPrependLink() {
        System.out.println("Starting testPrependLink.");

        assertEquals(SocialsManager.prependHttpsToLink("http://link"), "http://link");
        assertEquals(SocialsManager.prependHttpsToLink("https://testlink"), "https://testlink");

        assertNull(SocialsManager.prependHttpsToLink(null));
        assertEquals(SocialsManager.prependHttpsToLink("test"), "https://test");
        assertEquals(SocialsManager.prependHttpsToLink("testhttps://test"), "https://testhttps://test");
        assertEquals(SocialsManager.prependHttpsToLink(""), "https://");
        assertEquals(SocialsManager.prependHttpsToLink("httpslink"), "https://httpslink");
        assertEquals(SocialsManager.prependHttpsToLink("https:/link"), "https://https:/link");

        System.out.println("Finished testPrependLink.");
    }
}
