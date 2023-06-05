package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;

import comp3350.highschoolhub.business.AccessHighSchools;
import comp3350.highschoolhub.business.AccessRequests;
import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.objects.*;
import comp3350.highschoolhub.business.AccessUsers;
import java.util.List;



import comp3350.highschoolhub.R;

public class Connections extends Activity {

    private AccessUsers accessUsers;
    private AccessRequests accessRequests;
    private ConnectionsManager connectionsManager;
    private List<User> connectionsList;
    private ArrayAdapter<User> connectionsArrayAdapter;
    private int connectionsListPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_connections);

        Button btn=findViewById(R.id.backToMyProfile);
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

        new AccessHighSchools();//REMOVE THIS LINE ONCE THE START UI IS DETERMINED.
        new AccessRequests();//REMOVE THIS LINE ONCE THE START UI IS DETERMINED.


        //Remove this line once the login feature is created.
        accessUsers.setLoggedInUser(accessUsers.getUsers().get(0));


        connectionsList = connectionsManager.getHighSchoolConnections(accessUsers.getLoggedInUser(), accessUsers.getUsers());

        try{
            connectionsArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, connectionsList){

                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    String fullName = connectionsList.get(position).getFirstName() + " " + connectionsList.get(position).getLastName();

                    text1.setText(fullName);
                    text2.setText(connectionsList.get(position).getUserName());

                    return view;

                }
            };

            //Set up what happens when a list item is clicked on.
            final ListView listView = (ListView)findViewById(R.id.userConnections);
            listView.setAdapter(connectionsArrayAdapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {
                connectionsListPosition = position;
                selectUserAtPosition(connectionsListPosition);
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
    public void selectUserAtPosition(int position)
    {
        User selected = connectionsArrayAdapter.getItem(position);
        connectionsManager.setRecipientUser(selected);
        Request findRequest = connectionsManager.findRequest(accessUsers.getLoggedInUser(), selected, accessRequests.getRequests());



        ConnectionsManager.setRecipientUser(selected);
        ConnectionsManager.setRequest(findRequest);

        Intent connectionsUserOptions = new Intent(Connections.this, ConnectionsUserOptions.class);
        Connections.this.startActivity(connectionsUserOptions);



    }

    //This method is used to go back to a user's profile page when the button is clicked on.
    private void showProfile(){
        Intent profile=new Intent(this, MyProfile.class);
        startActivity(profile);
    }
}
