package edu.utsa.cs3443.rowdystudenthealth.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import edu.utsa.cs3443.rowdystudenthealth.LoginActivity;
import edu.utsa.cs3443.rowdystudenthealth.MainActivity;
import edu.utsa.cs3443.rowdystudenthealth.MenuActivity;
import edu.utsa.cs3443.rowdystudenthealth.MyAppointmentsActivity;
import edu.utsa.cs3443.rowdystudenthealth.R;
import edu.utsa.cs3443.rowdystudenthealth.ScheduleActivity;
import edu.utsa.cs3443.rowdystudenthealth.model.AppointmentList;

/**
 * AppointmentsController.java
 * Description: Controller that manages user interaction with tha app
 * and communicates between the view and the model.
 * Variables include:
 * activity - an Activity used to update views of the app.
 * manager - an AssetManager used to access the data files.
 * context - a Context used to access data files to read and write to.
 * username - a String with the username of the user that is signed in.
 * key - a String with the key for a key-value pair used to stores the username.
 * textViewsUsed - an int with the number of appointments being displayed on text views.
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
public class AppointmentsController implements View.OnClickListener {

    // variables
    private Activity activity;
    private AssetManager manager;
    private Context context;
    private String username;
    private static String key = "user_name";
    private static String key2 = "appointment";
    private int[] buttonIDs;
    private int textViewsUsed;
    private int[] dateTextViewIDs = {R.id.date_textView1, R.id.date_textView2,
            R.id.date_textView3, R.id.date_textView4, R.id.date_textView5,
            R.id.date_textView6, R.id.date_textView7, R.id.date_textView8,
            R.id.date_textView9, R.id.date_textView10, R.id.date_textView11,
            R.id.date_textView12, R.id.date_textView13, R.id.date_textView14,
            R.id.date_textView15, R.id.date_textView16, R.id.date_textView17,
            R.id.date_textView18, R.id.date_textView19, R.id.date_textView20,
            R.id.date_textView21, R.id.date_textView22, R.id.date_textView23,
            R.id.date_textView24, R.id.date_textView25};
    private int[] timeTextViewIDs = {R.id.time_textView1, R.id.time_textView2,
            R.id.time_textView3, R.id.time_textView4, R.id.time_textView5,
            R.id.time_textView6, R.id.time_textView7, R.id.time_textView8,
            R.id.time_textView9, R.id.time_textView10, R.id.time_textView11,
            R.id.time_textView12, R.id.time_textView13, R.id.time_textView14,
            R.id.time_textView15, R.id.time_textView16, R.id.time_textView17,
            R.id.time_textView18, R.id.time_textView19, R.id.time_textView20,
            R.id.time_textView21, R.id.time_textView22, R.id.time_textView23,
            R.id.time_textView24, R.id.time_textView25};
    private int[] vaccineTextViewIDs = {R.id.vaccine_textView1, R.id.vaccine_textView2,
            R.id.vaccine_textView3, R.id.vaccine_textView4, R.id.vaccine_textView5,
            R.id.vaccine_textView6, R.id.vaccine_textView7, R.id.vaccine_textView8,
            R.id.vaccine_textView9, R.id.vaccine_textView10, R.id.vaccine_textView11,
            R.id.vaccine_textView12, R.id.vaccine_textView13, R.id.vaccine_textView14,
            R.id.vaccine_textView15, R.id.vaccine_textView16, R.id.vaccine_textView17,
            R.id.vaccine_textView18, R.id.vaccine_textView19, R.id.vaccine_textView20,
            R.id.vaccine_textView21, R.id.vaccine_textView22, R.id.vaccine_textView23,
            R.id.vaccine_textView24, R.id.vaccine_textView25};

    // constructor
    public AppointmentsController( Activity activity, AssetManager manager ) {
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

        // If the menu button was clicked
        if (view.getId() == R.id.menu_Button) {

            // Intent object to store info that the next activity needs
            Intent intent = new Intent(activity, MenuActivity.class);
            // Store the user name
            intent.putExtra(key, username);
            // Start activity
            ActivityCompat.startActivityForResult(activity, intent
                    , 0, null);
        }
        // Else if the My Appointments button was clicked
        if (view.getId() == R.id.my_appointments_Button) {

            // Intent object to store info that the next activity needs
            Intent intent = new Intent(activity, MyAppointmentsActivity.class);
            // Store the user name
            intent.putExtra(key, username);
            // Start activity
            activity.startActivity(intent);
        }
        // Else an appointment button was clicked
        else {
            // Loop through the number of appointments displayed
            for (int i = 0; i < textViewsUsed; i++) {
                // If the button id matches the button clicked
                if (view.getId() == buttonIDs[i]) {

                    // Save the appointment text for the texViewIDs at the same index
                    TextView tvDate = (TextView) activity.findViewById(dateTextViewIDs[i]);
                    String date = tvDate.getText().toString();

                    TextView tvTime = (TextView) activity.findViewById(timeTextViewIDs[i]);
                    String time = tvTime.getText().toString();

                    TextView tvVaccine = (TextView) activity.findViewById(vaccineTextViewIDs[i]);
                    String vaccine = tvVaccine.getText().toString();

                    // Format appointment text
                    String appointment = date + " " + time + " " + vaccine;

                    // Intent object to store info that the next activity needs
                    Intent intent = new Intent(activity, ScheduleActivity.class);
                    // Store the user name
                    intent.putExtra(key, username);
                    // Store the appointment selected
                    intent.putExtra(key2, appointment);
                    // Start activity
                    ActivityCompat.startActivityForResult(activity, intent
                            , 0, null);
                }
            }
        }
    }

    /**
     * Method: appointmentsInfo
     * Description: Displays the info for the appointments available to schedule.
     * Calls upon:
     * displayText: to display the info for the appointment.
     * @param name - a String with the username of the user signed in.
     * @param buttonIDs - Array of integers used to store the ids
     *                    for buttons that schedule an appointment.
     */
    public void appointmentsInfo( String name, int buttonIDs[] ) {

        // Set buttonIDs to match the button clicked with the corresponding appointment.
        this.buttonIDs = buttonIDs;
        // Set the username for the signed in user.
        username = name;
        // Context for working with files
        context = activity.getApplicationContext();

        // load the appointments
        AppointmentList appointments = new AppointmentList();
        appointments.loadAppointments(manager, context);

        // If there are more text views than appointments to display
        if (appointments.getNumberOfAvailable() < dateTextViewIDs.length) {
            // Display appointments
            for (int i = 0; i < appointments.getNumberOfAvailable(); i++) {

                String date = appointments.getAvailable(i).getDate();
                String time = appointments.getAvailable(i).getTime();
                String vaccine = appointments.getAvailable(i).getVaccine();

                displayText(date, time, vaccine,  dateTextViewIDs[i],
                        timeTextViewIDs[i], vaccineTextViewIDs[i]);
            }
            // Hide empty text views
            textViewsUsed = appointments.getNumberOfAvailable();
            for (int j = textViewsUsed; j < dateTextViewIDs.length; j++) {

                displayText("", "", "",  dateTextViewIDs[j],
                        timeTextViewIDs[j], vaccineTextViewIDs[j]);
            }
        }
        // Else display all text views
        else {
            textViewsUsed = 0;
            for (int i = 0; i < dateTextViewIDs.length; i++) {

                String date = appointments.getAvailable(i).getDate();
                String time = appointments.getAvailable(i).getTime();
                String vaccine = appointments.getAvailable(i).getVaccine();

                displayText(date, time, vaccine,  dateTextViewIDs[i],
                        timeTextViewIDs[i], vaccineTextViewIDs[i]);
                textViewsUsed++;
            }
        }
    }

    /**
     * Method: displayText
     * Description: Displays the text for an appointment.
     * @param date - a String with the date of the appointment.
     * @param time - a String with the time of the appointment.
     * @param vaccine - a String with the vaccine that the appointment is for.
     * @param dateTextViewID - an int with the id for a text view that display the date.
     * @param timeTextViewID - an int with the id for a text view that display the time.
     * @param vaccineTextViewID - an int with the id for a text view that display the vaccine.
     */
    private void displayText( String date, String time, String vaccine, int dateTextViewID,
                              int timeTextViewID, int vaccineTextViewID) {

        // Object to represent the text view that displays the date
        TextView tvDate = (TextView) activity.findViewById(dateTextViewID);
        // Set text
        tvDate.setText(date);

        // Object to represent the text view that displays the date
        TextView tvTime = (TextView) activity.findViewById(timeTextViewID);
        // Set text
        tvTime.setText(time);

        // Object to represent the text view that displays the date
        TextView tvVaccine = (TextView) activity.findViewById(vaccineTextViewID);
        // Set text
        tvVaccine.setText(vaccine);
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

    /**
     * Method: getSelectedAppointment
     * Description: Gets the selected appointment from the Intent object.
     * @param intent - an Intent object that holds the selected appointment.
     * @return - a String with the selected appointment.
     */
    public static String getSelectedAppointment( Intent intent ) {
        return intent.getStringExtra(key2);
    }
}