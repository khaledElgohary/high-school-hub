package comp3350.highschoolhub.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.highschoolhub.tests.objects.UserTest;
import comp3350.highschoolhub.tests.objects.HighSchoolTest;
import comp3350.highschoolhub.tests.objects.RequestTest;
import comp3350.highschoolhub.tests.business.SocialsManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HighSchoolTest.class,
        RequestTest.class,
        UserTest.class,
        SocialsManagerTest.class
})
public class AllTests {

}
