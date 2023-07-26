package comp3350.highschoolhub.objects;

public class HighSchool {
    private String name;

    public HighSchool(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object highSchool) {
        return highSchool instanceof HighSchool
                && this.name.equals(((HighSchool) highSchool).getName());
    }
}
