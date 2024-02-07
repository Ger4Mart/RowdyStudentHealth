package edu.utsa.cs3443.rowdystudenthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.utsa.cs3443.rowdystudenthealth.controller.AppointmentsController;
/*import edu.utsa.cs3443.rowdystudenthealth.controller.MainController;*/
import edu.utsa.cs3443.rowdystudenthealth.controller.MenuController;
import edu.utsa.cs3443.rowdystudenthealth.controller.ScheduleController;
import edu.utsa.cs3443.rowdystudenthealth.controller.LoginController;
/**
 * AppointmentsActivity.java
 * Description: Activity that manages the view, controller, and models required
 * for displaying a screen to select an appointment.
 * Variables include:
 * controller - AppointmentsController used to communicate between the view and the model.
 * buttonIDs - Array of integers used to store the ids for buttons that schedule an appointment.
 * @author Kaleb Phillips (bzr855)
 * UTSA CS 3443-003 - Team Project
 * Spring 2023
 */
public class AppointmentsActivity extends AppCompatActivity {

    // variables
    private AppointmentsController controller;
    private int[] buttonIDs = {R.id.appointment_Button1,
            R.id.appointment_Button2, R.id.appointment_Button3, R.id.appointment_Button4,
            R.id.appointment_Button5, R.id.appointment_Button6, R.id.appointment_Button7,
            R.id.appointment_Button8, R.id.appointment_Button9, R.id.appointment_Button10,
            R.id.appointment_Button11, R.id.appointment_Button12, R.id.appointment_Button13,
            R.id.appointment_Button14, R.id.appointment_Button15, R.id.appointment_Button16,
            R.id.appointment_Button17, R.id.appointment_Button18, R.id.appointment_Button19,
            R.id.appointment_Button20, R.id.appointment_Button21, R.id.appointment_Button22,
            R.id.appointment_Button23, R.id.appointment_Button24, R.id.appointment_Button25};

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        // Controller object for communicating between the view and the model
        controller = new AppointmentsController(this, getAssets());

        // Set up the menu button
        findViewById(R.id.menu_Button).setOnClickListener(controller);

        // Set up the My Appointments button
        findViewById(R.id.my_appointments_Button).setOnClickListener(controller);

        // Set up the appointment buttons
        for (int i = 0; i < buttonIDs.length; i++) {
            findViewById(buttonIDs[i]).setOnClickListener(controller);
        }

        // If activity was launched from the MainActivity
        if (MenuController.getUsername(getIntent()) != null) {
            controller.appointmentsInfo(MenuController.getUsername(getIntent()), buttonIDs);
        }
        // Else if activity was launched from ScheduleActivity
        else if (ScheduleController.getUsername(getIntent()) != null) {
            controller.appointmentsInfo(ScheduleController.getUsername(getIntent()), buttonIDs);
        }
        // Error case
        else {
            controller.appointmentsInfo(LoginController.getUsername(getIntent()), buttonIDs);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Reload the data
        // If activity was launched from the MainActivity
        if (LoginController.getUsername(getIntent()) != null) {
            controller.appointmentsInfo(LoginController.getUsername(getIntent()), buttonIDs);
        }
        // Else if activity was launched from ScheduleActivity
        else if (ScheduleController.getUsername(getIntent()) != null) {
            controller.appointmentsInfo(ScheduleController.getUsername(getIntent()), buttonIDs);
        }
        // Error case
        else {
            controller.appointmentsInfo(LoginController.getUsername(getIntent()), buttonIDs);
        }
    }
}