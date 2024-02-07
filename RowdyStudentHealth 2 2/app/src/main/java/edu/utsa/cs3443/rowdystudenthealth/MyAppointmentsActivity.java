package edu.utsa.cs3443.rowdystudenthealth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.rowdystudenthealth.controller.AppointmentsController;
import edu.utsa.cs3443.rowdystudenthealth.controller.MenuController;
import edu.utsa.cs3443.rowdystudenthealth.controller.MyAppointmentsController;

public class MyAppointmentsActivity extends AppCompatActivity {

    // variables
    private MyAppointmentsController controller;
    private int[] buttonIDs = {R.id.cancel_Button1,
            R.id.cancel_Button2, R.id.cancel_Button3, R.id.cancel_Button4,
            R.id.cancel_Button5, R.id.cancel_Button6, R.id.cancel_Button7,
            R.id.cancel_Button8, R.id.cancel_Button9, R.id.cancel_Button10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        // Controller object for communicating between the view and the model
        controller = new MyAppointmentsController(this, getAssets());

        // Set up the back button
        findViewById(R.id.back_Button).setOnClickListener(controller);

        // Set up the buttons
        for (int i = 0; i < buttonIDs.length; i++) {
            findViewById(buttonIDs[i]).setOnClickListener(controller);
        }

        if (getCallingActivity() != null) {
            // If the activity that called this activity was AppointmentsActivity
            if (getCallingActivity().getShortClassName().equals(".AppointmentsActivity")) {
                // Display user's appointments
                controller.appointmentsInfo(
                        AppointmentsController.getUsername(getIntent()), buttonIDs);
            }
            // If the activity that called this activity was MenuActivity
            else if (getCallingActivity().getShortClassName().equals(".MenuActivity")) {
                // Display user's appointments
                controller.appointmentsInfo(MenuController.getUsername(getIntent()), buttonIDs);
            }
            // Error case
            else {
                System.out.println("ERROR: Could not identify calling activity");
                // Attempt to display user's appointments
                controller.appointmentsInfo(
                        AppointmentsController.getUsername(getIntent()), buttonIDs);
            }
        }
        else {
            System.out.println("ERROR: calling activity is null");
            controller.appointmentsInfo(
                    AppointmentsController.getUsername(getIntent()), buttonIDs);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Reload the data
        controller.appointmentsInfo(AppointmentsController.getUsername(getIntent()), buttonIDs);
    }
}