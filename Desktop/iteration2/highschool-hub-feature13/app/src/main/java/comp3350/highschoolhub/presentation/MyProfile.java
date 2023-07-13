package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.IConnectionsManager;
import comp3350.highschoolhub.objects.User;

public class MyProfile extends Activity {
    //flag used to prevent spinner from choosing the first item
    boolean isFirstTime=true;
    //new name input
    EditText nameInput;
    //new Maritial status
    EditText maritialInput;
    //new Bio
    EditText bioInput;
    String[] items=new String[]{"Edit","Married","Single","Widowed","Divorced"};
    //fetching the loggedin user
    private final AccessUsers updater=new AccessUsers();

    private final User loggedIn=AccessUsers.getLoggedInUser();

    private final IConnectionsManager connectionsManager = new ConnectionsManager();

    private final IAccessUsers accessUsers = new AccessUsers();

    String numberOfConnections = connectionsManager.getHighSchoolConnections(loggedIn, accessUsers.getUsers()).size() + "";

    String highSchoolName = loggedIn.getHighSchool().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Button button = findViewById(R.id.backToConnections);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConnections();
            }
        });

        Button socialsButton = findViewById(R.id.socialLinksButton);
        socialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSocials();
            }
        });

        Button highSchoolsButton = findViewById(R.id.editHighSchoolButton);
        highSchoolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHighSchools();
            }
        });

        ImageButton editImage=findViewById(R.id.edit_photo);
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });

        initializeNameEdit();
        ImageButton editName=(ImageButton) findViewById(R.id.edit_name);
        //handling the name edit button
        buttonHandler(nameInput,editName,true);

        initializeBioEdit();
        ImageButton bio=findViewById(R.id.edit_bio);
        //handling the bio button
        buttonHandler(bioInput,bio,false);


        maritialInput=findViewById(R.id.marital_status);
        //Set original maritial status
        maritialInput.setText(loggedIn.getMaritalStatus());
        //disable editing the editText
        maritialInput.setEnabled(false);
        //create dropdown menu
        Spinner dropdown= findViewById(R.id.chooseStatus);
        //Linking to the array of maritial status
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items);
        //Setting adapter
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //checking to prevent selecting first item automatically
                if(isFirstTime){
                    isFirstTime=false;
                }
                else{
                    //Assign the text to the edittext
                    maritialInput.setText(dropdown.getSelectedItem().toString());
                    //change the maritial status for the logged in user
                    loggedIn.changeStatus(dropdown.getSelectedItem().toString());
                    //update user in persistence
                    updater.updateUser(loggedIn);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        //displaying the number of connections a user have in the UI
        TextView textView1 = findViewById(R.id.number_of_connections);
        textView1.setText(numberOfConnections);

        //displaying the highschool name the user was in the UI
        TextView textView2 = findViewById(R.id.highschool_name);
        textView2.setText(highSchoolName);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK && data!=null){
            Uri selectedImage= data.getData();
            ImageView imageView=findViewById(R.id.profile_image);
            imageView.setImageURI(selectedImage);
        }
    }
    //this method is used to return to the connections page when the button is pressed
    private void showConnections() {
        Intent connections = new Intent(this, Connections.class);
        startActivity(connections);
    }

    //this method is used to go to the page where the user's socials are.
    private void showSocials() {
        Intent socials = new Intent(this, Socials.class);
        startActivity(socials);
    }

    private void showHighSchools() {
        Intent highSchools = new Intent(this, HighSchoolList.class);
        startActivity(highSchools);
    }

    private void initializeNameEdit(){
        //displaying the user name in the UI
        nameInput = findViewById(R.id.user_name);
        //Displaying the user name
        nameInput.setText(loggedIn.getUserName());
        //Disable the button to prevent user from editing name without the usage of edit button
        nameInput.setEnabled(false);
        //Disabled button color changed back to black instead of grey
        nameInput.setTextColor(Color.BLACK);
        //Hide cursor
        nameInput.setCursorVisible(false);
    }

    private void initializeBioEdit(){
        bioInput=findViewById(R.id.Bio);
        bioInput.setText(loggedIn.getBio());
        bioInput.setEnabled(false);
        bioInput.setTextColor(Color.BLACK);
        bioInput.setCursorVisible(false);
    }

    private void buttonHandler(EditText text,ImageButton btn,boolean flag){
        btn.setOnClickListener(new View.OnClickListener() {
            //Counter used to keep track of number of clicks on the edit button for name
            int save=0;
            @Override
            public void onClick(View v) {
                WindowCompat.getInsetsController(getWindow(),text).show(WindowInsetsCompat.Type.ime());
                //increase counter by one allowing user to edit text
                save++;
                //Enable the button
                text.setEnabled(true);
                //Display cursor to show that the app is ready to accept the new input for field
                text.setCursorVisible(true);
                //keyboard popped up
                text.requestFocus();
                if(save>1){
                    //Display new input for edit text box
                    text.setText(text.getText().toString());
                    //change the edit text box for the active user, depending on flag
                    //flag will determine whether or not we are editing the name or the bio
                    if(flag){
                        loggedIn.changeName(text.getText().toString().split(" ")[0],text.getText().toString().split(" ")[1]);
                    }
                    else{
                        loggedIn.changeBio(text.getText().toString());
                    }
                    //update the active user object in persistence
                    updater.updateUser(loggedIn);
                    //Disable the button ( second click to confirm end of editing)
                    text.setEnabled(false);
                    //Remove cursor showing the app is done allowing edits for editText
                    text.setCursorVisible(false);
                    //Set the disabled button color to black instead of grey
                    text.setTextColor(Color.BLACK);
                    //remove focus
                    text.clearFocus();
                    //reset button clicks
                    save=0;
                }
            }
        });
    }
}