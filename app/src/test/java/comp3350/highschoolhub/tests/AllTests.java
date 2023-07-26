package comp3350.highschoolhub.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.highschoolhub.business.PrivacyManager;
import comp3350.highschoolhub.tests.business.PasswordManagerTest;
import comp3350.highschoolhub.tests.business.AccessHighSchoolsUnitTests;
import comp3350.highschoolhub.tests.business.AccessRequestsUnitTests;
import comp3350.highschoolhub.tests.business.AccessUsersUnitTests;
import comp3350.highschoolhub.tests.business.ConnectionManagerTests;
import comp3350.highschoolhub.tests.business.HighSchoolsManagerTests;
import comp3350.highschoolhub.tests.business.PrivacyManagerUnitTests;
import comp3350.highschoolhub.tests.business.SocialsManagerTest;
import comp3350.highschoolhub.tests.objects.HighSchoolTest;
import comp3350.highschoolhub.tests.objects.RequestTest;
import comp3350.highschoolhub.tests.objects.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HighSchoolTest.class,
        RequestTest.class,
        UserTest.class,
        PasswordManagerTest.class,
        UserTest.class,
        SocialsManagerTest.class,
        ConnectionManagerTests.class,
        HighSchoolsManagerTests.class,
        AccessUsersUnitTests.class,
        AccessRequestsUnitTests.class,
        AccessHighSchoolsUnitTests.class,
        PrivacyManagerUnitTests.class
})
public class AllTests {

}
