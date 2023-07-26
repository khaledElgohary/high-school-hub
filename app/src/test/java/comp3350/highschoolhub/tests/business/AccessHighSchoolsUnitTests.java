package comp3350.highschoolhub.tests.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import comp3350.highschoolhub.business.AccessHighSchools;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.persistence.stubs.HighSchoolPersistenceStub;

import java.util.List;

public class AccessHighSchoolsUnitTests {

    private AccessHighSchools accessHighSchools;

    @Before
    public void setUp() {
        accessHighSchools = new AccessHighSchools(new HighSchoolPersistenceStub());
    }

    @Test
    public void testGetHighSchool() {
        List<HighSchool> highSchools = accessHighSchools.getHighSchools();

        assertNotNull(highSchools);
        assertEquals(7, highSchools.size());
        assertEquals("Central High School", highSchools.get(0).getName());
        assertEquals("Grant Park High School", highSchools.get(6).getName());
    }
}
