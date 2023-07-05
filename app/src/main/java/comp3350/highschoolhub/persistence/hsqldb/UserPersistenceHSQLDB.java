package comp3350.highschoolhub.persistence.hsqldb;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.UserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class UserPersistenceHSQLDB implements UserPersistence {

    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath) { this.dbPath = dbPath; }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(ResultSet rs) throws SQLException {
        final int userId = rs.getInt("userid");
        final String firstname = rs.getString("firstname");
        final String lastname = rs.getString("lastname");
        final String bio = rs.getString("bio");
        final String maritalStatus = rs.getString("maritalStatus");
        HighSchool highschool = new HighSchool(rs.getString("highschoolname"));

        User newUser = new User(userId, firstname, lastname, bio, maritalStatus);
        newUser.setHighSchool(highschool);
        addSocialsToUser(newUser);

        return newUser;
    }

    private void addSocialsToUser(User user) {

        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM SOCIALS WHERE USER = ?");
            st.setInt(1, user.getUserId());

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                user.addSocialMedia(rs.getString("type"), rs.getString("link"));
            }

            rs.close();
            st.close();

        } catch(final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<User> getUsers() {
        final List<User> users = new ArrayList<User>();

        try(final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM USERS");

            while(rs.next()) {
                final User user = fromResultSet(rs);
                users.add(user);
            }

            rs.close();
            st.close();

            return users;

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }

    @Override
    public boolean insertUser(User user) {
        boolean inserted = false;
        boolean inDatabase = false;
        List<User> users = getUsers();

        for(int i = 0; i < users.size() && !inDatabase; i++)
        {
            inDatabase = user.equals(users.get(i));
        }

        if(!inDatabase) {
            inserted = true;

            try(final Connection c = connection()) {
                final PreparedStatement st = c.prepareStatement("INSERT INTO USERS VALUES(?,?, ?, ?, ?, ?)");
                st.setInt(1, user.getUserId());
                st.setString(2, user.getFirstName());
                st.setString(3, user.getLastName());
                st.setString(4, user.getBio());
                st.setString(5, user.getMaritalStatus());
                st.setString(6, user.getHighSchool().getName());

                st.executeUpdate();

                st.close();

                //Now insert the social links.
                HashMap<String, String> links = user.getSocials();

                for(HashMap.Entry<String, String> entry : links.entrySet()) {
                    String type = entry.getKey();
                    String link = entry.getValue();

                    final PreparedStatement insert = c.prepareStatement("INSERT INTO SOCIALS VALUES(?, ?, ?)");
                    insert.setInt(1, user.getUserId());
                    insert.setString(2, type);
                    insert.setString(3, link);

                    insert.executeUpdate();

                    insert.close();
                }

            } catch (final SQLException e) {
                throw new PersistenceException(e);
            }

        }

        return inserted;
    }

    @Override
    public boolean updateUser(User user) {
        System.out.println("Updating User");
        boolean updated = false;

        try(Connection c = connection()) {
            updated = true;
            //First delete all the social links.
            final PreparedStatement delete = c.prepareStatement("DELETE FROM SOCIALS WHERE USER = ?");
            delete.setInt(1, user.getUserId());

            delete.execute();

            delete.close();

            //Now update the user.
            final PreparedStatement update = c.prepareStatement("UPDATE USERS SET FIRSTNAME = ?, LASTNAME = ?, BIO = ?, MARITALSTATUS = ?, HIGHSCHOOLNAME = ? WHERE USERID = ?");
            update.setString(1, user.getFirstName());
            update.setString(2, user.getLastName());
            update.setString(3, user.getBio());
            update.setString(4, user.getMaritalStatus());
            update.setString(5, user.getHighSchool().getName());
            update.setInt(6, user.getUserId());

            update.executeUpdate();

            update.close();

            //Add social links back in just in case they changed.
            HashMap<String, String> links = user.getSocials();

            for(HashMap.Entry<String, String> entry : links.entrySet()) {
                String type = entry.getKey();
                String link = entry.getValue();

                final PreparedStatement insert = c.prepareStatement("INSERT INTO SOCIALS (USER, TYPE, LINK) VALUES(?, ?, ?)");
                insert.setInt(1, user.getUserId());
                insert.setString(2, type);
                insert.setString(3, link);

                insert.executeUpdate();

                insert.close();
            }


        } catch(SQLException e) {
            throw new PersistenceException(e);
        }

        return updated;
    }
}
