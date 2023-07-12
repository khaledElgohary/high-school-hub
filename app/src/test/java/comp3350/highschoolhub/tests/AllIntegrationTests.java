package comp3350.highschoolhub.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.highschoolhub.tests.business.AccessHighSchoolsIntegrationTests;
import comp3350.highschoolhub.tests.business.AccessRequestsIntegrationTests;
import comp3350.highschoolhub.tests.business.AccessUsersIntegrationTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessHighSchoolsIntegrationTests.class,
        AccessRequestsIntegrationTests.class,
        AccessUsersIntegrationTests.class
})
public class AllIntegrationTests {
}
