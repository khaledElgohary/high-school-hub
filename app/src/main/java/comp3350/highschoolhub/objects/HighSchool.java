package comp3350.highschoolhub.objects;

public class HighSchool {
    private String name;

    public HighSchool(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(HighSchool highSchool) {
        return this.name.equals(highSchool.name);
    }
}
