package edu.utsa.cs3443.rowdystudenthealth.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.utsa.cs3443.rowdystudenthealth.MenuActivity;
import edu.utsa.cs3443.rowdystudenthealth.model.credentialManager;

public class LoginController {

    private EditText userNameField;
    private EditText passwordField;
    private TextView bannerField;

    private Button loginButton;
    private Button btn_create;
    private credentialManager credentialBase;
    private Activity loginActivity;
    private String username;
    private static String key = "username";
    private Context c;

    public LoginController(Activity loginActivity, EditText userNameField, EditText passwordField, TextView bannerField, Button loginButton){
        this.loginActivity = loginActivity;
        this.userNameField = userNameField;
        this.passwordField = passwordField;
        this.bannerField = bannerField;
        this.loginButton = loginButton;
        // Get a valid Context object by calling getApplicationContext() on loginActivity
        c = loginActivity.getApplicationContext();
        credentialBase = new credentialManager(c);
    }

    public static String getUsername(Intent intent) {
        return intent.getStringExtra(key);
    }

    public int authenticate(String username, String password){
        if(credentialBase.isValid(username, password, this.credentialBase)){
            this.username = username;
            return 1;
        } else{
            return 0;
        }
    }

    public void startActivity() {
        Intent intent = new Intent(loginActivity, MenuActivity.class);
        intent.putExtra(key, username);
        loginActivity.startActivity(intent);
    }
}