package comp3350.highschoolhub.presentation;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import comp3350.highschoolhub.R;
import comp3350.highschoolhub.business.AccessUsers;
import comp3350.highschoolhub.business.ConnectionsManager;
import comp3350.highschoolhub.business.IAccessUsers;
import comp3350.highschoolhub.business.IConnectionsManager;
import comp3350.highschoolhub.objects.User;

public class MyProfile extends Activity {
    private static final int STORAGE_PERMISSION_CODE=1;
    //flag used to prevent spinner from choosing the first item
    boolean isFirstTime=true;
    //new name input
    EditText nameInput;
    //new Marital status
    EditText maritalInput;
    //new Bio
    EditText bioInput;
    String[] items=new String[]{"Edit","Married","Single","Widowed","Divorced"};
    //fetching the loggedin user

    private User loggedIn;

    private final IConnectionsManager connectionsManager = new ConnectionsManager();

    private IAccessUsers accessUsers;
    //creating a string which is easier since both names will be combined and placed in a placeholder
    String name;
    SharedPreferences preferences;
    String numberOfConnections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        preferences= this.getPreferences(Context.MODE_PRIVATE);

        accessUsers = new AccessUsers();

        loggedIn=AccessUsers.getLoggedInUser();

        name = loggedIn.getFirstName() + " " + loggedIn.getLastName();

        numberOfConnections = connectionsManager.getHighSchoolConnections(loggedIn, accessUsers.getUsers()).size() + "";

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

        Button highSchoolsButton = findViewById(R.id.highSchoolsButton);
        highSchoolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHighSchools();
            }
        });

        Button logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
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


        maritalInput = findViewById(R.id.marital_status);
        //Set original martial status
        maritalInput.setText(loggedIn.getMaritalStatus());
        //disable editing the editText
        maritalInput.setEnabled(false);
        //create dropdown menu
        Spinner dropdown= findViewById(R.id.chooseStatus);
        //Linking to the array of marital status
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
                    maritalInput.setText(dropdown.getSelectedItem().toString());
                    //change the martial status for the logged in user
                    loggedIn.changeStatus(dropdown.getSelectedItem().toString());
                    //update user in persistence
                    accessUsers.updateUser(loggedIn);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Button privacyButton = findViewById(R.id.privacyInfoButton);
        privacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showPrivacyInfo(); }
        });

        //displaying the user name in the UI
        TextView textView = findViewById(R.id.user_name);
        textView.setText(name);

        //displaying the number of connections a user have in the UI
        TextView textView1 = findViewById(R.id.number_of_connections);
        textView1.setText(numberOfConnections);

        //Displaying the marital status of the user in the UI
        TextView textView4 = maritalInput;
        textView4.setText(loggedIn.getMaritalStatus());

        //displaying the bio of the user in the UI
        TextView textView3 = findViewById(R.id.Bio);
        textView3.setText(loggedIn.getBio());

        String imageString= preferences.getString("profileImage",null);
        if(imageString!=null){
            System.out.println("In");
            ImageView imageView=findViewById(R.id.profile_image);
            imageView.setImageURI(Uri.parse(imageString));
        }

        if(ContextCompat.checkSelfPermission(MyProfile.this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MyProfile.this,"Access to photos is granted",Toast.LENGTH_SHORT).show();
        }
        else{
            requestStoragePermission();
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==Activity.RESULT_OK && data!=null){
            Uri profileImage=data.getData();
            ImageView imageView=findViewById(R.id.profile_image);
            if(profileImage!=null){
                SharedPreferences.Editor editor=preferences.edit();
                imageView.setImageURI(profileImage);
                editor.putString("profileImage",profileImage.toString());
                editor.apply();
            }
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
        Intent highSchools = new Intent(this, HighSchools.class);
        startActivity(highSchools);
    }

    private void initializeNameEdit(){
        //displaying the user name in the UI
        nameInput = findViewById(R.id.user_name);
        //Displaying the user name
        nameInput.setText(loggedIn.getFirstName() + " " + loggedIn.getLastName());
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

    private void buttonHandler(EditText text,ImageButton btn,boolean flag) {
        btn.setOnClickListener(new View.OnClickListener() {
            //Counter used to keep track of number of clicks on the edit button for name
            int save = 0;

            @Override
            public void onClick(View v) {
                WindowCompat.getInsetsController(getWindow(), text).show(WindowInsetsCompat.Type.ime());
                //increase counter by one allowing user to edit text
                save++;
                //Enable the button
                text.setEnabled(true);
                //Display cursor to show that the app is ready to accept the new input for field
                text.setCursorVisible(true);
                //keyboard popped up
                text.requestFocus();
                if (save > 1) {
                    //Display new input for edit text box
                    text.setText(text.getText().toString());
                    //change the edit text box for the active user, depending on flag
                    //flag will determine whether or not we are editing the name or the bio
                    String[] splitText = text.getText().toString().split(" ");
                    if (flag && splitText.length >= 2) {
                        loggedIn.changeName(splitText[0], splitText[1]);
                    } else if (flag) {
                        initializeNameEdit();
                    } else {
                        loggedIn.changeBio(text.getText().toString());
                    }
                    //update the active user object in persistence
                    accessUsers.updateUser(loggedIn);
                    //Disable the button ( second click to confirm end of editing)
                    text.setEnabled(false);
                    //Remove cursor showing the app is done allowing edits for editText
                    text.setCursorVisible(false);
                    //Set the disabled button color to black instead of grey
                    text.setTextColor(Color.BLACK);
                    //remove focus
                    text.clearFocus();
                    //reset button clicks
                    save = 0;
                }
            }
        });
    }

    public void showHighSchoolExplore(View view) {
        Intent highSchoolExplore = new Intent(this, HighSchoolExplore.class);
        startActivity(highSchoolExplore);
    }

    private void showPrivacyInfo() {
        Intent privacy = new Intent(this, PrivacyInfo.class);
        startActivity(privacy);
    }

    private void logout() {
        AccessUsers.clearLoggedInUser();
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }

    private void requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this).setTitle("Permission needed");
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==STORAGE_PERMISSION_CODE){
            if(grantResults.length >0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission GRANTED",Toast.LENGTH_SHORT).show();
            }
        }
    }
}