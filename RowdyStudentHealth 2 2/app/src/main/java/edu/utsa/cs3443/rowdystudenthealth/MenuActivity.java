package edu.utsa.cs3443.rowdystudenthealth;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.rowdystudenthealth.controller.AppointmentsController;
import edu.utsa.cs3443.rowdystudenthealth.controller.LoginController;
import edu.utsa.cs3443.rowdystudenthealth.controller.MenuController;
import edu.utsa.cs3443.rowdystudenthealth.model.Appointment;

public class MenuActivity extends AppCompatActivity {

    private MenuController controller;

    private static final int[] buttonArray = {
            R.id.faqButton,
            R.id.apptScehdule,
            R.id.apptsView,
            R.id.signoutButton
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        controller = new MenuController(this);
        for(int id : buttonArray){
            setupButton(id);
        }

        // TODO added this block of code
        if (getCallingActivity() != null) {
            // If the activity that called this activity was AppointmentsActivity
            if (getCallingActivity().getShortClassName().equals(".AppointmentsActivity")) {
                // Set username
                controller.setUsername(
                        AppointmentsController.getUsername(getIntent()));
            }
            // If the activity that called this activity was LoginActivity
            else if (getCallingActivity().getShortClassName().equals(".LoginActivity")) {
                // Set username
                controller.setUsername(LoginController.getUsername(getIntent()));
            }
            // Error case
            else {
                System.out.println("ERROR: Could not identify calling activity");
                // Attempt to set username
                controller.setUsername(
                        LoginController.getUsername(getIntent()));
            }
        }
        else {
            System.out.println("ERROR: calling activity is null");
            // Attempt to set username
            controller.setUsername(
                    LoginController.getUsername(getIntent()));
        } // TODO added this block of code

        //System.out.println("entered");
    }
    private void setupButton(int buttonID) {
        //Find button to initialize
        Button button = findViewById(buttonID);
        button.setOnClickListener(controller);
    }
}
