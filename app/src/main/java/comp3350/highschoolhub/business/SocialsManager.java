package comp3350.highschoolhub.business;

import java.util.regex.Pattern;

import comp3350.highschoolhub.objects.User;

public class SocialsManager implements ISocialsManager{
    //This method adds a new social media link to a user's account
    public boolean addLink(User loggedIn, String platform, String link)
            throws InvalidLinkException, InvalidPlatformException{

        boolean success = false;
        String linkWithHttps = prependHttpsToLink(link);

        if(link == null || link.length() == 0 || !validLink(linkWithHttps)) {
            throw new InvalidLinkException("Invalid Social Link");
        }

        if(platform == null || platform.length() == 0) {
            throw new InvalidPlatformException("Invalid Social Platform");
        }

        if (loggedIn != null) {
            loggedIn.addSocialMedia(platform.toLowerCase(), linkWithHttps);
            success = true;
        }

        return success;
    }

    //This method checks if a link is in the right format
    public static boolean validLink(String link) {
        //Checks for either 2 periods or a period and a slash in the link
        //Such as in my-blog.tumblr.com or facebook.com/me
        String regex = ".+((\\.[^./]+\\.[^./]+)|(\\.[^./]+/.*))$";
        Pattern pattern = Pattern.compile(regex);
        return link != null && pattern.matcher(link).matches();
    }

    //This method prepends a link with "https://" if it does not start with "http://" or "https://"
    public static String prependHttpsToLink(String link) {
        String newLink = link;
        String regex = "^https{0,1}://.*$";
        Pattern pattern = Pattern.compile(regex);

        if (newLink != null && !pattern.matcher(link).matches()) {
            newLink = "https://" + newLink;
        }

        return newLink;
    }
}
