package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessRequests;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.business.IAccessRequests;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.IConnectionsManager;
import comp3350.highschoolhub.objects.Request;
import comp3350.highschoolhub.objects.User;

//This class is used for running the user_connections_options page.
public class ConnectionsUserOptions extends Activity {

    private Request request;

    private User user;

    private User loggedIn;

    private IAccessRequests accessRequests;

    private IAccessUsers accessUsers;

    private IConnectionsManager connectionsManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_connections_options);

        accessUsers = new AccessUsers();
        accessRequests = new AccessRequests();
        connectionsManager = new ConnectionsManager();
        request = ConnectionsManager.getRequest();
        user = ConnectionsManager.getRecipientUser();
        loggedIn = AccessUsers.getLoggedInUser();

        final TextView titleText = findViewById(R.id.userOptionText);
        String showTitleText = ConnectionsManager.getTitleText();
        titleText.setText(showTitleText);

        //Set up accept or request button.
        String topButtonText = ConnectionsManager.acceptOrRequest();

        final Button topButton = findViewById(R.id.acceptRequestButton);
        topButton.setText(topButtonText);

        final Button bottomButton = findViewById(R.id.cancelButton);
        bottomButton.setText("Cancel");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void updateRequestOnClick(View v) {
        Request updated = connectionsManager.updateRequest(loggedIn, request);

        accessRequests.updateRequest(updated);

        finish();
    }

    public void goBackOnCancel(View v) {
        finish();
    }
}
