package edu.utsa.cs3443.rowdystudenthealth.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AppointmentList.java
 * Description: Creates a list of appointment objects.
 * Variables include:
 * appointments - an ArrayList of Appointment objects.
 * @author Kaleb Phillips (bzr855)
 * UTSA CS 3443-003 - Team Project
 * Spring 2023
 */
public class AppointmentList {

    // variables
    private ArrayList<Appointment> appointments;
    private ArrayList<Appointment> available;
    private ArrayList<Appointment> myAppointments;

    // constructor
    public AppointmentList() {
        appointments = new ArrayList<Appointment>();
        available = new ArrayList<Appointment>();
        myAppointments = new ArrayList<Appointment>();
    }

    // setters
    // --------------------------------------------------------------------------------------------

    /**
     * Method: setAppointments
     * Description: Sets the appointments ArrayList to be default appointments with default values.
     * @param date - a String with date of the appointment.
     * @param time - a String with time of the appointment.
     * @param vaccine - a String with the vaccine that the appointment is for.
     */
    public void setAppointments( String date, String time, String vaccine ) {

        // Clear old appointments
        for (Appointment appointment : appointments) {
            appointments.remove(appointment);
        }
        // Set default appointments
        for (int i = 0; i < 10; i++) {
            Appointment appointment = new Appointment(date, time, vaccine);
            appointments.add(appointment);
        }
    }

    /**
     * Method: setMyAppointments
     * Description: Sets the ArrayList of the user's appointments.
     * @param username - a String with the username of the person signed in.
     */
    public void setMyAppointments( String username ) {

        // Loop through all appointments
        for (Appointment appointment : appointments) {
            // If the appointment has a username
            if (appointment.getUsername() != null) {
                // If the appointment username matches the one passed in
                if (appointment.getUsername().equals(username)) {
                    myAppointments.add(appointment);
                }
            }
        }
    }
    // --------------------------------------------------------------------------------------------

    // getters
    // --------------------------------------------------------------------------------------------

    /**
     * Method: getNumberOfAppointments
     * Description: get the number of appointments in the ArrayList.
     * @return - an int with the number of appointments.
     */
    public int getNumberOfAppointments() {
        return appointments.size();
    }

    /**
     * Method: getAppointments
     * Description: Gets all the appointments.
     * @return appointments - an ArrayList of Appointment objects.
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Method: getAppointment
     * Description: Gets an appointment that matches the String passed in.
     * @param appointment - a String representing an appointment.
     * @return appoint - the appointment object that has a String
     *                   representation matching the String that was passed in.
     */
    public Appointment getAppointment( String appointment ) {

        // Loop through all appointments in the ArrayList
        for (Appointment appoint : appointments) {
            // If the appointment has a username
            if (appoint.getUsername() != null) {
                // If the appointment Strings match
                if (appoint.toString().equals(appointment)) {
                    // Return this appointment
                    return appoint;
                }
            }
        }
        // If no match was found
        return null;
    }

    /**
     * Method: getAppointment
     * Description: Gets an appointment at an index.
     * @param index - an int with the index to get the appointment from.
     * @return appoint - the appointment object in the appointments ArrayList
     *                   that is at he index that was passed in.
     */
    public Appointment getAppointment( int index ) {
        return appointments.get(index);
    }

    /**
     * Method: getNumberOfAvailable
     * Description: get the number of available appointments in the ArrayList.
     * @return - an int with the number of available appointments.
     */
    public int getNumberOfAvailable() {
        return available.size();
    }

    /**
     * Method: getAvailableAppointments
     * Description: Gets all available appointments.
     * @return available - an ArrayList of available appointment objects.
     */
    public ArrayList<Appointment> getAvailableAppointments() {
        return available;
    }

    /**
     * Method: getAvailable
     * Description: Gets an available appointment that matches the String passed in.
     * @param appointment - a String representing an appointment.
     * @return appoint - the appointment object in the available ArrayList that has a
     *                   String representation matching the String that was passed in.
     */
    public Appointment getAvailable( String appointment ) {

        // Loop through all available appointments in the ArrayList
        for (Appointment appoint : available) {
            // If the appointment Strings match
            if (appoint.toString().equals(appointment)) {
                // Return this appointment
                return appoint;
            }
        }
        // If no match was found
        return null;
    }

    /**
     * Method: getAvailable
     * Description: Gets an available appointment at an index.
     * @param index - an int with the index to get the appointment from.
     * @return appoint - the appointment object in the available ArrayList
     *                   that is at he index that was passed in.
     */
    public Appointment getAvailable( int index ) {
        return available.get(index);
    }

    /**
     * Method: getNumberOfMyAppointments
     * Description: get the number of the user's appointments in the ArrayList.
     * @return - an int with the number of the user's appointments.
     */
    public int getNumberOfMyAppointments() {
        return myAppointments.size();
    }

    /**
     * Method: getMyAppointments
     * Description: Gets all of the user's appointments.
     * @return myAppointments - an ArrayList of the user's appointments.
     */
    public ArrayList<Appointment> getMyAppointments() {
        return myAppointments;
    }

    /**
     * Method: getMyAppointment
     * Description: Gets a user's appointment that matches the String passed in.
     * @param appointment - a String representing an appointment.
     * @return appoint - the appointment object in the ArrayList of the user's appointments
     *                   that has a String representation matching the String that was passed in.
     */
    public Appointment getMyAppointment( String appointment ) {

        // Loop through all of the user's appointments in the ArrayList
        for (Appointment appoint : myAppointments) {
            // If the appointment Strings match
            if (appoint.toString().equals(appointment)) {
                // Return this appointment
                return appoint;
            }
        }
        // If no match was found
        return null;
    }

    /**
     * Method: getMyAppointment
     * Description: Gets a user's appointment at an index.
     * @param index - an int with the index to get the appointment from.
     * @return appoint - the appointment object in the ArrayList of the user's
     *                   appointments that is at he index that was passed in.
     */
    public Appointment getMyAppointment( int index ) {
        return myAppointments.get(index);
    }
    // --------------------------------------------------------------------------------------------

    /**
     * Method: createFile
     * Description: Creates a file for reading and writing by
     * copying the contents of a file in the assets folder.
     * Does not create the file if it already exists.
     * @param manager - an AssetManager used to access the data file.
     * @param context - a Context used to access data files to read and write to.
     */
    public void createFile( AssetManager manager, Context context ) {

        // Check if file already exists
        File file = new File(context.getFilesDir(),"updated.csv");
        if (file.exists()) {
            return;
        }
        // Create the file and copy contents from file in assets folder
        else {
            // Scanner object for reading file
            Scanner scan = null;
            try {
                // File object for the file in the assets folder
                InputStream oldFile = manager.open("appointments.csv");

                // File object for new file
                OutputStreamWriter newFile = new OutputStreamWriter(context.openFileOutput(
                        "updated.csv", context.MODE_PRIVATE));
                // Writer object for writing to file
                PrintWriter writer = new PrintWriter(newFile);

                scan = new Scanner(oldFile);
                // String used to store the current line in the file
                String line = "";

                // Loop over each line in the file which contains the info for an appointment
                while (scan.hasNextLine()) {

                    // Get appointment info on the current line
                    line = scan.nextLine();

                    // Write to new file
                    writer.println(line);
                }
                // close the file objects
                oldFile.close();
                newFile.close();
                scan.close();
            } catch ( IOException e ) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method: loadAppointments
     * Description: Loads appointments from a file.
     * Calls upon:
     * createFile - to creates a file for reading and writing if it doesn't already exist.
     * @param manager - an AssetManager used to access the data files.
     * @param context - a Context used to access data files to read and write to.
     */
    public void loadAppointments( AssetManager manager, Context context ) {

        // Create file for updating if it doesn't exist
        createFile(manager, context);

        // Scanner object for reading file
        Scanner scan = null;
        try {
            // file object for the file with the appointments
            InputStream file = context.openFileInput("updated.csv");
            scan = new Scanner(file);
            // String used to store the current line in the file
            String line = "";
            // Tokens to hold each separate piece of data on the line
            String[] tokens;

            // Loop over each line in the file which contains the information for an appointment
            while (scan.hasNextLine()) {

                // Get appointment info on the current line
                line = scan.nextLine();

                /*
                 * Separate line by commas.
                 * NOTE: Expected format for the line is:
                 * date,time PM/AM,vaccine(potentially with spaces in name),optionalUsername
                 * (e.g. 5/29/2023,1:00 PM,COVID-19
                 *  e.g. 5/22/2023,2:30 PM,Hepatitis B
                 *  e.g. 5/15/2023,1:30 PM,Flu,Username123)
                 */
                tokens = line.split(",");

                /*
                 * Create appointment
                 * NOTE:
                 * Tokens[0] contains the date.
                 * Tokens[1] contains the time.
                 * Tokens[2] contains the vaccine.
                 * Tokens[3] contains the username if there is one.
                 */
                // If there is a username
                if (tokens.length == 4) {
                    Appointment appointment = new Appointment(tokens[0], tokens[1], tokens[2]
                            , tokens[3]);
                    appointments.add(appointment);
                }
                // Else there is no username
                else {
                    Appointment appointment = new Appointment(tokens[0], tokens[1], tokens[2]);
                    appointments.add(appointment);
                    // Add to available appointments
                    available.add(appointment);
                }
            }
            // close the file
            file.close();
            scan.close();
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method: updateFile
     * Description: Updates an appointment in a file by
     * adding or removing the the username of a person that is signed in.
     * @param context - a Context used to access data files to read and write to.
     * @param appointment - a String representation of the appointment.
     * @param username a String with the username of the person that is signed in.
     */
    public void updateFile( Context context, String appointment, String username, String mode ) {

        // ArrayList to hold fields from the csv file
        ArrayList<String> output = new ArrayList<String>();
        // ArrayList to hold number of fields in each line
        ArrayList<Integer> fields = new ArrayList<Integer>();
        // Scanner object for reading file
        Scanner scan = null;
        try {
            // File object for reading the file with the appointments
            InputStream file = context.openFileInput("updated.csv");
            scan = new Scanner(file);

            // String used to store the current line in the file
            String line = "";
            // Tokens to hold each separate piece of data on the line
            String[] tokens;

            // Loop over each line in the file which contains the info for an appointment
            while (scan.hasNextLine()) {
                // Get dino info on the current line
                line = scan.nextLine();

                /*
                 * Separate line by commas.
                 * NOTE: Expected format for the line is:
                 * date,time PM/AM,vaccine(potentially with spaces in name),optionalUsername
                 * (e.g. 5/29/2023,1:00 PM,COVID-19
                 *  e.g. 5/22/2023,2:30 PM,Hepatitis B
                 *  e.g. 5/15/2023,1:30 PM,Flu,Username123)
                 */
                tokens = line.split(",");
                // Reformat current appointment to have spaces for
                // comparison to the appointment passed in.
                String currentAppointment = tokens[0] + " " + tokens[1] + " " + tokens[2];

                // If the appointment matches the one that was passed in
                if (currentAppointment.equals(appointment)) {

                    // If in cancel appointment mode
                    if (mode.equals("Cancel")) {
                        System.out.println( "Mode: " + mode);
                        // If there is a username
                        if ( tokens.length == 4) {
                            // If the username matches
                            if (tokens[3].equals(username)) {
                                // Don't include username for rewriting to cancel appointment
                                output.add(tokens[0]);
                                output.add(tokens[1]);
                                output.add(tokens[2]);
                                // Set number of fields
                                fields.add(3);
                            }
                            // Else this is not this user's appointment
                            else {
                                // Do not change original data
                                output.add(tokens[0]);
                                output.add(tokens[1]);
                                output.add(tokens[2]);
                                output.add(tokens[3]);
                                // Use original number of fields
                                fields.add(tokens.length);
                            }
                        }
                        // Else there is no username
                        else {
                            // Do not change original data
                            output.add(tokens[0]);
                            output.add(tokens[1]);
                            output.add(tokens[2]);
                            // Use original number of fields
                            fields.add(tokens.length);
                        }
                    }
                    // Else if in schedule appointment mode
                    else if (mode.equals("Schedule")){
                        System.out.println( "Mode: " + mode);
                        // Add original data
                        output.add(tokens[0]);
                        output.add(tokens[1]);
                        output.add(tokens[2]);
                        // Add the username to the end of the line
                        output.add(username);
                        // Set number of fields
                        fields.add(4);
                    }
                    // Error case
                    else {
                        System.out.println( "Invalid mode: " + mode );
                    }
                }
                else {
                    // When the appointment doesn't match
                    // Add original data
                    output.add(tokens[0]);
                    output.add(tokens[1]);
                    output.add(tokens[2]);
                    // If there is a username
                    if ( tokens.length == 4) {
                        output.add(tokens[3]);
                    }
                    // Use original number of fields
                    fields.add(tokens.length);
                }
            }
            // Close the file
            file.close();

            // File object for writing to the file with the appointments
            OutputStreamWriter newFile = new OutputStreamWriter(context.openFileOutput(
                    "updated.csv", context.MODE_PRIVATE));
            // Writer object for writing to file
            PrintWriter writer = new PrintWriter(newFile);

            int k = 0;
            // Loop through the lines
            for (int i = 0; i < fields.size(); i++) {
                // Loop through each field in a given line
                for (int j = 0; j < fields.get(i); j++) {
                    // Write the field
                    writer.append(output.get(k));
                    k++;
                    // If this is not the last field in the line
                    if (j < fields.get(i) - 1) {
                        // Write comma
                        writer.append(",");
                    }
                }
                // If this is not the last line in the file
                if (i < fields.size() - 1) {
                    // Write newline after the last field in the line
                    writer.append("\n");
                }
            }

            // Close objects for file
            newFile.close();
            writer.close();
            scan.close();
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method: toString
     * Description: Returns a string representation of the appointments.
     * Calls upon:
     * toString - in Appointment.java to get a string representation of an appointment.
     * @return output - a String representation of the appointments.
     */
    public String toString() {

        String output = "";
        for (Appointment appointment : appointments) {
            output += appointment.toString();
        }
        return output;
    }
}