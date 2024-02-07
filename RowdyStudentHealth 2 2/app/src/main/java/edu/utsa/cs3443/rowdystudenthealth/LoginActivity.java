package edu.utsa.cs3443.rowdystudenthealth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.rowdystudenthealth.controller.CreateController;
import edu.utsa.cs3443.rowdystudenthealth.controller.LoginController;

public class LoginActivity extends AppCompatActivity {

    private LoginController controller;
    private CreateController c_controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText userNameField = findViewById(R.id.username);
        EditText passwordField = findViewById(R.id.password);
        TextView bannerField = findViewById(R.id.loginBanner);
        Button loginButton = findViewById(R.id.loginButton);

        controller = new LoginController(this, userNameField, passwordField, bannerField, loginButton);
        setOnFocusChangeListener(userNameField);
        setOnFocusChangeListener(passwordField);
        setOnClickListener(loginButton);

    }
    public void setOnFocusChangeListener(EditText editText){
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String hint = (String) editText.getHint();

                if(hasFocus) {
                    editText.setHint(hint);
                }
                else {
                    if (editText.getText().toString().isEmpty()) {
                        editText.setHint(hint);
                    }
                    hideKeyboard(editText);
                }
            }});
    }
    public void setOnClickListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText myEditText1 = findViewById(R.id.username);
                EditText myEditText2 = findViewById(R.id.password);
                String userInput1 = myEditText1.getText().toString();
                String userInput2 = myEditText2.getText().toString();

                //Toast.makeText(getApplicationContext(), "Button clicked", Toast.LENGTH_SHORT).show();

                if (userInput1.isEmpty() || userInput2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You may enter both a username and password to login", Toast.LENGTH_SHORT).show();

                } else if (controller.authenticate(userInput1, userInput2) == 1) {
                    Toast.makeText(getApplicationContext(), "WELCOME!", Toast.LENGTH_SHORT).show();
                    controller.startActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Credentials. Try again.", Toast.LENGTH_SHORT).show();

                }
            }});
        Button createButton = findViewById(R.id.btn_create);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

    }
    private void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusedView = getCurrentFocus();
        if (focusedView != null) {
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }
}