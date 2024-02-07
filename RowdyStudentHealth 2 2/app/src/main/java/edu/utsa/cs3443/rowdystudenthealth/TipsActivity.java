package edu.utsa.cs3443.rowdystudenthealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import edu.utsa.cs3443.rowdystudenthealth.controller.TipsController;

public class TipsActivity extends AppCompatActivity {

    private TipsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tips);

        TextView t1Text = findViewById(R.id.tip1);
        TextView t2Text = findViewById(R.id.tip2);
        TextView t3Text = findViewById(R.id.tip3);
        TextView t4Text = findViewById(R.id.tip4);
        TextView t5Text = findViewById(R.id.tip5);
        TextView t6Text = findViewById(R.id.tip6);

        controller = new TipsController(t1Text, t2Text, t3Text, t4Text, t5Text, t6Text, getAssets());

    }
    public boolean onOptionsItemSelected(MenuItem item) {
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