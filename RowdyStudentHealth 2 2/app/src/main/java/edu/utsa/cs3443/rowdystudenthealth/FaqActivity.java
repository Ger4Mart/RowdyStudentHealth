package edu.utsa.cs3443.rowdystudenthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.utsa.cs3443.rowdystudenthealth.controller.FaqController;


public class FaqActivity extends AppCompatActivity {

    private FaqController controller;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                      //////////////////////////////////////////////
        setContentView(R.layout.activity_faq);

        // Creates the button and then calls openTipsActivity method which moves us to next screen.
        button = findViewById(R.id.tipsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTipsActivity();
            }
        });

        AssetManager assetManager = getAssets();

        // Creates the text views
        TextView a1Text = findViewById(R.id.a1);
        TextView a2Text = findViewById(R.id.a2);
        TextView a3Text = findViewById(R.id.a3);
        TextView a4Text = findViewById(R.id.a4);
        TextView a5Text = findViewById(R.id.a5);

        // Creates the buttons
        Button drop1Button = findViewById(R.id.drop1);
        Button drop2Button = findViewById(R.id.drop2);
        Button drop3Button = findViewById(R.id.drop3);
        Button drop4Button = findViewById(R.id.drop4);
        Button drop5Button = findViewById(R.id.drop5);

        controller = new FaqController(a1Text, a2Text, a3Text, a4Text, a5Text, assetManager);

        drop1Button.setOnClickListener(controller);
        drop2Button.setOnClickListener(controller);
        drop3Button.setOnClickListener(controller);
        drop4Button.setOnClickListener(controller);
        drop5Button.setOnClickListener(controller);

    }

    /**
     * Method to go from one screen to the next.
     */
    public void openTipsActivity() {
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {           /////////////////////////////////////////////////////////////////////
        switch (item.getItemId()) {
            case android.R.id.home:
                // user pressed the Up button, navigate back to parent Activity
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}