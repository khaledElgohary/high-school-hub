package comp3350.highschoolhub.presentation;

import android.app.Activity;
import android.os.Bundle;

import comp3350.highschoolhub.R;

public class Connections extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_connections);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
