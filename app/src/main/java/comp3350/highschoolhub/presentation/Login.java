package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.CopyDatabase;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.PasswordManager;
import comp3350.highschoolhub.objects.User;

public class Login extends Activity {
    private IAccessUsers accessUsers;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        CopyDatabase.copyDatabaseToDevice(this);

        accessUsers = new AccessUsers();
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);

        Button submit = findViewById(R.id.buttonSubmit);
        Button register = findViewById(R.id.buttonRegister);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //search for the appropriate user and log them in
    private void login() {
        if (PasswordManager.validateLogin(username.getText().toString(), password.getText().toString())) {
            User loggingIn = accessUsers.findUser(Integer.parseInt(username.getText().toString()), password.getText().toString());
            if (loggingIn != null) {
                AccessUsers.setLoggedInUser(loggingIn);
                Intent profile = new Intent(this, MyProfile.class);
                startActivity(profile);
            }
        }
        else {
            TextView errorText = findViewById(R.id.textViewError);
            errorText.setText("Incorrect username or password!");
        }
    }

    private void goToRegister() {
        Intent register = new Intent(this, Registration.class);
        startActivity(register);
    }
}
