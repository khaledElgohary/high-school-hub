package comp3350.highschoolhub.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import comp3350.highschoolhub.business.AccessHighSchools;
import comp3350.highschoolhub.business.AccessRequests;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.persistence.HighSchoolPersistence;
import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.persistence.hsqldb.HighSchoolPersistenceHSQLDB;
import comp3350.highschoolhub.persistence.hsqldb.RequestPersistenceHSQLDB;
import comp3350.highschoolhub.tests.utils.TestUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccessHighSchoolsIntegrationTests {

    private AccessHighSchools accessHighSchools;

    private File tempDB;

    @Before
    public void setUp() throws IOException{
        this.tempDB = TestUtils.copyDB();
        final HighSchoolPersistence persistence = new HighSchoolPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessHighSchools = new AccessHighSchools(persistence);
    }

    @Test
    public void testGetHighSchools(){
        final List<HighSchool> highSchools = accessHighSchools.getHighSchools();
        assertEquals(7, highSchools.size());
    }

    @Test
    public void testHighSchoolInList(){
        final List<HighSchool> highSchools = accessHighSchools.getHighSchools();
        assertEquals("Central High School", highSchools.get(0).getName());
        assertEquals("Summertime High School", highSchools.get(6).getName());
    }

    @After
    public void tearDown(){
        this.tempDB.delete();
    }
}
