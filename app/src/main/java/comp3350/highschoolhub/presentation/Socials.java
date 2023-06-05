package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.objects.User;
import comp3350.highschoolhub.business.AccessUsers;

public class Socials extends Activity {
    private SimpleAdapter simpleAdapter;
    private static final String[] MAP_KEYS = {"platform", "link"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_socials);

        //Set up listener for clicking the profile button
        Button goToProfileButton = findViewById(R.id.goToProfileButton);
        goToProfileButton.setOnClickListener(v -> goToProfile(v));

        //Set up listener for clicking the Add Link button
        Button addLinksButton = findViewById(R.id.addLinkButton);
        addLinksButton.setOnClickListener(v -> goToAddLink(v));

        //Get data for displaying the user's social media links
        User loggedIn = AccessUsers.getLoggedInUser();
        Map<String, String> socialsList = loggedIn.getSocials();

        ListView listView = findViewById(R.id.socialsListView);
        ArrayList<Map<String, String>> data = new ArrayList<>();

        //Transfer data to an ArrayList of maps for display
        socialsList.forEach((key, value) -> {
            HashMap<String, String> map = new HashMap<>();
            //This will display the platform name, then the link underneath
            map.put(MAP_KEYS[0], key);
            map.put(MAP_KEYS[1], value);
            data.add(map);
        });

        //Display the user's socials in a list
        simpleAdapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2,
                MAP_KEYS, new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);

        //Set up listener for clicking a link
        listView.setOnItemClickListener((parent, view, position, id) -> {
            openLinkAtPosition(position);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_socials, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    //This method opens a social media link in the default browser
    private void openLinkAtPosition(int position) {
        HashMap item = (HashMap) simpleAdapter.getItem(position);
        Uri uri = Uri.parse(item.get(MAP_KEYS[1]).toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }

    //This method lets the user navigate back to the profile
    private void goToProfile(View v) {
        //Uncomment this once there is a MyProfile class
        //Intent intent = new Intent(this, MyProfile.class);
        //startActivity(intent);
    }

    //This method navigates the user to a page for adding new social media links
    private void goToAddLink(View v) {
        Intent intent = new Intent(this, SocialsAddLink.class);
        startActivity(intent);
    }
}
