package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.application.Main;
import comp3350.highschoolhub.business.AccessHighSchools;
import comp3350.highschoolhub.business.AccessRequests;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.business.IAccessRequests;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.IConnectionsManager;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

public class Connections extends Activity {

    private IAccessUsers accessUsers;
    private IAccessRequests accessRequests;
    private IConnectionsManager connectionsManager;
    private List<User> connectionsList;
    private ArrayAdapter<User> connectionsArrayAdapter;
    private int connectionsListPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_connections);

        //MOVE THIS BLOCK OF CODE TO onCreate METHOD OF LOGIN PAGE ONCE IT IS CREATED
        copyDatabaseToDevice();
        new AccessUsers();
        new AccessHighSchools();
        new AccessRequests();
        //END OF BLOCK TO COPY


        Button btn = findViewById(R.id.backToMyProfile);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       showProfile();
                                   }
                               }
        );

        accessUsers = new AccessUsers();
        accessRequests = new AccessRequests();
        connectionsManager = new ConnectionsManager();




        //Remove this line once the login feature is created.
        AccessUsers.setLoggedInUser(accessUsers.getUsers().get(0));


        connectionsList = connectionsManager.getHighSchoolConnections(AccessUsers.getLoggedInUser(), accessUsers.getUsers());

        try {
            displayConnections(connectionsList);

            //Set up what happens when the my profile button is clicked on at the bottom of the screen.
            final Button myProfileButton = findViewById(R.id.backToMyProfile);

            //Set up the search button
            Button searchButton = findViewById(R.id.searchConnectionsButton);
            searchButton.setOnClickListener(v -> searchConnections());

            //Set up the reset button
            Button resetSearchButton = findViewById(R.id.resetSearchButton);
            resetSearchButton.setOnClickListener(v -> {
                //Reset the list display and search bar contents to default
                //when this button is clicked
                ((SearchView) findViewById(R.id.searchBar)).setQuery("", false);
                displayConnections(connectionsList);
            });
        } catch (final Exception e) {
            Messages.fatalError(this, e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_connections, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    //This method displays the provided list of connections in the UI
    public void displayConnections(List<User> displayList) {
        connectionsArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, displayList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                String fullName = displayList.get(position).getFirstName() + " " + displayList.get(position).getLastName();

                text1.setText(fullName);
                text2.setText(displayList.get(position).getUserName());

                return view;

            }
        };

        //Set up what happens when a list item is clicked on.
        final ListView listView = findViewById(R.id.userConnections);
        listView.setAdapter(connectionsArrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            connectionsListPosition = position;
            selectUserAtPosition(connectionsListPosition);
        });
    }

    //This method updates the display of the connections list when the user clicks the search button
    public void searchConnections() {
        String input = ((SearchView) findViewById(R.id.searchBar)).getQuery().toString();
        displayConnections(connectionsManager.getMatchingConnections(AccessUsers.getLoggedInUser(), input, connectionsList));
    }

    //This method is used to navigate to a user's profile or provide a popup to send a request.
    public void selectUserAtPosition(int position) {
        User selected = connectionsArrayAdapter.getItem(position);
        ConnectionsManager.setRecipientUser(selected);
        Request findRequest = connectionsManager.findRequest(AccessUsers.getLoggedInUser(), selected, accessRequests.getRequests());

        ConnectionsManager.setRecipientUser(selected);
        ConnectionsManager.setRequest(findRequest);

        if (((ToggleButton) findViewById(R.id.toggleButton)).isChecked()) {
            //Send a request instead of navigating to the profile or popup when request mode is on
            Request updated = connectionsManager.updateRequest(AccessUsers.getLoggedInUser(), findRequest);
            accessRequests.updateRequest(updated);
            Toast.makeText(this, "A connection request has been sent.", Toast.LENGTH_SHORT).show();
        } else {
            Intent connectionsUserOptions = new Intent(Connections.this, ConnectionsUserOptions.class);
            Connections.this.startActivity(connectionsUserOptions);
        }
    }

    //This method is used to go back to a user's profile page when the button is clicked on.
    private void showProfile() {
        Intent profile = new Intent(this, MyProfile.class);
        startActivity(profile);
    }

    //These last two methods are used to add the db to the device when the app is first started.
    //ONCE THE LOGIN PAGE IS COMPLETED, THESE TWO METHODS MUST BE MOVED TO THE ACTIVITY CLASS FOR THE LOGIN PAGE.
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
