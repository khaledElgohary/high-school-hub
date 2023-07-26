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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessHighSchools;
import comp3350.highschoolhub.business.AccessRequests;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.business.HighSchoolsManager;
import comp3350.highschoolhub.business.IAccessHighSchools;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.IHighSchoolsManager;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;

public class HighSchoolList extends Activity {

    private IAccessUsers accessUsers;
    private IAccessHighSchools accessHighSchools;
    private List<HighSchool> highSchoolsList;
    private ArrayAdapter<HighSchool> highSchoolArrayAdapter;
    private int highSchoolListPosition;
    private List<HighSchool> selectedHighSchools;
    private IHighSchoolsManager highSchoolsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_school_list);

        Button backBtn = findViewById(R.id.backToHighSchools);
        backBtn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       backToHighSchools();
                                   }
                               }
        );

        Button highSchoolsBtn = findViewById(R.id.submitHighSchools);
        highSchoolsBtn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       submitHighSchools();
                                   }
                               }
        );

        accessUsers = new AccessUsers();
        accessHighSchools = new AccessHighSchools();

        new AccessRequests();
        new ConnectionsManager();

        highSchoolsManager = new HighSchoolsManager();
        selectedHighSchools = new ArrayList<>();

        highSchoolsList = accessHighSchools.getHighSchools();

        try {
            highSchoolArrayAdapter = new ArrayAdapter<HighSchool>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, highSchoolsList) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = view.findViewById(android.R.id.text1);
                    text1.setText(highSchoolsList.get(position).getName());

                    return view;
                }
            };

            //Set up what happens when a list item is clicked on.
            final ListView listView = findViewById(R.id.highSchools);
            listView.setAdapter(highSchoolArrayAdapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {
                highSchoolListPosition = position;
                selectHighSchoolAtPosition(highSchoolListPosition, view, parent);
            });

            //Set up what happens when the my profile button is clicked on at the bottom of the screen.
            final Button myProfileButton = findViewById(R.id.backToMyProfile);
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

    public void selectHighSchoolAtPosition(int position, View convertView, ViewGroup parent) {
        HighSchool selected = highSchoolArrayAdapter.getItem(position);
        User loggedInUser = AccessUsers.getLoggedInUser();

        selectedHighSchools = highSchoolsManager.processNewHighSchool(selected, selectedHighSchools);
        highSchoolArrayAdapter.getView(position, convertView, parent).setSelected(loggedInUser.getHighSchools().contains(selected));

        Button submitBtn = findViewById(R.id.submitHighSchools);
        submitBtn.setEnabled(!selectedHighSchools.isEmpty());
    }

    private void submitHighSchools() {
        User loggedIn = AccessUsers.getLoggedInUser();
        loggedIn.setHighSchools(selectedHighSchools);
        accessUsers.updateUser(loggedIn);

        backToHighSchools();
    }

    private void backToHighSchools() {
        Intent userHighSchools = new Intent(this, HighSchools.class);
        startActivity(userHighSchools);
    }
}
