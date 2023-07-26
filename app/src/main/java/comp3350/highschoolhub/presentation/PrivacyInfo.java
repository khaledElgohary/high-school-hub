package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.IPrivacyManager;
import comp3350.highschoolhub.business.PrivacyManager;

public class PrivacyInfo extends Activity {

    private IPrivacyManager privacyManager;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.privacy_info);

        privacyManager = new PrivacyManager();

        String publicInfo = privacyManager.getPublicInfoString();
        String privateInfo = privacyManager.getPrivateInfoString();

        //Back button
        Button privacyBackButton = findViewById(R.id.privacyBackButton);
        privacyBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToProfile(); }
        });

        //displaying public info
        TextView publicText = findViewById(R.id.publicInfoText);
        publicText.setText(publicInfo);

        //displaying private info
        TextView privateText = findViewById(R.id.privateInfoText);
        privateText.setText(privateInfo);
    }

    private void goToProfile() {
        Intent profile = new Intent(this, MyProfile.class);
        startActivity(profile);
    }
}
