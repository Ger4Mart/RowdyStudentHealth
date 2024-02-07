package edu.utsa.cs3443.rowdystudenthealth;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.utsa.cs3443.rowdystudenthealth.controller.CreateController;
import edu.utsa.cs3443.rowdystudenthealth.controller.LoginController;
/**
 * createActivity.java
 * Description: the activity that receives the information and passes it to the controller to create the new user
 * and communicates between the view and the model.
 * c_username and c_passwordfield that receives the Strings to send to the controller
 * the btn_create that send the data to the method to add the strings to the file
 * @author Gerardo Martinez (fcv775)
 * UTSA CS 3443-003 - Team Project
 * Spring 2023
 */
public class CreateActivity extends AppCompatActivity {

    private CreateController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        EditText c_userNameField = findViewById(R.id.c_username);
        EditText c_passwordField = findViewById(R.id.c_password);
        TextView c_bannerField = findViewById(R.id.c_loginBanner);
        Button btn_create = findViewById(R.id.btn_create);

        controller = new CreateController(this, c_userNameField, c_passwordField, c_bannerField, btn_create);
        setOnFocusChangeListener(c_userNameField);
        setOnFocusChangeListener(c_passwordField);
        setOnClickListener(btn_create);
    }
    //method to change the text to string
    public void setOnFocusChangeListener(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String hint = (String) editText.getHint();

                if (hasFocus) {
                    editText.setHint(hint);
                } else {
                    if (editText.getText().toString().isEmpty()) {
                        editText.setHint(hint);
                    }
                    hideKeyboard(editText);
                }
            }
        });
    }
//method that receives the Strings, and compares if the username and password are introduced correctly and reads the file
    //and adds it to the users file so we can log in in the LoginActivity
    public void setOnClickListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText myEditText1 = findViewById(R.id.c_username);
                EditText myEditText2 = findViewById(R.id.c_password);
                String userInput1 = myEditText1.getText().toString();
                String userInput2 = myEditText2.getText().toString();

                //Toast.makeText(getApplicationContext(), "Button clicked", Toast.LENGTH_SHORT).show();

                if (userInput1.isEmpty() || userInput2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You may enter both a username and password to Create your account", Toast.LENGTH_SHORT).show();

                } else  {
                    Toast.makeText(getApplicationContext(), "Your Account has beeen created!", Toast.LENGTH_SHORT).show();

                    //AssetManager = c.getAssets();
//writer for the method on the "temporarr" file
                    File file = new File("/data/data/edu.utsa.cs3443.rowdystudenthealth/code_cache/.overlay/base.apk/assets/users.txt");
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)); // append mode
                        bw.write(userInput1+","+userInput2+"\n");
                        bw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    controller.startActivity();
                }

            }});
}}