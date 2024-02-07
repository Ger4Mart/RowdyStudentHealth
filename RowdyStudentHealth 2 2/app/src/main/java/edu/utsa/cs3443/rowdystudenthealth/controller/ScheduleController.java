package edu.utsa.cs3443.rowdystudenthealth.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.utsa.cs3443.rowdystudenthealth.AppointmentsActivity;
import edu.utsa.cs3443.rowdystudenthealth.MyAppointmentsActivity;
import edu.utsa.cs3443.rowdystudenthealth.R;
import edu.utsa.cs3443.rowdystudenthealth.model.AppointmentList;

/**
 * ScheduleController.java
 * Description: Controller that manages user interaction with tha app
 * and communicates between the view and the model.
 * Variables include:
 * activity - an Activity used to update views of the app.
 * manager - an AssetManager used to access the data files.
 * context - a Context used to access data files to read and write to.
 * appointment - a String with the appointment.
 * username - a String with the username of the user that is signed in.
 * mode - a String with the mode of the activity (e.g. Cancel/Schedule)
 * key - a String with the key for a key-value pair used to stores the username.
 * dateTextViewIDs - Array of integers used to store the ids
 *                   for text views that display the date of the appointment.
 * timeTextViewIDs - Array of integers used to store the ids
 *                   for text views that display the time of the appointment.
 * vaccineTextViewIDs - Array of integers used to store the ids for text views that
 *                      display the type of vaccine the appointment is for.
 * @author Kaleb Phillips (bzr855)
 * UTSA CS 3443-003 - Team Project
 * Spring 2023
 */
public class ScheduleController implements View.OnClickListener {

    // variables
    private Activity activity;
    private AssetManager manager;
    private Context context;
    private String appointment;
    private String username;
    private String mode;
    private static String key = "user_name";

    // constructor
    public ScheduleController( Activity activity, AssetManager manager ) {
        this.activity = activity;
        this.manager = manager;
    }

    /**
     * Method: onClick
     * Description: Listens for clicks on the buttons and reacts by displaying a new screen.
     * @param view - the app view
     */
    @Override
    public void onClick( View view ) {

        // If the cancel button was clicked
        if (view.getId() == R.id.cancel_button) {

            // If in cancel mode
            if (mode.equals("Cancel")) {
                // Intent object to store info that the next activity needs
                Intent intent = new Intent(activity, MyAppointmentsActivity.class);
                // Store the user name
                intent.putExtra(key, username);
                activity.startActivity(intent);
            }
            // If in schedule mode
            if (mode.equals("Schedule")) {
                // Intent object to store info that the next activity needs
                Intent intent = new Intent(activity, AppointmentsActivity.class);
                // Store the user name
                intent.putExtra(key, username);
                activity.startActivity(intent);
            }
        }
        // Else if the confirm button was clicked
        else if (view.getId() == R.id.confirm_button) {

            // Context for working with files
            context = activity.getApplicationContext();

            // load the appointments
            AppointmentList appointments = new AppointmentList();
            appointments.loadAppointments(manager, context);

            // Perform action on selected appointment based on the mode
            activityMode();
        }
    }

    /**
     * Method: appointmentInfo
     * Description: Sets the info passed in for the appointment that the
     * controller needs and displays the info for the appointment.
     * Calls upon:
     * displayText: to display the info for the appointment.
     * @param appointment - a String with the select appointment.
     * @param username - a String with the username of the user that is signed in.
     * @param mode - a String with cancel/schedule to denote the user's selection.
     */
    public void appointmentInfo( String appointment, String username, String mode ) {

        // Save appointment selected and username
        this.appointment = appointment;
        this.username = username;
        this.mode = mode;

        // Display selected appointment
        displayText(appointment);
    }

    /**
     * Method: displayText
     * Description: Displays formatted text for an appointment.
     * @param appointment - a String with the select appointment.
     */
    private void displayText( String appointment ) {

        // If in cancel mode
        if (mode.equals("Cancel")) {
            // Object to represent the text view that displays cancel
            TextView tvCancel = (TextView) activity.findViewById(R.id.textView);
            tvCancel.setText("Cancel the appointment for");
        }
        // Else in cancel mode
        else {
            // Object to represent the text view that displays cancel
            TextView tvSchedule = (TextView) activity.findViewById(R.id.textView);
            tvSchedule.setText("Schedule an appointment for");
        }

        // Format the appointment string
        String line = appointment;
        String tokens[] = line.split(" ");
        String text  = tokens[0] + " at " + tokens[1] + tokens[2] +
                " for a " + tokens[3] + " vaccine ?";
        // Object to represent the text view that displays the appointment
        TextView tvAppointment = (TextView) activity.findViewById(R.id.selected_textView);
        // Set text
        tvAppointment.setText(text);
    }

    /**
     * Method: activityMode
     * Description: Determines whether to update the file with a canceled appointment or
     * scheduled appointment and displays a toast to let the user know the action was successful.
     * Calls upon:
     * updateFile - from AppointmentList.java to update the file in the correct mode.
     */
    private void activityMode() {

        // Determine whether to schedule or cancel the appointment selected
        if (mode.equals("Schedule")) {

            // load the appointments
            AppointmentList appointments = new AppointmentList();
            appointments.loadAppointments(manager, context);

            // Update file to reflect user scheduling for the appointment
            appointments.updateFile(context, appointment, username, "Schedule");

            Toast.makeText(activity, "Appointment scheduled",
                    Toast.LENGTH_LONG).show();

            // Intent object to store info that the next activity needs
            Intent intent = new Intent(activity, AppointmentsActivity.class);
            // Store the user name
            intent.putExtra(key, username);
            activity.startActivity(intent);
        }
        else if (mode.equals("Cancel")) {
            Toast.makeText(activity, "Appointment canceled",
                    Toast.LENGTH_LONG).show();

            // load the appointments
            AppointmentList appointments = new AppointmentList();
            appointments.loadAppointments(manager, context);

            // Update file to reflect user canceling the appointment
            appointments.updateFile(context, appointment, username, "Cancel");

            // Intent object to store info that the next activity needs
            Intent intent = new Intent(activity, MyAppointmentsActivity.class);
            // Store the user name
            intent.putExtra(key, username);
            activity.startActivity(intent);
        }
        // Error mode
        else {
            Toast.makeText(activity, "Error",
                    Toast.LENGTH_LONG).show();
        }
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
}