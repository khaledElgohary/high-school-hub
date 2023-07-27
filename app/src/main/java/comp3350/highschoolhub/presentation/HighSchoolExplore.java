package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessHighSchools;
import comp3350.highschoolhub.business.AccessRequests;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.HighSchoolsManager;
import comp3350.highschoolhub.business.IAccessHighSchools;
import comp3350.highschoolhub.business.IAccessRequests;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.IHighSchoolsManager;
import comp3350.highschoolhub.business.IRequestsManager;
import comp3350.highschoolhub.business.RequestsManager;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

public class HighSchoolExplore extends Activity {
    private IAccessUsers accessUsers;
    private IAccessHighSchools accessHighSchools;
    private IAccessRequests accessRequests;
    private IHighSchoolsManager highSchoolsManager;
    private IRequestsManager requestsManager;
    private ArrayAdapter<User> userArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_school_explore);

        accessUsers = new AccessUsers();
        accessHighSchools = new AccessHighSchools();
        accessRequests = new AccessRequests();
        highSchoolsManager = new HighSchoolsManager();
        requestsManager = new RequestsManager();

        //Set up dropdown menu for high schools
        Spinner spinner = (Spinner) findViewById(R.id.highSchoolDropdown);
        List<HighSchool> highSchools = accessHighSchools.getHighSchools();

        ArrayAdapter<HighSchool> highSchoolArrayAdapter = new ArrayAdapter<HighSchool>(
                this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, highSchools) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                text1.setText(accessHighSchools.getHighSchools().get(position).getName());

                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView dropDownView = (TextView) super.getDropDownView(position, convertView, parent);
                dropDownView.setText(highSchools.get(position).getName());

                return dropDownView;
            }
        };

        spinner.setAdapter(highSchoolArrayAdapter);

        //Set up listener for search button
        Button goButton = findViewById(R.id.searchButton);
        goButton.setOnClickListener(v -> selectHighSchool());

        //Set up listener for back button
        Button btn = findViewById(R.id.backToMyProfile);
        btn.setOnClickListener(v -> showProfile());

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

    //This method updates the UI with the new list of users related to the selected high school
    private void selectHighSchool() {
        HighSchool selectedHighSchool =
                (HighSchool) ((Spinner) findViewById(R.id.highSchoolDropdown)).getSelectedItem();
        List<User> usersFromHighSchool
                = highSchoolsManager.getUsersFromHighSchool(AccessUsers.getLoggedInUser(), selectedHighSchool, accessUsers.getUsers());
        displayUsers(usersFromHighSchool);
    }

    //This displays all the given users in a list in the UI
    public void displayUsers(List<User> displayList) {
        userArrayAdapter = new ArrayAdapter<User>(
                this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, displayList) {
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

        final ListView listView = findViewById(R.id.usersList);
        listView.setAdapter(userArrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectUserAtPosition(position);
        });
    }

    //This method is used to navigate to a user's profile or provide a popup to send a request.
    private void selectUserAtPosition(int position) {
        User selected = userArrayAdapter.getItem(position);

        RequestsManager.setRecipientUser(selected);
        Request findRequest = requestsManager.findRequest(AccessUsers.getLoggedInUser(), selected, accessRequests.getRequests());

        RequestsManager.setRecipientUser(selected);
        RequestsManager.setRequest(findRequest);

        if (((ToggleButton) findViewById(R.id.toggleButton)).isChecked()) {
            Intent connectionsUserOptions = new Intent(this, ConnectionsUserOptions.class);
            this.startActivity(connectionsUserOptions);
        } else {
            AccessUsers.setProfileUser(selected);
            Intent connected = new Intent(this, ViewConnectedUserProfile.class);
            startActivity(connected);
        }
    }

    //This method is used to go back to a user's profile page when the button is clicked on.
    private void showProfile() {
        Intent profile = new Intent(this, MyProfile.class);
        startActivity(profile);
    }
}
