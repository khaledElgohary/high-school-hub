package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.PasswordManager;
import comp3350.highschoolhub.business.UsersManager;

public class Registration extends Activity {
    private EditText firstName;
    private EditText lastName;
    private EditText bio;
    private EditText maritalStatus;
    private EditText password;
    private Spinner highSchool;

    private IAccessUsers accessUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        accessUsers = new AccessUsers();

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        password = findViewById(R.id.editTextPassword);
        bio = findViewById(R.id.editTextBio);
        maritalStatus = findViewById(R.id.editTextMaritalStatus);
        highSchool = (Spinner)findViewById((R.id.highSchoolDropdown));
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
        //if (usersManager.)
    }

    //this method returns
    private void goToHome() {

    }
}
