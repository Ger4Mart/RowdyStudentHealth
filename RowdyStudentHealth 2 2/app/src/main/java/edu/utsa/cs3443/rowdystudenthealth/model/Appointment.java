package edu.utsa.cs3443.rowdystudenthealth.model;

/**
 * Appointment.java
 * Description: Creates an appointment object.
 * Variables include:
 * date - a String with date of the appointment.
 * time - a String with time of the appointment.
 * vaccine - a String with the vaccine that the appointment is for.
 * username - a String with the username of the person going to the appointment.
 * @author Kaleb Phillips (bzr855)
 * UTSA CS 3443-003 - Team Project
 * Spring 2023
 */
public class Appointment {

    // variables
    private String date;
    private String time;
    private String vaccine;
    private String username;

    // constructor that requires everything accept for a username
    public Appointment( String date, String time, String vaccine ) {
        this.date = date;
        this.time = time;
        this.vaccine = vaccine;
    }

    // constructor that requires a username
    public Appointment( String date, String time, String vaccine, String username ) {
        this.date = date;
        this.time = time;
        this.vaccine = vaccine;
        this.username = username;
    }

    // setters
    // --------------------------------------------------------------------------------------------

    /**
     * Method: setDate
     * Description: Sets the date of the appointment.
     * @param date - a String with date of the appointment.
     */
    public void setDate( String date ) {
        this.date = date;
    }

    /**
     * Method: setTime
     * Description: Sets the time of the appointment.
     * @param time - a String with time of the appointment.
     */
    public void setTime( String time ) {
        this.date = time;
    }

    /**
     * Method: setVaccine
     * Description: Sets the vaccine that the appointment is for.
     * @param vaccine - a String with the vaccine that the appointment is for.
     */
    public void setVaccine( String vaccine ) {
        this.date = vaccine;
    }

    /**
     * Method: setUsername
     * Description: Sets the username of the person going to the appointment.
     * @param username - a String with the username of the person that
     *                   is scheduled for the appointment.
     */
    public void setUsername( String username ) {
        this.date = username;
    }
    // --------------------------------------------------------------------------------------------

    // getters
    // --------------------------------------------------------------------------------------------

    /**
     * Method: getDate
     * Description: Gets the date of the appointment.
     * @return date - a String with date of the appointment.
     */
    public String getDate() {
        return date;
    }

    /**
     * Method: getTime
     * Description: Gets the time of the appointment.
     * @return time - a String with time of the appointment.
     */
    public String getTime() {
        return time;
    }

    /**
     * Method: getVaccine
     * Description: Gets the vaccine that the appointment is for.
     * @return vaccine - a String with the vaccine that the appointment is for.
     */
    public String getVaccine() {
        return vaccine;
    }

    /**
     * Method: getUsername
     * Description: Gets the username of the person going to the appointment.
     * @return username - a String with the username of the person that
     *                    is scheduled for the appointment.
     */
    public String getUsername() {
        return username;
    }
    // --------------------------------------------------------------------------------------------

    /**
     * Method: toString
     * Description: Returns a string representation of an appointment.
     * Calls upon:
     * getDate - to get a String with date of the appointment.
     * getTime - to get a String with time of the appointment.
     * getVaccine - to get a String with the vaccine that the appointment is for.
     * @return output - a String representation of an appointment.
     */
    public String toString() {

        String output = getDate() + " " + getTime() + " " + getVaccine();
        return output;
    }
}