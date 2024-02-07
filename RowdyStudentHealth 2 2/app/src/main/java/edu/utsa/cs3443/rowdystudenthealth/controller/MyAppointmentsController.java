package edu.utsa.cs3443.rowdystudenthealth.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import edu.utsa.cs3443.rowdystudenthealth.AppointmentsActivity;
import edu.utsa.cs3443.rowdystudenthealth.R;
import edu.utsa.cs3443.rowdystudenthealth.ScheduleActivity;
import edu.utsa.cs3443.rowdystudenthealth.model.AppointmentList;

public class MyAppointmentsController implements View.OnClickListener {

    // variables
    private Activity activity;
    private AssetManager manager;
    private Context context;
    private String username;
    private static String key = "user_name";
    private static String key2 = "appointment";
    private int[] buttonIDs;
    private int textViewsUsed;
    private int[] dateTextViewIDs = {R.id.myDate_textView1, R.id.myDate_textView2,
            R.id.myDate_textView3, R.id.myDate_textView4, R.id.myDate_textView5,
            R.id.myDate_textView6, R.id.myDate_textView7, R.id.myDate_textView8,
            R.id.myDate_textView9, R.id.myDate_textView10};
    private int[] timeTextViewIDs = {R.id.myTime_textView1, R.id.myTime_textView2,
            R.id.myTime_textView3, R.id.myTime_textView4, R.id.myTime_textView5,
            R.id.myTime_textView6, R.id.myTime_textView7, R.id.myTime_textView8,
            R.id.myTime_textView9, R.id.myTime_textView10};
    private int[] vaccineTextViewIDs = {R.id.myVaccine_textView1, R.id.myVaccine_textView2,
            R.id.myVaccine_textView3, R.id.myVaccine_textView4, R.id.myVaccine_textView5,
            R.id.myVaccine_textView6, R.id.myVaccine_textView7, R.id.myVaccine_textView8,
            R.id.myVaccine_textView9, R.id.myVaccine_textView10};

    // constructor
    public MyAppointmentsController(Activity activity, AssetManager manager ) {
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

        // If the back button was clicked
        if (view.getId() == R.id.back_Button) {

            // Intent object to store info that the next activity needs
            Intent intent = new Intent(activity, AppointmentsActivity.class);
            // Store the user name
            intent.putExtra(key, username);
            // Start activity
            activity.startActivity(intent);
        }
        // Else a cancel button was clicked
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
     * Description: Displays the info for the user's scheduled  appointments.
     * Calls upon:
     * displayText: to display the info for the appointment.
     * @param name - a String with the username of the user signed in.
     * @param buttonIDs - Array of integers used to store the ids
     *                    for buttons that cancel an appointment.
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

        // Load the user's scheduled appointments
        appointments.setMyAppointments(username);

        // If there are more text views than appointments to display
        if (appointments.getNumberOfMyAppointments() < dateTextViewIDs.length) {
            // Display appointments
            for (int i = 0; i < appointments.getNumberOfMyAppointments(); i++) {

                String date = appointments.getMyAppointment(i).getDate();
                String time = appointments.getMyAppointment(i).getTime();
                String vaccine = appointments.getMyAppointment(i).getVaccine();

                displayText(date, time, vaccine,  dateTextViewIDs[i],
                        timeTextViewIDs[i], vaccineTextViewIDs[i]);
            }
            // Hide empty text views
            textViewsUsed = appointments.getNumberOfMyAppointments();
            for (int j = textViewsUsed; j < dateTextViewIDs.length; j++) {

                displayText("", "", "",  dateTextViewIDs[j],
                        timeTextViewIDs[j], vaccineTextViewIDs[j]);
            }
        }
        // Else display all text views
        else {
            textViewsUsed = 0;
            for (int i = 0; i < dateTextViewIDs.length; i++) {

                String date = appointments.getMyAppointment(i).getDate();
                String time = appointments.getMyAppointment(i).getTime();
                String vaccine = appointments.getMyAppointment(i).getVaccine();

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