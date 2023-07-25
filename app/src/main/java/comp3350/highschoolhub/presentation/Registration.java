package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessHighSchools;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.IAccessHighSchools;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.PasswordManager;
import comp3350.highschoolhub.objects.HighSchool;
import comp3350.highschoolhub.objects.User;

public class Registration extends Activity {
    private EditText firstName;
    private EditText lastName;
    private EditText bio;
    private EditText maritalStatus;
    private EditText password;
    private Spinner highSchool;
    private List<HighSchool> highSchoolsList;
    private ArrayAdapter<HighSchool> highSchoolArrayAdapter;

    private IAccessUsers accessUsers;
    private IAccessHighSchools accessHighSchools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        accessUsers = new AccessUsers();
        accessHighSchools = new AccessHighSchools();

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        password = findViewById(R.id.editTextPassword);
        bio = findViewById(R.id.editTextBio);
        maritalStatus = findViewById(R.id.editTextMaritalStatus);
        highSchool = (Spinner) findViewById((R.id.highSchoolDropdown));

        highSchoolsList = accessHighSchools.getHighSchools();
        highSchoolArrayAdapter = new ArrayAdapter<HighSchool>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, highSchoolsList);

        Button submit = findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PasswordManager.validDatePassword(password.toString())) {
                    submitUser();
                }
            }
        });

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void submitUser() {
        int userID = accessUsers.getNumUsers() + 1;
        User newUser = new User(userID, firstName.toString(), lastName.toString(), bio.toString(), maritalStatus.toString(), password.toString());
        accessUsers.insertUser(newUser);
    }

    private void goToLogin() {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
}
