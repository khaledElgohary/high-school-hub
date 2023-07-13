package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import comp3350.highschoolhub.business.AccessHighSchools;
import comp3350.highschoolhub.persistence.stubs.HighSchoolPersistenceStub;

public class AccessHighSchoolsUnitTests {

    private AccessHighSchools accessHighSchools;

    @Before
    public void setUp() {
        accessHighSchools = new AccessHighSchools(new HighSchoolPersistenceStub());
    }

    @Test
    public void testGetHighSchool() {
        assertNotNull(accessHighSchools.getHighSchools());
    }
}
