package comp3350.highschoolhub.persistence.hsqldb;

import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.persistence.RequestPersistence;
import comp3350.highschoolhub.objects.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class RequestPersistenceHSQLDB implements RequestPersistence {

    private final String dbPath;

    public RequestPersistenceHSQLDB(final String dbPath) { this.dbPath = dbPath; }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromUserResultSet(ResultSet rs) throws SQLException {
        final int userId = rs.getInt("userid");
        final String firstname = rs.getString("firstname");
        final String lastname = rs.getString("lastname");
        final String bio = rs.getString("bio");
        final String maritalStatus = rs.getString("maritalStatus");
        HighSchool highschool = new HighSchool(rs.getString("highschoolname"));
        final String password = rs.getString("password");

        User newUser = new User(userId, firstname, lastname, bio, maritalStatus, password);
        newUser.setHighSchool(highschool);
        addSocialsToUser(newUser);

        return newUser;
    }

    private void addSocialsToUser(User user) {

        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM SOCIALS WHERE USERID = ?");
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

    private User getUser(int id) {
        try(Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM USERS WHERE USERID = ?");
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            User getUser = null;

            while(rs.next())
            {
                getUser = fromUserResultSet(rs);
            }

            rs.close();
            st.close();

            return getUser;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private Request fromResultSet(ResultSet rs) throws SQLException {
        int senderId = rs.getInt("sender");
        int recipientID = rs.getInt("recipient");
        boolean accepted = rs.getBoolean("accepted");

        User sender = getUser(senderId);
        User recipient = getUser(recipientID);
        Request request = new Request(sender, recipient);
        request.setAccepted(accepted);
        return request;
    }

    @Override
    public List<Request> getRequests() {
        List<Request> requests = new ArrayList<Request>();

        try(final Connection c = connection()) {
            final Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM REQUESTS");

            while(rs.next()) {
                final Request request = fromResultSet(rs);
                requests.add(request);
            }

            s.close();
            rs.close();

            return requests;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public boolean insertRequest(Request request) {
        boolean inserted = false;

        try(Connection c = connection()) {
            inserted = true;
            final PreparedStatement st = c.prepareStatement("INSERT INTO REQUESTS VALUES(?, ?, ?)");
            st.setInt(1, request.getSender().getUserId());
            st.setInt(2, request.getRecipient().getUserId());
            st.setBoolean(3, request.getAccepted());

            st.executeUpdate();

            st.close();
        } catch(SQLException e) {
            throw new PersistenceException(e);
        }

        return inserted;
    }

    @Override
    public boolean updateRequest(Request request) {

        boolean updated = false;

        try(Connection c = connection()) {
            updated = true;
            final PreparedStatement st = c.prepareStatement("UPDATE REQUESTS SET ACCEPTED = ? WHERE SENDER = ? AND RECIPIENT = ?");
            st.setBoolean(1, request.getAccepted());
            st.setInt(2, request.getSender().getUserId());
            st.setInt(3, request.getRecipient().getUserId());

            st.executeUpdate();

        } catch(SQLException e) {
            throw new PersistenceException(e);
        }

        return updated;
    }
}
