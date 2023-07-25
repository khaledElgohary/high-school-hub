package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
    private Spinner maritalStatus;
    private EditText password;
    private Spinner highSchoolDropdown;

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

        maritalStatus = findViewById(R.id.maritalStatusDropdown);
        String[] maritalStatuses =new String[]{"Married","Single","Widowed","Divorced"};
        ArrayAdapter<String> maritalStatusArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, maritalStatuses);
        maritalStatus.setAdapter(maritalStatusArrayAdapter);

        highSchoolDropdown = findViewById(R.id.highSchoolDropdown);
        List<HighSchool> highSchoolsList = accessHighSchools.getHighSchools();
        ArrayAdapter<HighSchool> highSchoolArrayAdapter = new ArrayAdapter<HighSchool>(
                this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, highSchoolsList) {
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
                dropDownView.setText(highSchoolsList.get(position).getName());

                return dropDownView;
            }
        };
        highSchoolDropdown.setAdapter(highSchoolArrayAdapter);

        Button submit = findViewById(R.id.buttonSubmit);
        submit.setOnClickListener(view -> submitUser());

        Button login = findViewById(R.id.login);
        login.setOnClickListener(view -> goToLoginPage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void submitUser() {
        if (PasswordManager.validateUser(firstName.getText().toString(), lastName.getText().toString(),
                password.getText().toString())) {
            int userID = accessUsers.getNumUsers() + 1;
            User newUser = new User(userID, firstName.getText().toString(),
                    lastName.getText().toString(), bio.getText().toString(),
                    maritalStatus.getSelectedItem().toString(), password.getText().toString());

            HighSchool newHighSchool = (HighSchool) highSchoolDropdown.getSelectedItem();
            newUser.setHighSchool(newHighSchool);

            accessUsers.insertUser(newUser);

            AccessUsers.setLoggedInUser(newUser);
            Intent profile = new Intent(this, MyProfile.class);
            startActivity(profile);
        }
    }

    private void goToLoginPage() {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
}
