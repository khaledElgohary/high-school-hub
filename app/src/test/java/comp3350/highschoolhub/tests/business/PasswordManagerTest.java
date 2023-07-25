package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import comp3350.highschoolhub.business.PasswordManager;
import comp3350.highschoolhub.objects.User;

public class PasswordManagerTest {

    @Test
    public void testPasswordValidation() {
        System.out.println("Starting testPasswordValidation");

        String validPassword1 = "ABCxyz123";
        String validPassword2 = "abcXYZ123";
        String validPassword3 = "123ABCxyz";
        String validPassword4 = "1aA2vBCdd";

        String noUpperCase = "abcxyz123";
        String noLowerCase = "ABCXYZ123";
        String noNumbers = "ABCXYZABC";
        String tooShort = "Ab1";
        String tooLong = "ABCxyz123ABCxyz123ABCxyz123ABCxyz123ABCxyz123";
        String emptyStringPass = " ";

        assertTrue(PasswordManager.validDatePassword(validPassword1));
        assertTrue(PasswordManager.validDatePassword(validPassword2));
        assertTrue(PasswordManager.validDatePassword(validPassword3));
        assertTrue(PasswordManager.validDatePassword(validPassword4));

        assertFalse(PasswordManager.validDatePassword(null));
        assertFalse(PasswordManager.validDatePassword(noUpperCase));
        assertFalse(PasswordManager.validDatePassword(noLowerCase));
        assertFalse(PasswordManager.validDatePassword(noNumbers));
        assertFalse(PasswordManager.validDatePassword(tooShort));
        assertFalse(PasswordManager.validDatePassword(tooLong));
        assertFalse(PasswordManager.validDatePassword(emptyStringPass));


        System.out.println("Finished testPasswordValidation");
    }

    @Test
    public void testFindUserInList() {

        System.out.println("Starting testFindUserInList");

        List<User> userList = new ArrayList<>();

        assertNull(PasswordManager.getUserFromList(0, "Password0", userList));
        assertNull(PasswordManager.getUserFromList(1, "Password1", userList));

        User user0 = new User(0, "Aaa", "Bbb", "BIO", "Single", "Password0");
        User user1 = new User(1, "Ccc", "Ddd", "BIO", "Single", "Password1");
        User user2 = new User(2, "Eee", "Fff", "BIO", "Single", "Password2");
        User user3 = new User(3, "Ggg", "Hhh", "BIO", "Single", "Password3");
        User user4 = new User(4, "Iii", "Jjj", "BIO", "Single", "Password4");

        userList.add(user0);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        assertEquals(PasswordManager.getUserFromList(user0.getUserId(), user0.getPassword(), userList), user0);
        assertEquals(PasswordManager.getUserFromList(user1.getUserId(), user1.getPassword(), userList), user1);
        assertEquals(PasswordManager.getUserFromList(user2.getUserId(), user2.getPassword(), userList), user2);
        assertEquals(PasswordManager.getUserFromList(user3.getUserId(), user3.getPassword(), userList), user3);

        assertNull(PasswordManager.getUserFromList(user4.getUserId(), user4.getPassword(), userList));
        assertNull(PasswordManager.getUserFromList(0, "Invalid0", userList));
        assertNull(PasswordManager.getUserFromList(1, null, userList));
        assertNull(PasswordManager.getUserFromList(4, "Password0", userList));

        System.out.println("Finished testFindUserInList");
    }

    @Test
    public void testValidateLogin() {
        assertFalse(PasswordManager.validateLogin("invalidUsername", ""));
        assertFalse(PasswordManager.validateLogin("0", ""));
        assertTrue(PasswordManager.validateLogin("0", "validPass"));
    }
}
