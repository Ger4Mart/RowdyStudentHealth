package edu.utsa.cs3443.rowdystudenthealth.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import edu.utsa.cs3443.rowdystudenthealth.AppointmentsActivity;
import edu.utsa.cs3443.rowdystudenthealth.FaqActivity;
import edu.utsa.cs3443.rowdystudenthealth.MenuActivity;
import edu.utsa.cs3443.rowdystudenthealth.MyAppointmentsActivity;
import edu.utsa.cs3443.rowdystudenthealth.R;
import edu.utsa.cs3443.rowdystudenthealth.ScheduleActivity;

public class MenuController implements View.OnClickListener{

    MenuActivity menuActivity;
    private String username; // TODO changed this
    private static String key = "username";
    public MenuController(MenuActivity menuActivity) {
        this.menuActivity = menuActivity;
    }


    /**
     * Method: getUsername
     * Description: Gets the username of the user signed in from the Intent object.
     * @param intent - an Intent object that holds the username.
     * @return - a String with the username of the signed in user.
     */
    public static String getUsername( Intent intent ) {
        return intent.getStringExtra(key);
    }

    @Override
    public void onClick(View v) {

        /*Initiate the next screen based on the button pressed*/
        Button buttonClicked = v.findViewById(v.getId());

        if(buttonClicked.getId() == R.id.faqButton){
            Intent newIntent = new Intent(menuActivity, FaqActivity.class);
            //Toast.makeText(v.getContext().getApplicationContext(), "1s clicked", Toast.LENGTH_SHORT).show();
            this.menuActivity.startActivity(newIntent);
        }
        if(buttonClicked.getId() == R.id.apptScehdule){
            Intent newIntent = new Intent(menuActivity, AppointmentsActivity.class);
            //Toast.makeText(v.getContext().getApplicationContext(), "2nd clicked", Toast.LENGTH_SHORT).show();
            // Store the name of the user
            newIntent.putExtra(key, username); // TODO changed this
            this.menuActivity.startActivity(newIntent);
        }
        if(buttonClicked.getId() == R.id.apptsView){
            Intent newIntent = new Intent(menuActivity, MyAppointmentsActivity.class);
            //Toast.makeText(v.getContext().getApplicationContext(), "3rd clicked", Toast.LENGTH_SHORT).show();
            // Store the name of the user
            newIntent.putExtra(key, username); // TODO changed this
            //this.menuActivity.startActivity(newIntent);

            // Start activity
            // TODO changed this ( Needed to tell MyAppointmentsActivity where to get username from )
            ActivityCompat.startActivityForResult(this.menuActivity, newIntent
                    , 0, null);
        }
        if(buttonClicked.getId() == R.id.signoutButton){
            Intent newIntent = new Intent(menuActivity, MyAppointmentsActivity.class);
            Toast.makeText(v.getContext().getApplicationContext(), "You have been logged out", Toast.LENGTH_SHORT).show();
            this.menuActivity.startActivity(newIntent);
        }
    }

    public void setUsername( String username) {
        this.username = username;
    }
}
