package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.objects.HighSchool;

public class HighSchools extends Activity {
    private IAccessUsers accessUsers;
    private ArrayAdapter<HighSchool> highSchoolArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_high_schools);

        accessUsers = new AccessUsers();

        List<HighSchool> userHighSchools = AccessUsers.getLoggedInUser().getHighSchools();

        highSchoolArrayAdapter = new ArrayAdapter<HighSchool>(this, android.R.layout.simple_list_item_activated_2,
                android.R.id.text1, userHighSchools) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                text1.setText(userHighSchools.get(position).getName());

                return view;
            }

            @Override
            public boolean isEnabled(int position) {
                return false;
            }
        };

        final ListView listView = findViewById(R.id.userHighSchools);
        listView.setAdapter(highSchoolArrayAdapter);

        Button profileButton = findViewById(R.id.backToMyProfile);
        profileButton.setOnClickListener(v -> showProfile());

        Button editButton = findViewById(R.id.editHighSchoolsButton);
        editButton.setOnClickListener(v -> showEditHighSchools());
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

    private void showProfile() {
        Intent profile = new Intent(this, MyProfile.class);
        startActivity(profile);
    }

    private void showEditHighSchools() {
        Intent edit = new Intent(this, HighSchoolList.class);
        startActivity(edit);
    }
}
