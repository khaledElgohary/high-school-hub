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

import java.util.List;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessHighSchools;
import comp3350.highschoolhub.business.AccessRequests;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;

public class HighSchoolList extends Activity {

    private AccessUsers accessUsers;
    private AccessHighSchools accessHighSchools;
    private List<HighSchool> highSchoolsList;
    private ArrayAdapter<HighSchool> highSchoolArrayAdapter;
    private int highSchoolListPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_school_list);

        Button btn=findViewById(R.id.backToMyProfile);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       showProfile();
                                   }
                               }
        );

        accessUsers = new AccessUsers();
        accessHighSchools = new AccessHighSchools();

        new AccessRequests();
        new ConnectionsManager();

        //Remove this line once the login feature is created.
        AccessUsers.setLoggedInUser(accessUsers.getUsers().get(0));

        highSchoolsList = accessHighSchools.getHighSchools();

        try{
            highSchoolArrayAdapter = new ArrayAdapter<HighSchool>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, highSchoolsList){

                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    text1.setText(highSchoolsList.get(position).getName());

                    return view;
                }
            };

            //Set up what happens when a list item is clicked on.
            final ListView listView = (ListView)findViewById(R.id.highSchools);
            listView.setAdapter(highSchoolArrayAdapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {
                highSchoolListPosition = position;
                selectHighSchoolAtPosition(highSchoolListPosition);
            });

            //Set up what happens when the my profile button is clicked on at the bottom of the screen.
            final Button myProfileButton = (Button)findViewById(R.id.backToMyProfile);
        }
        catch(final Exception e){
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

    //This method is used to navigate to a user's profile or provide a popup to send a request.
    public void selectHighSchoolAtPosition(int position)
    {
        HighSchool selected = highSchoolArrayAdapter.getItem(position);
        User loggedInUser = AccessUsers.getLoggedInUser();
        loggedInUser.setHighSchool(selected);
        accessUsers.updateUser(loggedInUser);

        showProfile();
    }

    //This method is used to go back to a user's profile page when the button is clicked on.
    private void showProfile(){
        Intent profile=new Intent(this, MyProfile.class);
        startActivity(profile);
    }
}
