package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.IConnectionsManager;
import comp3350.highschoolhub.objects.User;

public class MyProfile extends Activity {
    //fetching the loggedin user
    private final User loggedIn = AccessUsers.getLoggedInUser();

    private final IConnectionsManager connectionsManager = new ConnectionsManager();

    private final IAccessUsers accessUsers = new AccessUsers();
    //creating a string which is easier since both names will be combined and placed in a placeholder
    String name = loggedIn.getFirstName() + " " + loggedIn.getLastName();

    String numberOfConnections = connectionsManager.getHighSchoolConnections(loggedIn, accessUsers.getUsers()).size() + "";

    String highSchoolName = loggedIn.getHighSchool().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Button button = findViewById(R.id.backToConnections);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConnections();
            }
        });

        Button socialsButton = findViewById(R.id.socialLinksButton);
        socialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSocials();
            }
        });

        Button highSchoolsButton = findViewById(R.id.editHighSchoolButton);
        highSchoolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHighSchools();
            }
        });

        //displaying the user name in the UI
        TextView textView = findViewById(R.id.user_name);
        textView.setText(name);

        //displaying the number of connections a user have in the UI
        TextView textView1 = findViewById(R.id.number_of_connections);
        textView1.setText(numberOfConnections);

        //displaying the highschool name the user was in the UI
        TextView textView2 = findViewById(R.id.highschool_name);
        textView2.setText(highSchoolName);

        //Displaying the marital status of the user in the UI
        TextView textView4 = findViewById(R.id.marital_status);
        textView4.setText(loggedIn.getMaritalStatus());

        //displaying the bio of the user in the UI
        TextView textView3 = findViewById(R.id.Bio);
        textView3.setText(loggedIn.getBio());
    }

    //this method is used to return to the connections page when the button is pressed
    private void showConnections() {
        Intent connections = new Intent(this, Connections.class);
        startActivity(connections);
    }

    //this method is used to go to the page where the user's socials are.
    private void showSocials() {
        Intent socials = new Intent(this, Socials.class);
        startActivity(socials);
    }

    private void showHighSchools() {
        Intent highSchools = new Intent(this, HighSchoolList.class);
        startActivity(highSchools);
    }

    public void showHighSchoolExplore(View view) {
        Intent highSchoolExplore = new Intent(this, HighSchoolExplore.class);
        startActivity(highSchoolExplore);
    }
}