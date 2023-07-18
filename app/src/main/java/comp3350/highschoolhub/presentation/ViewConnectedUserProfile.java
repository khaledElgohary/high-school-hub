package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.ConnectionConfirmer;
import comp3350.highschoolhub.business.IConnectionConfirmer;
import comp3350.highschoolhub.business.IConnectionsManager;
import comp3350.highschoolhub.objects.User;


public class ViewConnectedUserProfile extends Activity {

    private User loggedIn;
    private User toVisit;
    private boolean areConnected;
    private ArrayAdapter<String> arrayAdapter;

    private IConnectionConfirmer connectionConfirmer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_connected_user_profile);

        //get logged in user
        loggedIn = AccessUsers.getLoggedInUser();

        //getting user to visit
        toVisit = AccessUsers.getProfileUser();

        connectionConfirmer = new ConnectionConfirmer();
        areConnected = connectionConfirmer.areConnected(loggedIn, toVisit);

        //Set up listener for clicking the back button
        Button addLinksButton = findViewById(R.id.goToHSExplore);
        addLinksButton.setOnClickListener(v -> goBack(v));

        //filling in toVisit info in text fields
        TextView firstName = findViewById(R.id.userFirstName);
        firstName.setText(toVisit.getFirstName());

        TextView lastName = findViewById(R.id.userLastName);
        lastName.setText(toVisit.getLastName());

        TextView highSchool = findViewById(R.id.userHighSchool);
        highSchool.setText(toVisit.getHighSchool().getName());

        //only view private info if connected
        if(areConnected) {
            TextView maritalStatus = findViewById(R.id.userMarital);
            maritalStatus.setText(toVisit.getMaritalStatus());

            TextView bio = findViewById(R.id.userBio);
            bio.setText(toVisit.getBio());

            //extracting all links to an arrayList
            HashMap<String, String> socialMap = toVisit.getSocials();
            Collection<String> linkCollection = socialMap.values();
            ArrayList<String> links = new ArrayList<String>(linkCollection);

            displayLinks(links);

        }

    }

    private void goBack(View v) {
        AccessUsers.setProfileUser(null);
        Intent highSchoolExplore = new Intent(this, HighSchoolExplore.class);
        startActivity(highSchoolExplore);
    }

    public void displayLinks(ArrayList<String> links) {
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, links) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                String currLink = links.get(position);
                text1.setText(currLink);
                return view;
            }
        };

        final ListView listView = findViewById(R.id.userLinks);
        listView.setAdapter(arrayAdapter);
        setSocialListListener(listView);
    }

    public void setSocialListListener(ListView lv) {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToLinkAtPos(position);
            }
        });
    }

    public void goToLinkAtPos(int pos) {
        //arrayAdapter.getItem(pos);
        Uri theLink = Uri.parse(arrayAdapter.getItem(pos));
        Intent intent = new Intent(Intent.ACTION_VIEW, theLink);
        startActivity(intent);
    }

}
