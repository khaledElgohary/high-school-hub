package comp3350.highschoolhub.persistence.hsqldb;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.persistence.HighSchoolPersistence;

import java.util.List;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class HighSchoolPersistenceHSQLDB implements HighSchoolPersistence {

    private final String dbPath;

    public HighSchoolPersistenceHSQLDB(final String dbPath) { this.dbPath = dbPath; }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private HighSchool fromResultSet(final ResultSet rs) throws SQLException {
        final String name = rs.getString("name");
        return new HighSchool(name);
    }

    @Override
    public List<HighSchool> getHighSchools() {
        final List<HighSchool> highSchools = new ArrayList<HighSchool>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM HIGHSCHOOLS");
            while (rs.next()) {
                final HighSchool highSchool = fromResultSet(rs);
                highSchools.add(highSchool);
            }

            rs.close();
            st.close();

            return highSchools;
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
