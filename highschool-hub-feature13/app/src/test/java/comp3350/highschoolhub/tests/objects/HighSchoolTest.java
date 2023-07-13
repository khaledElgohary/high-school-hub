package comp3350.highschoolhub.tests.objects;

import comp3350.highschoolhub.objects.HighSchool;

import org.junit.Test;

import static org.junit.Assert.*;

public class HighSchoolTest {
    @Test
    public void testHighSchoolCreate() {
        System.out.println("Starting testHighSchoolCreate");

        HighSchool highSchool = new HighSchool("Sunshine High");

        assertEquals(highSchool.getName(), "Sunshine High");

        System.out.println("Finished testHighSchoolCreate");
    }
}
