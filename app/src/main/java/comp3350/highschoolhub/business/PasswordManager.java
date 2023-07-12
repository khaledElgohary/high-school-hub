package comp3350.highschoolhub.business;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import comp3350.highschoolhub.objects.User;

public class PasswordManager {
    private static final String REGEX_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,30}$";
    private static final Pattern THE_PATTERN = Pattern.compile(REGEX_PATTERN);

    public static boolean validDatePassword(String toValidate) {
        boolean valid = false;
        if(toValidate != null) {
            Matcher theMatcher = THE_PATTERN.matcher(toValidate);
            valid = theMatcher.matches();
        }
        return valid;
    }

    public static User getUserFromList(int userID, String enteredPassword, List<User> userList) {
        User found = null;
        if(enteredPassword != null && userList != null) {
            for(int i = 0; i < userList.size(); i++) {
                User curr = userList.get(i);
                if(curr.getUserId() == userID && curr.getPassword().equals(enteredPassword)) {
                    found = curr;
                }
            }
        }
        return found;
    }
}