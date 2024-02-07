package edu.utsa.cs3443.rowdystudenthealth.controller;

import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;

import edu.utsa.cs3443.rowdystudenthealth.model.loadTips;

public class TipsController implements View.OnClickListener {

    private loadTips tip1, tip2, tip3, tip4, tip5, tip6;
    private TextView t1Text, t2Text, t3Text, t4Text, t5Text, t6Text;

    public TipsController(TextView t1Text, TextView t2Text, TextView t3Text, TextView t4Text,
                          TextView t5Text, TextView t6Text, AssetManager assetManager) {
        this.t1Text = t1Text;
        this.t2Text = t2Text;
        this.t3Text = t3Text;
        this.t4Text = t4Text;
        this.t5Text = t5Text;
        this.t6Text = t6Text;

        /**
         * Loads text from a file that is in the assets folder. These files have the tips for
         * one of the six disease prevention tips.
         */
        tip1 = new loadTips();
        tip1.loadText(assetManager,"tip1.txt");

        tip2 = new loadTips();
        tip2.loadText(assetManager,"tip2.txt");

        tip3 = new loadTips();
        tip3.loadText(assetManager,"tip3.txt");

        tip4 = new loadTips();
        tip4.loadText(assetManager,"tip4.txt");

        tip5 = new loadTips();
        tip5.loadText(assetManager,"tip5.txt");

        tip6 = new loadTips();
        tip6.loadText(assetManager,"tip6.txt");

        /**
         * Sets text to the screen. We want it to be visible once we go to the second screen so we
         * do not set visibility
         */
        t1Text.setText(tip1.getText());
        t2Text.setText(tip2.getText());
        t3Text.setText(tip3.getText());
        t4Text.setText(tip4.getText());
        t5Text.setText(tip5.getText());
        t6Text.setText(tip6.getText());
    }

    @Override
    public void onClick(View view) {

    }
}