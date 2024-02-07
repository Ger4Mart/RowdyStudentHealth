package edu.utsa.cs3443.rowdystudenthealth.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.utsa.cs3443.rowdystudenthealth.LoginActivity;
import edu.utsa.cs3443.rowdystudenthealth.MainActivity;
import edu.utsa.cs3443.rowdystudenthealth.MenuActivity;
import edu.utsa.cs3443.rowdystudenthealth.model.credentialManager;
/**
 * createController.java
 * Description: Controller that manages the createActivity activity
 * and communicates between the view and the model.
 * Variables include:
 *EditTexts that receive the data from the Activity and passes it to the constructor
 * Button create the new username on the temoporary file
 * String key to add the username to the program to add the vaccines appointments
 * @author Gerardo Martinez (fcv775)
 * UTSA CS 3443-003 - Team Project
 * Spring 2023
 */
public class CreateController {

    private EditText c_userNameField;
    private EditText c_passwordField;
    private TextView c_bannerField;

    private Button btn_create;
    private credentialManager credentialBase;
    private Activity CreateActivity;
    private String username;
    private static String key = "username";
    Context c;
//constructor of the create Activity
    public CreateController(Activity CreateActivity, EditText c_userNameField, EditText c_passwordField, TextView c_bannerField, Button btn_create){
        this.CreateActivity = CreateActivity;
        this.c_userNameField = c_userNameField;
        this.c_passwordField = c_passwordField;
        this.c_bannerField = c_bannerField;
        this.btn_create = btn_create;
        c = CreateActivity.getApplicationContext(); // TODO added this
        credentialBase = new credentialManager(c);
    }
// check if the username and the password are valids
    public int authenticate(String username, String password){

        if(credentialBase.isValid(username, password, this.credentialBase)){
            this.username = username;
            return 1;
        } else{
            return 0;
        }
    }
//start the activity once the username and the password is introduced
    public void startActivity() {
        Intent intent = new Intent(CreateActivity, LoginActivity.class);
        intent.putExtra(key, username);
        CreateActivity.startActivity(intent);
    }



}
