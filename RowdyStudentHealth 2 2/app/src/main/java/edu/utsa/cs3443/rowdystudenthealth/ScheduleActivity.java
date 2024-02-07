package edu.utsa.cs3443.rowdystudenthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.utsa.cs3443.rowdystudenthealth.controller.AppointmentsController;
import edu.utsa.cs3443.rowdystudenthealth.controller.MyAppointmentsController;
import edu.utsa.cs3443.rowdystudenthealth.controller.ScheduleController;

/**
 * ScheduleActivity.java
 * Description: Activity that manages the view, controller, and models required
 * for displaying a screen to schedule a selected appointment.
 * Variables include:
 * controller - ScheduleController used to communicate between the view and the model.
 * buttonIDs - Array of integers used to store the ids for buttons.
 * @author Kaleb Phillips (bzr855)
 * UTSA CS 3443-003 - Team Project
 * Spring 2023
 */
public class ScheduleActivity extends AppCompatActivity {

    // variables
    private ScheduleController controller;
    private int[] buttonIDs = {R.id.cancel_button, R.id.confirm_button};

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        // Controller object for communicating between the view and the model
        controller = new ScheduleController(this, getAssets());

        // Set up the buttons
        for (int i = 0; i < buttonIDs.length; i++) {
            findViewById(buttonIDs[i]).setOnClickListener(controller);
        }

        if (getCallingActivity() != null) {

            // If the activity that called this activity was MyAppointmentsActivity
            if (getCallingActivity().getShortClassName().equals(".MyAppointmentsActivity")) {

                // Launch controller to cancel appointment
                controller.appointmentInfo(
                        MyAppointmentsController.getSelectedAppointment(getIntent()),
                        AppointmentsController.getUsername(getIntent()), "Cancel");
            }
            // Else if the activity that called this activity was AppointmentsActivity
            else if (getCallingActivity().getShortClassName().equals(".AppointmentsActivity")) {

                // Launch controller to schedule an appointment
                controller.appointmentInfo(
                        AppointmentsController.getSelectedAppointment(getIntent()),
                        AppointmentsController.getUsername(getIntent()), "Schedule");
            }
            // Error case
            else {

                System.out.println( "ERROR: Could not identify calling activity" );
                // Display selected appointment
                controller.appointmentInfo(
                        AppointmentsController.getSelectedAppointment(getIntent()),
                        AppointmentsController.getUsername(getIntent()), "Error");
            }
        }
        else {
            System.out.println( "ERROR: calling activity is null" );
            controller.appointmentInfo(AppointmentsController.getSelectedAppointment(getIntent()),
                    AppointmentsController.getUsername(getIntent()), "Error");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Reload the data
        if (getCallingActivity() != null) {

            // If the activity that called this activity was MyAppointmentsActivity
            if (getCallingActivity().getShortClassName().equals(".MyAppointmentsActivity")) {

                // Launch controller to cancel appointment
                controller.appointmentInfo(
                        MyAppointmentsController.getSelectedAppointment(getIntent()),
                        AppointmentsController.getUsername(getIntent()), "Cancel");
            }
            // Else if the activity that called this activity was AppointmentsActivity
            else if (getCallingActivity().getShortClassName().equals(".AppointmentsActivity")) {

                // Launch controller to schedule an appointment
                controller.appointmentInfo(
                        AppointmentsController.getSelectedAppointment(getIntent()),
                        AppointmentsController.getUsername(getIntent()), "Schedule");
            }
            // Error case
            else {

                System.out.println( "ERROR: Could not identify calling activity" );
                // Display selected appointment
                controller.appointmentInfo(
                        AppointmentsController.getSelectedAppointment(getIntent()),
                        AppointmentsController.getUsername(getIntent()), "Error");
            }
        }
        else {
            System.out.println( "ERROR: calling activity is null" );
            controller.appointmentInfo(AppointmentsController.getSelectedAppointment(getIntent()),
                    AppointmentsController.getUsername(getIntent()), "Error");
        }
    }
}