package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.CharSequence;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.SocialsManager;

public class SocialsAddLink extends Activity {
    private EditText platformNameInput;
    private EditText linkInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_socials_add_link);

        platformNameInput = findViewById(R.id.platformNameInput);
        linkInput = findViewById(R.id.linkInput);

        //Set up listeners for navigation buttons
        Button submitSocialsButton = findViewById(R.id.submitSocialsButton);
        submitSocialsButton.setOnClickListener(v -> submitSocials());

        Button goToSocialsButton = findViewById(R.id.goToSocialsButton);
        goToSocialsButton.setOnClickListener(v -> goToSocials(v));
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

    //This method attempts to add the provided platform name and link to the user's socials
    private void submitSocials() {
        if (TextUtils.isEmpty(platformNameInput.getText())) {
            Toast.makeText(this, (CharSequence) "Platform name cannot be empty.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(linkInput.getText())) {
            Toast.makeText(this, (CharSequence) "Link cannot be empty.", Toast.LENGTH_SHORT).show();
        } else {
            if (SocialsManager.addLink(AccessUsers.getLoggedInUser(),
                    platformNameInput.getText().toString(), linkInput.getText().toString())) {
                Toast.makeText(this, (CharSequence) "The link was successfully added.", Toast.LENGTH_SHORT).show();
                goToSocials(new View(this));
            } else {
                Toast.makeText(this, (CharSequence) "Please enter a valid link.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //This method lets the user navigate back to the socials page
    private void goToSocials(View v) {
        Intent intent = new Intent(this, Socials.class);
        startActivity(intent);
    }
}
