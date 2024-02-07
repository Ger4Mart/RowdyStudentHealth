package edu.utsa.cs3443.rowdystudenthealth.controller;

import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;

import edu.utsa.cs3443.rowdystudenthealth.R;
import edu.utsa.cs3443.rowdystudenthealth.model.loadAnswers;

public class FaqController implements View.OnClickListener {

    private loadAnswers answer1, answer2, answer3, answer4, answer5;
    private TextView a1Text, a2Text, a3Text, a4Text, a5Text;

    public FaqController(TextView a1Text, TextView a2Text, TextView a3Text, TextView a4Text,
                         TextView a5Text, AssetManager assetManager) {
        this.a1Text = a1Text;
        this.a2Text = a2Text;
        this.a3Text = a3Text;
        this.a4Text = a4Text;
        this.a5Text = a5Text;


        // Loads text from a file that is in the assets folder. These files have the answers to
        // one of the five FAQs.
        answer1 = new loadAnswers();
        answer1.loadText(assetManager, "a1.txt");

        answer2 = new loadAnswers();
        answer2.loadText(assetManager, "a2.txt");

        answer3 = new loadAnswers();
        answer3.loadText(assetManager, "a3.txt");

        answer4 = new loadAnswers();
        answer4.loadText(assetManager, "a4.txt");

        answer5 = new loadAnswers();
        answer5.loadText(assetManager, "a5.txt");

        // Sets text to the screen. Currently sets texts to be invisible.
        a1Text.setText(answer1.getText());
        a1Text.setVisibility(View.GONE);

        a2Text.setText(answer2.getText());
        a2Text.setVisibility(View.GONE);

        a3Text.setText(answer3.getText());
        a3Text.setVisibility(View.GONE);

        a4Text.setText(answer4.getText());
        a4Text.setVisibility(View.GONE);

        a5Text.setText(answer5.getText());
        a5Text.setVisibility(View.GONE);

    }

    /**
     * On click listener to get the id of the button pressed. Once the button is pressed then it
     * will make the text visible.
     * @param view
     */
    public void onClick(View view) {
        if (view.getId() == R.id.drop1) {
            if (a1Text.getVisibility() == View.GONE) {
                a1Text.setVisibility(View.VISIBLE);
            }else {
                a1Text.setVisibility(View.GONE);
            }
        }
        else if (view.getId() == R.id.drop2) {
            if (a2Text.getVisibility() == View.GONE) {
                a2Text.setVisibility(View.VISIBLE);
            }else {
                a2Text.setVisibility(View.GONE);
            }
        }
        else if (view.getId() == R.id.drop3) {
            if (a3Text.getVisibility() == View.GONE) {
                a3Text.setVisibility(View.VISIBLE);
            } else {
                a3Text.setVisibility(View.GONE);
            }
        }
        else if (view.getId() == R.id.drop4) {
            if (a4Text.getVisibility() == View.GONE) {
                a4Text.setVisibility(View.VISIBLE);
            } else {
                a4Text.setVisibility(View.GONE);
            }
        }
        else if (view.getId() == R.id.drop5) {
            if (a5Text.getVisibility() == View.GONE) {
                a5Text.setVisibility(View.VISIBLE);
            } else {
                a5Text.setVisibility(View.GONE);
            }
        }
    }
}