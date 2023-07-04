package comp3350.highschoolhub.application;

public class Main {

    private static String dbName = "HH";

    public static void setDBPathName(final String name) {

        System.out.println(name);
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(name);
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }
}
